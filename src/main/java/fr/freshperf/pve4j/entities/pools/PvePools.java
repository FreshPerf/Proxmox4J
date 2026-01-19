package fr.freshperf.pve4j.entities.pools;

import com.google.gson.reflect.TypeToken;
import fr.freshperf.pve4j.request.ProxmoxHttpClient;
import fr.freshperf.pve4j.request.ProxmoxRequest;

import java.util.List;

/**
 * Facade for Proxmox resource pool management.
 */
public class PvePools {

    private final ProxmoxHttpClient client;

    /**
     * Creates a new pools facade.
     *
     * @param client the HTTP client
     */
    public PvePools(ProxmoxHttpClient client) {
        this.client = client;
    }

    /**
     * Lists all resource pools.
     *
     * @return a request returning the list of pools
     */
    public ProxmoxRequest<List<PvePool>> list() {
        return new ProxmoxRequest<>(() ->
            client.get("pools")
                .executeList(new TypeToken<>() {})
        );
    }

    /**
     * Creates a new resource pool.
     *
     * @param poolid the pool ID (name)
     * @return a request that completes when the pool is created
     * @throws IllegalArgumentException if poolid is null or empty
     */
    public ProxmoxRequest<Void> create(String poolid) {
        return create(poolid, null);
    }

    /**
     * Creates a new resource pool with options.
     *
     * @param poolid  the pool ID (name)
     * @param options pool creation options (comment) or null
     * @return a request that completes when the pool is created
     * @throws IllegalArgumentException if poolid is null or empty
     */
    public ProxmoxRequest<Void> create(String poolid, PvePoolCreateOptions options) {
        if (poolid == null || poolid.isBlank()) {
            throw new IllegalArgumentException("poolid cannot be null or empty");
        }

        PvePoolCreateOptions effectiveOptions = options != null ? options : PvePoolCreateOptions.builder();

        return new ProxmoxRequest<>(() -> {
            client.post("pools")
                .params(effectiveOptions.toParams(poolid))
                .execute();
            return null;
        });
    }

    /**
     * Gets a specific pool by ID.
     *
     * @param poolid the pool ID
     * @return the pool item facade
     * @throws IllegalArgumentException if poolid is null or empty
     */
    public PvePoolItem get(String poolid) {
        if (poolid == null || poolid.isBlank()) {
            throw new IllegalArgumentException("poolid cannot be null or empty");
        }
        return new PvePoolItem(client, poolid);
    }
}

