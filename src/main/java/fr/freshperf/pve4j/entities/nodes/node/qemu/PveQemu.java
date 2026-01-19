package fr.freshperf.pve4j.entities.nodes.node.qemu;

import com.google.gson.reflect.TypeToken;
import fr.freshperf.pve4j.entities.PveTask;
import fr.freshperf.pve4j.request.ProxmoxHttpClient;
import fr.freshperf.pve4j.request.ProxmoxRequest;
import fr.freshperf.pve4j.request.TaskResponseTransformer;

import java.util.List;

/**
 * Facade for QEMU VM management on a node.
 */
public record PveQemu(ProxmoxHttpClient client, String nodeName) {

    /**
     * Lists all QEMU VMs on this node.
     *
     * @return a request returning the list of VMs
     */
    public ProxmoxRequest<List<PveQemuIndex>> getIndex() {
        return new ProxmoxRequest<>(() -> 
            client.get("nodes/" + nodeName + "/qemu").executeList(new TypeToken<>() {
            })
        );
    }

    /**
     * Gets a specific VM by VMID.
     *
     * @param vmid the VM ID (must be positive)
     * @return the VM API facade
     * @throws IllegalArgumentException if vmid is not positive
     */
    public PveQemuVm get(int vmid) {
        if (vmid <= 0) {
            throw new IllegalArgumentException("VMID must be a positive integer");
        }
        return new PveQemuVm(client, nodeName, vmid);
    }

    /**
     * Creates a new QEMU VM.
     *
     * @param vmid the VM ID (must be >= 100)
     * @return a request returning the task for tracking
     * @throws IllegalArgumentException if vmid is less than 100
     */
    public ProxmoxRequest<PveTask> create(int vmid) {
        return create(vmid, null);
    }

    /**
     * Creates a new QEMU VM with options.
     *
     * @param vmid    the VM ID (must be >= 100)
     * @param options VM creation options or null
     * @return a request returning the task for tracking
     * @throws IllegalArgumentException if vmid is less than 100
     */
    public ProxmoxRequest<PveTask> create(int vmid, PveQemuCreateOptions options) {
        if (vmid < 100) {
            throw new IllegalArgumentException("VMID must be >= 100");
        }

        PveQemuCreateOptions effectiveOptions = options != null ? options : PveQemuCreateOptions.builder();

        return new ProxmoxRequest<>(() ->
            client.post("nodes/" + nodeName + "/qemu")
                .params(effectiveOptions.toParams(vmid))
                .transformer(new TaskResponseTransformer())
                .execute(PveTask.class)
        );
    }
}

