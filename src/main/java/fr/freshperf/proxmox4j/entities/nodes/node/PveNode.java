package fr.freshperf.proxmox4j.entities.nodes.node;

import com.google.gson.reflect.TypeToken;
import fr.freshperf.proxmox4j.entities.nodes.PveNodes;
import fr.freshperf.proxmox4j.entities.nodes.node.qemu.PveQemu;
import fr.freshperf.proxmox4j.entities.nodes.node.lxc.PveLxc;
import fr.freshperf.proxmox4j.entities.nodes.node.storage.PveStorage;
import fr.freshperf.proxmox4j.request.ProxmoxHttpClient;
import fr.freshperf.proxmox4j.request.ProxmoxRequest;

import java.util.List;

public class PveNode {

    private final ProxmoxHttpClient client;
    private final String nodeName;

    private final PveQemu pveQemu;
    private final PveLxc pveLxc;
    private final PveStorage pveStorage;

    public PveNode(ProxmoxHttpClient client, String nodeName) {
        this.client = client;
        this.nodeName = nodeName;

        this.pveQemu = new PveQemu(client, nodeName);
        this.pveLxc = new PveLxc(client, nodeName);
        this.pveStorage = new PveStorage(client, nodeName);
    }

    public ProxmoxRequest<List<PveNodeIndex>> getIndex() {
        return new ProxmoxRequest<>(() -> 
            client.get("nodes/" + nodeName).executeList(new TypeToken<List<PveNodeIndex>>(){})
        );
    }

    public ProxmoxRequest<PveNodeStatus> getStatus() {
        return new ProxmoxRequest<>(() -> 
            client.get("nodes/" + nodeName + "/status").execute(PveNodeStatus.class)
        );
    }

    public ProxmoxRequest<PveNodeVersion> getVersion() {
        return new ProxmoxRequest<>(() -> 
            client.get("nodes/" + nodeName + "/version").execute(PveNodeVersion.class)
        );
    }

    public PveQemu getQemu() {
        return pveQemu;
    }

    public PveLxc getLxc() {
        return pveLxc;
    }

    public PveStorage getStorage() {
        return pveStorage;
    }
}

