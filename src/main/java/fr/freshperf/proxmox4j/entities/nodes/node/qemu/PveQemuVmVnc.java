package fr.freshperf.proxmox4j.entities.nodes.node.qemu;

import com.google.gson.JsonElement;
import fr.freshperf.proxmox4j.Proxmox;
import fr.freshperf.proxmox4j.request.ProxmoxHttpClient;
import fr.freshperf.proxmox4j.request.ProxmoxRequest;
import fr.freshperf.proxmox4j.util.ProxmoxApiBaseUrlBuilder;

public class PveQemuVmVnc {

    private final ProxmoxHttpClient client;
    private final String nodeName;
    private final int vmid;

    public PveQemuVmVnc(ProxmoxHttpClient client, String nodeName, int vmid) {
        this.client = client;
        this.nodeName = nodeName;
        this.vmid = vmid;

    }

    public ProxmoxRequest<PveQemuVmVncProxy> getVncProxy() {
        return new ProxmoxRequest<>(() ->
                client.post("nodes/" + nodeName + "/qemu/" + vmid + "/vncproxy")
                        .execute(PveQemuVmVncProxy.class)
        );
    }

    public ProxmoxRequest<JsonElement> openVncWebsocket(PveQemuVmVncProxy vmVncProxy) {
        return new ProxmoxRequest<JsonElement>(() ->
                client.get("nodes/" + nodeName + "/qemu/" + vmid + "/vncwebsocket")
                        .param("port", vmVncProxy.getPort())
                        .param("vncticket", vmVncProxy.getTicket())
                        .execute(JsonElement.class)
        );
    }

    public String getConsoleUrl(Proxmox proxmox, PveQemuVmVncProxy vmVncProxy) {
        return ProxmoxApiBaseUrlBuilder.buildConsoleUrl(proxmox, nodeName, vmid, vmVncProxy);
    }



}
