package fr.freshperf.proxmox4j;

import fr.freshperf.proxmox4j.entities.PveCluster;
import fr.freshperf.proxmox4j.request.ProxmoxHttpClient;

public class Proxmox {

    private final ProxmoxHttpClient httpClient;

    private final PveCluster pveCluster;

    private Proxmox(String host, int port, String apikey, boolean verifySSL) {
        this.httpClient = new ProxmoxHttpClient("https://" + host + ":" + port + "/api2/json/", apikey, verifySSL);

        this.pveCluster = new PveCluster(httpClient);
    }

    public static Proxmox create(String host, int port, String apikey, boolean verifySSL) {
        return new Proxmox(host, port, apikey, verifySSL);
    }

    public PveCluster getCluster() {
        return pveCluster;
    }

}
