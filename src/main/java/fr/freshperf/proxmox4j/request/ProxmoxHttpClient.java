package fr.freshperf.proxmox4j.request;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.security.cert.X509Certificate;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProxmoxHttpClient {

    private final String apiToken;
    private final HttpClient client;
    private final String baseUrl;
    private final Gson gson;
    private final List<ResponseTransformer> transformers;
    private final ProxmoxResponseTransformer defaultTransformer;

    public ProxmoxHttpClient(String baseUrl, String apiToken) {
        this(baseUrl, apiToken, true);
    }

    public ProxmoxHttpClient(String baseUrl, String apiToken, boolean verifySSL) {
        this(baseUrl, apiToken, verifySSL, new ArrayList<>());
    }

    public ProxmoxHttpClient(String baseUrl, String apiToken, boolean verifySSL, List<ResponseTransformer> customTransformers) {
        this.baseUrl = baseUrl.endsWith("/") ? baseUrl : baseUrl + "/";
        this.apiToken = apiToken;
        
        HttpClient.Builder clientBuilder = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .connectTimeout(Duration.ofSeconds(10));
        
        if (!verifySSL) {
            try {
                SSLContext sslContext = SSLContext.getInstance("TLS");
                TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        public X509Certificate[] getAcceptedIssuers() {
                            return null;
                        }
                        public void checkClientTrusted(X509Certificate[] certs, String authType) {
                        }
                        public void checkServerTrusted(X509Certificate[] certs, String authType) {
                        }
                    }
                };
                sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
                clientBuilder.sslContext(sslContext);
            } catch (Exception e) {
                throw new RuntimeException("Failed to create SSL context", e);
            }
        }
        
        this.client = clientBuilder.build();
        this.gson = new Gson();
        this.defaultTransformer = new ProxmoxResponseTransformer();
        this.transformers = new ArrayList<>();
        this.transformers.add(defaultTransformer);
        this.transformers.addAll(customTransformers);
    }

    public void addTransformer(ResponseTransformer transformer) {
        this.transformers.add(transformer);
    }

    // --- GET ---
    public JsonObject get(String path) throws IOException, InterruptedException {
        return get(path, Map.of());
    }

    public JsonObject get(String path, Map<String, String> params) throws IOException, InterruptedException {
        return send(path, "GET", params, null, JsonObject.class);
    }

    public <T> T get(String path, Class<T> clazz) throws IOException, InterruptedException {
        return get(path, Map.of(), clazz);
    }

    public <T> T get(String path, Map<String, String> params, Class<T> clazz) throws IOException, InterruptedException {
        return send(path, "GET", params, null, clazz);
    }

    // --- POST ---
    public JsonObject post(String path) throws IOException, InterruptedException {
        return post(path, Map.of(), "{}");
    }

    public JsonObject post(String path, String jsonBody) throws IOException, InterruptedException {
        return post(path, Map.of(), jsonBody);
    }

    public JsonObject post(String path, Map<String, String> params, String jsonBody) throws IOException, InterruptedException {
        return send(path, "POST", params, jsonBody, JsonObject.class);
    }

    public <T> T post(String path, Map<String, String> params, String jsonBody, Class<T> clazz) throws IOException, InterruptedException {
        return send(path, "POST", params, jsonBody, clazz);
    }

    // --- PUT ---
    public JsonObject put(String path) throws IOException, InterruptedException {
        return put(path, Map.of(), "{}");
    }

    public JsonObject put(String path, String jsonBody) throws IOException, InterruptedException {
        return put(path, Map.of(), jsonBody);
    }

    public JsonObject put(String path, Map<String, String> params, String jsonBody) throws IOException, InterruptedException {
        return send(path, "PUT", params, jsonBody, JsonObject.class);
    }

    public <T> T put(String path, Map<String, String> params, String jsonBody, Class<T> clazz) throws IOException, InterruptedException {
        return send(path, "PUT", params, jsonBody, clazz);
    }

    // --- PATCH ---
    public JsonObject patch(String path) throws IOException, InterruptedException {
        return patch(path, Map.of(), "{}");
    }

    public JsonObject patch(String path, String jsonBody) throws IOException, InterruptedException {
        return patch(path, Map.of(), jsonBody);
    }

    public JsonObject patch(String path, Map<String, String> params, String jsonBody) throws IOException, InterruptedException {
        return send(path, "PATCH", params, jsonBody, JsonObject.class);
    }

    public <T> T patch(String path, Map<String, String> params, String jsonBody, Class<T> clazz) throws IOException, InterruptedException {
        return send(path, "PATCH", params, jsonBody, clazz);
    }

    // --- DELETE ---
    public JsonObject delete(String path) throws IOException, InterruptedException {
        return delete(path, Map.of());
    }

    public JsonObject delete(String path, Map<String, String> params) throws IOException, InterruptedException {
        return send(path, "DELETE", params, null, JsonObject.class);
    }

    public <T> T delete(String path, Map<String, String> params, Class<T> clazz) throws IOException, InterruptedException {
        return send(path, "DELETE", params, null, clazz);
    }

    // --- CORE SEND ---
    private <T> T send(String path, String method, Map<String, String> params, String body, Class<T> clazz) throws IOException, InterruptedException {
        String url = buildUrl(path, params);
        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Authorization", "PVEAPIToken=" + apiToken)
                .header("Content-Type", "application/json");

        switch (method) {
            case "GET" -> builder.GET();
            case "POST" -> builder.POST(HttpRequest.BodyPublishers.ofString(body));
            case "PUT" -> builder.PUT(HttpRequest.BodyPublishers.ofString(body));
            case "PATCH" -> builder.method("PATCH", HttpRequest.BodyPublishers.ofString(body));
            case "DELETE" -> builder.DELETE();
        }

        HttpResponse<String> response = client.send(builder.build(), HttpResponse.BodyHandlers.ofString());
        JsonElement parsedResponse = JsonParser.parseString(response.body());
        
        JsonElement dataElement = extractDataFromResponse(parsedResponse);
        
        JsonElement transformed = dataElement;
        for (ResponseTransformer transformer : transformers) {
            transformed = transformer.transform(transformed);
        }
        
        return gson.fromJson(transformed, clazz);
    }

    private JsonElement extractDataFromResponse(JsonElement jsonElement) {
        if (jsonElement.isJsonObject()) {
            JsonObject obj = jsonElement.getAsJsonObject();
            if (obj.has("data")) {
                JsonElement data = obj.get("data");
                if (data.isJsonArray() && data.getAsJsonArray().size() > 0) {
                    return data.getAsJsonArray().get(0);
                } else if (!data.isJsonNull()) {
                    return data;
                }
            }
        }
        return jsonElement;
    }

    private String buildUrl(String path, Map<String, String> params) {
        StringBuilder url = new StringBuilder(baseUrl + path);
        if (!params.isEmpty()) {
            String query = params.entrySet().stream()
                    .map(e -> URLEncoder.encode(e.getKey(), StandardCharsets.UTF_8)
                            + "=" + URLEncoder.encode(e.getValue(), StandardCharsets.UTF_8))
                    .collect(Collectors.joining("&"));
            url.append("?").append(query);
        }
        return url.toString();
    }
}
