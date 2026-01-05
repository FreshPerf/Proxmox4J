package fr.freshperf.proxmox4j.entities.nodes.node.lxc;

import com.google.gson.reflect.TypeToken;
import fr.freshperf.proxmox4j.request.ProxmoxHttpClient;
import fr.freshperf.proxmox4j.request.ProxmoxRequest;

import java.util.List;

public record PveLxc(ProxmoxHttpClient client, String nodeName) {

    /**
     * Lists all LXC containers on this node
     */
    public ProxmoxRequest<List<PveLxcIndex>> getIndex() {
        return new ProxmoxRequest<>(() -> 
            client.get("nodes/" + nodeName + "/lxc").executeList(new TypeToken<List<PveLxcIndex>>(){})
        );
    }

    /**
     * Gets a specific container by VMID
     */
    public PveLxcContainer get(int vmid) {
        if (vmid <= 0) {
            throw new IllegalArgumentException("VMID must be a positive integer");
        }
        return new PveLxcContainer(client, nodeName, vmid);
    }
}

