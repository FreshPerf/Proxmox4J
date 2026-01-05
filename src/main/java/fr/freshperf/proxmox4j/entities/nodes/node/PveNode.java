package fr.freshperf.proxmox4j.entities.nodes.node;

import com.google.gson.reflect.TypeToken;
import fr.freshperf.proxmox4j.entities.nodes.node.qemu.PveQemu;
import fr.freshperf.proxmox4j.entities.nodes.node.lxc.PveLxc;
import fr.freshperf.proxmox4j.entities.nodes.node.storage.PveStorage;
import fr.freshperf.proxmox4j.request.ProxmoxHttpClient;
import fr.freshperf.proxmox4j.request.ProxmoxRequest;

import java.util.List;

public record PveNode(ProxmoxHttpClient client, String nodeName) {

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
        return new PveQemu(client, nodeName);
    }

    public PveLxc getLxc() {
        return new PveLxc(client, nodeName);
    }

    public PveStorage getStorage() {
        return new PveStorage(client, nodeName);
    }
}

