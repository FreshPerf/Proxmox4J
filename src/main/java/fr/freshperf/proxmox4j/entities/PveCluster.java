package fr.freshperf.proxmox4j.entities;

import fr.freshperf.proxmox4j.entities.cluster.PveClusterStatus;
import fr.freshperf.proxmox4j.request.ProxmoxHttpClient;
import fr.freshperf.proxmox4j.throwable.ProxmoxAPIError;

public class PveCluster {

    private final ProxmoxHttpClient httpClient;

    public PveCluster(ProxmoxHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public PveClusterStatus getStatus() throws ProxmoxAPIError {
        try {
            return httpClient.get("cluster/status", PveClusterStatus.class);
        } catch (Exception e) {
            throw new ProxmoxAPIError(e);
        }
    }

}
