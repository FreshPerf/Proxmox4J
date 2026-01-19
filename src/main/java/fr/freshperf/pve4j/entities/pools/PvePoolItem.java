package fr.freshperf.pve4j.entities.pools;

import fr.freshperf.pve4j.request.ProxmoxHttpClient;
import fr.freshperf.pve4j.request.ProxmoxRequest;

import java.util.List;

/**
 * Facade for managing a specific resource pool.
 */
public class PvePoolItem {

    private final ProxmoxHttpClient client;
    private final String poolid;

    /**
     * Creates a new pool item facade.
     *
     * @param client the HTTP client
     * @param poolid the pool ID
     */
    public PvePoolItem(ProxmoxHttpClient client, String poolid) {
        this.client = client;
        this.poolid = poolid;
    }

    /**
     * Gets detailed pool information including members.
     *
     * @return a request returning the pool details
     */
    public ProxmoxRequest<PvePoolDetails> getDetails() {
        return new ProxmoxRequest<>(() ->
            client.get("pools/" + poolid)
                .execute(PvePoolDetails.class)
        );
    }

    /**
     * Gets detailed pool information with specific member type.
     *
     * @param type the member type to filter ("qemu", "lxc", "storage")
     * @return a request returning the pool details
     */
    public ProxmoxRequest<PvePoolDetails> getDetails(String type) {
        return new ProxmoxRequest<>(() ->
            client.get("pools/" + poolid)
                .param("type", type)
                .execute(PvePoolDetails.class)
        );
    }

    /**
     * Updates the pool configuration.
     *
     * @param options the update options
     * @return a request that completes when the update is done
     * @throws IllegalArgumentException if options is null
     */
    public ProxmoxRequest<Void> update(PvePoolUpdateOptions options) {
        if (options == null) {
            throw new IllegalArgumentException("options cannot be null");
        }
        return new ProxmoxRequest<>(() -> {
            client.put("pools/" + poolid)
                .params(options.toParams())
                .execute();
            return null;
        });
    }

    /**
     * Adds VMs to this pool.
     *
     * @param vmids the VMIDs to add
     * @return a request that completes when the update is done
     */
    public ProxmoxRequest<Void> addVms(List<Integer> vmids) {
        return update(PvePoolUpdateOptions.builder().vms(vmids).delete(false));
    }

    /**
     * Removes VMs from this pool.
     *
     * @param vmids the VMIDs to remove
     * @return a request that completes when the update is done
     */
    public ProxmoxRequest<Void> removeVms(List<Integer> vmids) {
        return update(PvePoolUpdateOptions.builder().vms(vmids).delete(true));
    }

    /**
     * Adds storage to this pool.
     *
     * @param storageNames the storage names to add
     * @return a request that completes when the update is done
     */
    public ProxmoxRequest<Void> addStorage(List<String> storageNames) {
        return update(PvePoolUpdateOptions.builder().storage(storageNames).delete(false));
    }

    /**
     * Removes storage from this pool.
     *
     * @param storageNames the storage names to remove
     * @return a request that completes when the update is done
     */
    public ProxmoxRequest<Void> removeStorage(List<String> storageNames) {
        return update(PvePoolUpdateOptions.builder().storage(storageNames).delete(true));
    }

    /**
     * Deletes this pool.
     *
     * @return a request that completes when the deletion is done
     */
    public ProxmoxRequest<Void> delete() {
        return new ProxmoxRequest<>(() -> {
            client.delete("pools/" + poolid)
                .execute();
            return null;
        });
    }
}

