package fr.freshperf.proxmox4j.request;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonPrimitive;

import java.util.Map;
import java.util.Set;

public class ProxmoxResponseTransformer implements ResponseTransformer {

    @Override
    public JsonElement transform(JsonElement jsonElement) {
        if (jsonElement == null || jsonElement.isJsonNull()) {
            return jsonElement;
        }

        if (jsonElement.isJsonObject()) {
            return transformObject(jsonElement.getAsJsonObject());
        }

        if (jsonElement.isJsonArray()) {
            return transformArray(jsonElement.getAsJsonArray());
        }

        if (jsonElement.isJsonPrimitive()) {
            return jsonElement;
        }

        return jsonElement;
    }

    private JsonElement transformObject(JsonObject jsonObject) {
        JsonObject transformed = new JsonObject();
        
        Set<Map.Entry<String, JsonElement>> entries = jsonObject.entrySet();
        for (Map.Entry<String, JsonElement> entry : entries) {
            String key = entry.getKey();
            JsonElement value = entry.getValue();
            
            if (value.isJsonPrimitive()) {
                JsonPrimitive primitive = value.getAsJsonPrimitive();
                if (primitive.isNumber()) {
                    Number number = primitive.getAsNumber();
                    if (isBooleanField(key)) {
                        transformed.add(key, new JsonPrimitive(number.intValue() == 1));
                    } else {
                        transformed.add(key, transform(value));
                    }
                } else {
                    transformed.add(key, transform(value));
                }
            } else {
                transformed.add(key, transform(value));
            }
        }
        
        return transformed;
    }

    private JsonElement transformArray(JsonArray jsonArray) {
        JsonArray transformed = new JsonArray();
        for (JsonElement element : jsonArray) {
            transformed.add(transform(element));
        }
        return transformed;
    }

    protected boolean isBooleanField(String fieldName) {
        String lowerName = fieldName.toLowerCase();
        return lowerName.contains("local") || 
               lowerName.contains("online") || 
               lowerName.contains("quorate") ||
               lowerName.contains("enabled") ||
               lowerName.contains("active") ||
               lowerName.contains("running") ||
               lowerName.contains("locked") ||
               lowerName.contains("protected") ||
               lowerName.equals("ha") ||
               lowerName.equals("template") ||
               lowerName.equals("shared");
    }
}

