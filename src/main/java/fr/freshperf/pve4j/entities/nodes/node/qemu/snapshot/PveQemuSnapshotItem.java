package fr.freshperf.pve4j.entities.nodes.node.qemu.snapshot;

import fr.freshperf.pve4j.entities.PveTask;
import fr.freshperf.pve4j.request.ProxmoxHttpClient;
import fr.freshperf.pve4j.request.ProxmoxRequest;
import fr.freshperf.pve4j.request.TaskResponseTransformer;

/**
 * Facade for managing a specific QEMU VM snapshot.
 */
public class PveQemuSnapshotItem {

    private final ProxmoxHttpClient client;
    private final String nodeName;
    private final int vmid;
    private final String snapname;

    /**
     * Creates a new snapshot item facade.
     *
     * @param client   the HTTP client
     * @param nodeName the node name
     * @param vmid     the VM ID
     * @param snapname the snapshot name
     */
    public PveQemuSnapshotItem(ProxmoxHttpClient client, String nodeName, int vmid, String snapname) {
        this.client = client;
        this.nodeName = nodeName;
        this.vmid = vmid;
        this.snapname = snapname;
    }

    /**
     * Gets the snapshot configuration.
     *
     * @return a request returning the snapshot configuration
     */
    public ProxmoxRequest<PveQemuSnapshotConfig> getConfig() {
        return new ProxmoxRequest<>(() ->
            client.get("nodes/" + nodeName + "/qemu/" + vmid + "/snapshot/" + snapname + "/config")
                .execute(PveQemuSnapshotConfig.class)
        );
    }

    /**
     * Updates the snapshot configuration (description).
     *
     * @param options the update options
     * @return a request that completes when the update is done
     * @throws IllegalArgumentException if options is null
     */
    public ProxmoxRequest<Void> updateConfig(PveQemuSnapshotUpdateOptions options) {
        if (options == null) {
            throw new IllegalArgumentException("options cannot be null");
        }
        return new ProxmoxRequest<>(() -> {
            client.put("nodes/" + nodeName + "/qemu/" + vmid + "/snapshot/" + snapname + "/config")
                .params(options.toParams())
                .execute();
            return null;
        });
    }

    /**
     * Deletes this snapshot.
     *
     * @return a request returning the task for tracking
     */
    public ProxmoxRequest<PveTask> delete() {
        return new ProxmoxRequest<>(() ->
            client.delete("nodes/" + nodeName + "/qemu/" + vmid + "/snapshot/" + snapname)
                .transformer(new TaskResponseTransformer())
                .execute(PveTask.class)
        );
    }

    /**
     * Deletes this snapshot with force option.
     *
     * @param force if true, forces deletion even if snapshot is the parent of other snapshots
     * @return a request returning the task for tracking
     */
    public ProxmoxRequest<PveTask> delete(boolean force) {
        return new ProxmoxRequest<>(() ->
            client.delete("nodes/" + nodeName + "/qemu/" + vmid + "/snapshot/" + snapname)
                .param("force", force ? "1" : "0")
                .transformer(new TaskResponseTransformer())
                .execute(PveTask.class)
        );
    }

    /**
     * Rolls back the VM to this snapshot.
     * Warning: This will stop the VM if running!
     *
     * @return a request returning the task for tracking
     */
    public ProxmoxRequest<PveTask> rollback() {
        return new ProxmoxRequest<>(() ->
            client.post("nodes/" + nodeName + "/qemu/" + vmid + "/snapshot/" + snapname + "/rollback")
                .transformer(new TaskResponseTransformer())
                .execute(PveTask.class)
        );
    }

    /**
     * Rolls back the VM to this snapshot with start option.
     *
     * @param start if true, starts the VM after rollback (if it was running before)
     * @return a request returning the task for tracking
     */
    public ProxmoxRequest<PveTask> rollback(boolean start) {
        return new ProxmoxRequest<>(() ->
            client.post("nodes/" + nodeName + "/qemu/" + vmid + "/snapshot/" + snapname + "/rollback")
                .param("start", start ? "1" : "0")
                .transformer(new TaskResponseTransformer())
                .execute(PveTask.class)
        );
    }
}

