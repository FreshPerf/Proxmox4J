package fr.freshperf.pve4j.entities.nodes.node.qemu.snapshot;

import com.google.gson.reflect.TypeToken;
import fr.freshperf.pve4j.entities.PveTask;
import fr.freshperf.pve4j.request.ProxmoxHttpClient;
import fr.freshperf.pve4j.request.ProxmoxRequest;
import fr.freshperf.pve4j.request.TaskResponseTransformer;

import java.util.List;

/**
 * Facade for QEMU VM snapshot management.
 */
public class PveQemuSnapshots {

    private final ProxmoxHttpClient client;
    private final String nodeName;
    private final int vmid;

    /**
     * Creates a new snapshots facade.
     *
     * @param client   the HTTP client
     * @param nodeName the node name
     * @param vmid     the VM ID
     */
    public PveQemuSnapshots(ProxmoxHttpClient client, String nodeName, int vmid) {
        this.client = client;
        this.nodeName = nodeName;
        this.vmid = vmid;
    }

    /**
     * Lists all snapshots for this VM.
     *
     * @return a request returning the list of snapshots
     */
    public ProxmoxRequest<List<PveQemuSnapshot>> list() {
        return new ProxmoxRequest<>(() ->
            client.get("nodes/" + nodeName + "/qemu/" + vmid + "/snapshot")
                .executeList(new TypeToken<>() {})
        );
    }

    /**
     * Creates a new snapshot.
     *
     * @param snapname the snapshot name
     * @return a request returning the task for tracking
     * @throws IllegalArgumentException if snapname is null or empty
     */
    public ProxmoxRequest<PveTask> create(String snapname) {
        return create(snapname, null);
    }

    /**
     * Creates a new snapshot with options.
     *
     * @param snapname the snapshot name
     * @param options  snapshot creation options (description, vmstate) or null
     * @return a request returning the task for tracking
     * @throws IllegalArgumentException if snapname is null or empty
     */
    public ProxmoxRequest<PveTask> create(String snapname, PveQemuSnapshotCreateOptions options) {
        if (snapname == null || snapname.isBlank()) {
            throw new IllegalArgumentException("snapname cannot be null or empty");
        }

        PveQemuSnapshotCreateOptions effectiveOptions = options != null ? options : PveQemuSnapshotCreateOptions.builder();

        return new ProxmoxRequest<>(() ->
            client.post("nodes/" + nodeName + "/qemu/" + vmid + "/snapshot")
                .params(effectiveOptions.toParams(snapname))
                .transformer(new TaskResponseTransformer())
                .execute(PveTask.class)
        );
    }

    /**
     * Gets a specific snapshot by name.
     *
     * @param snapname the snapshot name
     * @return the snapshot item facade
     * @throws IllegalArgumentException if snapname is null or empty
     */
    public PveQemuSnapshotItem get(String snapname) {
        if (snapname == null || snapname.isBlank()) {
            throw new IllegalArgumentException("snapname cannot be null or empty");
        }
        return new PveQemuSnapshotItem(client, nodeName, vmid, snapname);
    }
}

