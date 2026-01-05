package fr.freshperf.proxmox4j.entities.access;

import com.google.gson.reflect.TypeToken;
import fr.freshperf.proxmox4j.request.ProxmoxHttpClient;
import fr.freshperf.proxmox4j.request.ProxmoxRequest;

import java.util.List;

public record PveAccessRoles(ProxmoxHttpClient httpClient) {

    /**
     * Lists all roles
     */
    public ProxmoxRequest<List<PveAccessRole>> getIndex() {
        return new ProxmoxRequest<>(() -> 
            httpClient.get("access/roles").executeList(new TypeToken<List<PveAccessRole>>(){})
        );
    }

    /**
     * Gets a specific role
     */
    public ProxmoxRequest<PveAccessRole> get(String roleid) {
        if (roleid == null || roleid.isEmpty()) {
            throw new IllegalArgumentException("Role ID cannot be null or empty");
        }
        return new ProxmoxRequest<>(() -> 
            httpClient.get("access/roles/" + roleid).execute(PveAccessRole.class)
        );
    }
}

