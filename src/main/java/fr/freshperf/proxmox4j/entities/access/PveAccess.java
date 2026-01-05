package fr.freshperf.proxmox4j.entities.access;

import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import fr.freshperf.proxmox4j.request.ProxmoxHttpClient;
import fr.freshperf.proxmox4j.request.ProxmoxRequest;

import java.util.List;

public record PveAccess(ProxmoxHttpClient httpClient) {

    /**
     * Gets the access index
     */
    public ProxmoxRequest<List<PveAccessIndex>> getIndex() {
        return new ProxmoxRequest<>(() -> 
            httpClient.get("access/").executeList(new TypeToken<List<PveAccessIndex>>(){})
        );
    }

    /**
     * Gets authentication ticket (for username/password authentication)
     * @param username Username
     * @param password Password
     * @param realm Authentication realm (default: pam)
     */
    public ProxmoxRequest<PveAccessTicket> getTicket(String username, String password, String realm) {
        return new ProxmoxRequest<>(() -> {
            var builder = httpClient.post("access/ticket")
                .param("username", username)
                .param("password", password);
            if (realm != null && !realm.isEmpty()) {
                builder.param("realm", realm);
            }
            return builder.execute(PveAccessTicket.class);
        });
    }

    /**
     * Gets authentication ticket with default PAM realm
     */
    public ProxmoxRequest<PveAccessTicket> getTicket(String username, String password) {
        return getTicket(username, password, "pam");
    }

    /**
     * Gets users
     */
    public PveAccessUsers getUsers() {
        return new PveAccessUsers(httpClient);
    }

    /**
     * Gets groups
     */
    public PveAccessGroups getGroups() {
        return new PveAccessGroups(httpClient);
    }

    /**
     * Gets roles
     */
    public PveAccessRoles getRoles() {
        return new PveAccessRoles(httpClient);
    }

    /**
     * Gets ACL (Access Control List)
     */
    public ProxmoxRequest<List<PveAccessAcl>> getAcl() {
        return new ProxmoxRequest<>(() -> 
            httpClient.get("access/acl").executeList(new TypeToken<List<PveAccessAcl>>(){})
        );
    }

    /**
     * Gets domains (authentication realms)
     */
    public ProxmoxRequest<List<PveAccessDomain>> getDomains() {
        return new ProxmoxRequest<>(() -> 
            httpClient.get("access/domains").executeList(new TypeToken<List<PveAccessDomain>>(){})
        );
    }
}

