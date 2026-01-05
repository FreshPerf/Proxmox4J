package fr.freshperf.proxmox4j.request;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * A transformer that wraps a string UPID response into a PveTask object structure.
 * Used when Proxmox returns a task ID as a simple string instead of an object.
 */
public class TaskResponseTransformer implements ResponseTransformer {

    @Override
    public JsonElement transform(JsonElement jsonElement, Class<?> targetClass) {
        // If the response is a string (UPID), wrap it in an object
        if (jsonElement != null && jsonElement.isJsonPrimitive() && jsonElement.getAsJsonPrimitive().isString()) {
            JsonObject wrapper = new JsonObject();
            wrapper.add("upid", jsonElement);
            return wrapper;
        }

        // Otherwise, return as-is
        return jsonElement;
    }
}

