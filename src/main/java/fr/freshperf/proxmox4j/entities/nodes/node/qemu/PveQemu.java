package fr.freshperf.proxmox4j.entities.nodes.node.qemu;

import com.google.gson.reflect.TypeToken;
import fr.freshperf.proxmox4j.request.ProxmoxHttpClient;
import fr.freshperf.proxmox4j.request.ProxmoxRequest;

import java.util.List;

public record PveQemu(ProxmoxHttpClient client, String nodeName) {

    /**
     * Lists all VMs on this node
     */
    public ProxmoxRequest<List<PveQemuIndex>> getIndex() {
        return new ProxmoxRequest<>(() -> 
            client.get("nodes/" + nodeName + "/qemu").executeList(new TypeToken<>() {
            })
        );
    }

    /**
     * Gets a specific VM by VMID
     */
    public PveQemuVm get(int vmid) {
        if (vmid <= 0) {
            throw new IllegalArgumentException("VMID must be a positive integer");
        }
        return new PveQemuVm(client, nodeName, vmid);
    }
}

