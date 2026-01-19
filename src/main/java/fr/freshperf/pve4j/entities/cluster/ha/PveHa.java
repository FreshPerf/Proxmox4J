package fr.freshperf.pve4j.entities.cluster.ha;

import com.google.gson.reflect.TypeToken;
import fr.freshperf.pve4j.request.ProxmoxHttpClient;
import fr.freshperf.pve4j.request.ProxmoxRequest;

import java.util.List;

/**
 * Facade for Proxmox HA (High Availability) management.
 */
public class PveHa {

    private final ProxmoxHttpClient client;

    /**
     * Creates a new HA facade.
     *
     * @param client the HTTP client
     */
    public PveHa(ProxmoxHttpClient client) {
        this.client = client;
    }

    /**
     * Lists all HA resources.
     *
     * @return a request returning the list of HA resources
     */
    public ProxmoxRequest<List<PveHaResource>> listResources() {
        return new ProxmoxRequest<>(() ->
            client.get("cluster/ha/resources")
                .executeList(new TypeToken<>() {})
        );
    }

    /**
     * Gets a specific HA resource.
     *
     * @param sid the service ID (e.g., "vm:100")
     * @return a request returning the HA resource
     */
    public ProxmoxRequest<PveHaResource> getResource(String sid) {
        if (sid == null || sid.isBlank()) {
            throw new IllegalArgumentException("sid cannot be null or empty");
        }
        return new ProxmoxRequest<>(() ->
            client.get("cluster/ha/resources/" + sid)
                .execute(PveHaResource.class)
        );
    }

    /**
     * Creates a new HA resource.
     *
     * @param sid the service ID (e.g., "vm:100", "ct:101")
     * @return a request that completes when the resource is created
     */
    public ProxmoxRequest<Void> createResource(String sid) {
        return createResource(sid, null);
    }

    /**
     * Creates a new HA resource with options.
     *
     * @param sid     the service ID (e.g., "vm:100", "ct:101")
     * @param options HA resource options or null
     * @return a request that completes when the resource is created
     */
    public ProxmoxRequest<Void> createResource(String sid, PveHaResourceCreateOptions options) {
        if (sid == null || sid.isBlank()) {
            throw new IllegalArgumentException("sid cannot be null or empty");
        }

        PveHaResourceCreateOptions effectiveOptions = options != null ? options : PveHaResourceCreateOptions.builder();

        return new ProxmoxRequest<>(() -> {
            client.post("cluster/ha/resources")
                .params(effectiveOptions.toParams(sid))
                .execute();
            return null;
        });
    }

    /**
     * Deletes an HA resource.
     *
     * @param sid the service ID
     * @return a request that completes when the resource is deleted
     */
    public ProxmoxRequest<Void> deleteResource(String sid) {
        if (sid == null || sid.isBlank()) {
            throw new IllegalArgumentException("sid cannot be null or empty");
        }
        return new ProxmoxRequest<>(() -> {
            client.delete("cluster/ha/resources/" + sid)
                .execute();
            return null;
        });
    }

    /**
     * Requests a resource state change (migrate, relocate, etc.).
     *
     * @param sid   the service ID
     * @param state the new state ("started", "stopped", "disabled", "ignored")
     * @return a request that completes when the request is submitted
     */
    public ProxmoxRequest<Void> setResourceState(String sid, String state) {
        if (sid == null || sid.isBlank()) {
            throw new IllegalArgumentException("sid cannot be null or empty");
        }
        return new ProxmoxRequest<>(() -> {
            client.put("cluster/ha/resources/" + sid)
                .param("state", state)
                .execute();
            return null;
        });
    }
}
