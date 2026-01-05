package fr.freshperf.proxmox4j.entities.nodes.node.storage;

import com.google.gson.reflect.TypeToken;
import fr.freshperf.proxmox4j.request.ProxmoxHttpClient;
import fr.freshperf.proxmox4j.request.ProxmoxRequest;

import java.util.List;

public record PveStorageItem(ProxmoxHttpClient client, String nodeName, String storageId) {

    /**
     * Gets the storage status
     */
    public ProxmoxRequest<PveStorageStatus> getStatus() {
        return new ProxmoxRequest<>(() -> 
            client.get("nodes/" + nodeName + "/storage/" + storageId + "/status")
                .execute(PveStorageStatus.class)
        );
    }

    /**
     * Lists content on this storage
     */
    public ProxmoxRequest<List<PveStorageContent>> getContent() {
        return new ProxmoxRequest<>(() -> 
            client.get("nodes/" + nodeName + "/storage/" + storageId + "/content")
                .executeList(new TypeToken<List<PveStorageContent>>(){})
        );
    }

    /**
     * Gets RRD statistics (graph data)
     */
    public ProxmoxRequest<PveStorageRrd> getRrd() {
        return new ProxmoxRequest<>(() -> 
            client.get("nodes/" + nodeName + "/storage/" + storageId + "/rrd")
                .execute(PveStorageRrd.class)
        );
    }
}

