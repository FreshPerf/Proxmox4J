package fr.freshperf.pve4j.entities.pools;

import fr.freshperf.pve4j.entities.options.ParamsConvertible;
import fr.freshperf.pve4j.util.ParamsHelpers;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * Options for updating a resource pool.
 * Use the builder pattern to configure update settings.
 */
public class PvePoolUpdateOptions implements ParamsConvertible {

    private String comment;
    private java.util.List<Integer> vms;
    private java.util.List<String> storage;
    private Boolean delete;

    /**
     * Creates a new builder for pool update options.
     *
     * @return a new PvePoolUpdateOptions instance
     */
    public static PvePoolUpdateOptions builder() {
        return new PvePoolUpdateOptions();
    }

    @Override
    public void populateParams(Map<String, Object> params) {
        ParamsHelpers.put(params, "comment", comment);
        ParamsHelpers.putBool(params, "delete", delete);
        
        if (vms != null && !vms.isEmpty()) {
            String vmList = vms.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(","));
            params.put("vms", vmList);
        }
        
        if (storage != null && !storage.isEmpty()) {
            params.put("storage", String.join(",", storage));
        }
    }

    /**
     * Sets the pool comment/description.
     *
     * @param comment the comment text
     * @return this instance for method chaining
     */
    public PvePoolUpdateOptions comment(String comment) {
        this.comment = comment;
        return this;
    }

    /**
     * Sets the VMs to add to (or remove from) the pool.
     *
     * @param vms list of VMIDs
     * @return this instance for method chaining
     */
    public PvePoolUpdateOptions vms(java.util.List<Integer> vms) {
        this.vms = vms;
        return this;
    }

    /**
     * Sets the storage to add to (or remove from) the pool.
     *
     * @param storage list of storage names
     * @return this instance for method chaining
     */
    public PvePoolUpdateOptions storage(java.util.List<String> storage) {
        this.storage = storage;
        return this;
    }

    /**
     * Sets whether to remove (delete=true) or add (delete=false) the specified VMs/storage.
     * When true, the specified VMs and storage will be removed from the pool.
     * When false (default), they will be added.
     *
     * @param delete true to remove, false to add
     * @return this instance for method chaining
     */
    public PvePoolUpdateOptions delete(Boolean delete) {
        this.delete = delete;
        return this;
    }

    /**
     * Builds and returns the options instance.
     *
     * @return this instance
     */
    public PvePoolUpdateOptions build() {
        return this;
    }
}

