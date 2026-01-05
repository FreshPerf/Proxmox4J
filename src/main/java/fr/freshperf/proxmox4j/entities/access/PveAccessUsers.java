package fr.freshperf.proxmox4j.entities.access;

import com.google.gson.reflect.TypeToken;
import fr.freshperf.proxmox4j.request.ProxmoxHttpClient;
import fr.freshperf.proxmox4j.request.ProxmoxRequest;

import java.util.List;

public record PveAccessUsers(ProxmoxHttpClient httpClient) {

    /**
     * Lists all users
     */
    public ProxmoxRequest<List<PveAccessUser>> getIndex() {
        return new ProxmoxRequest<>(() -> 
            httpClient.get("access/users").executeList(new TypeToken<List<PveAccessUser>>(){})
        );
    }

    /**
     * Gets a specific user
     */
    public ProxmoxRequest<PveAccessUser> get(String userid) {
        if (userid == null || userid.isEmpty()) {
            throw new IllegalArgumentException("User ID cannot be null or empty");
        }
        return new ProxmoxRequest<>(() -> 
            httpClient.get("access/users/" + userid).execute(PveAccessUser.class)
        );
    }
}

