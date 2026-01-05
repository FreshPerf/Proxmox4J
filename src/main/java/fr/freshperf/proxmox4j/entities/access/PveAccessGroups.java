package fr.freshperf.proxmox4j.entities.access;

import com.google.gson.reflect.TypeToken;
import fr.freshperf.proxmox4j.request.ProxmoxHttpClient;
import fr.freshperf.proxmox4j.request.ProxmoxRequest;

import java.util.List;

public record PveAccessGroups(ProxmoxHttpClient httpClient) {

    /**
     * Lists all groups
     */
    public ProxmoxRequest<List<PveAccessGroup>> getIndex() {
        return new ProxmoxRequest<>(() -> 
            httpClient.get("access/groups").executeList(new TypeToken<List<PveAccessGroup>>(){})
        );
    }

    /**
     * Gets a specific group
     */
    public ProxmoxRequest<PveAccessGroup> get(String groupid) {
        if (groupid == null || groupid.isEmpty()) {
            throw new IllegalArgumentException("Group ID cannot be null or empty");
        }
        return new ProxmoxRequest<>(() -> 
            httpClient.get("access/groups/" + groupid).execute(PveAccessGroup.class)
        );
    }
}

