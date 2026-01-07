package fr.freshperf.proxmox4j.entities.nodes.node.qemu.firewall;

import fr.freshperf.proxmox4j.entities.PveTask;
import fr.freshperf.proxmox4j.entities.nodes.node.qemu.firewall.ipset.PveQemuFirewallIpSet;
import fr.freshperf.proxmox4j.request.ProxmoxHttpClient;
import fr.freshperf.proxmox4j.request.ProxmoxRequest;
import fr.freshperf.proxmox4j.request.TaskResponseTransformer;

/**
 * Facade to manage firewall options and IP sets for a QEMU VM.
 */
public record PveQemuFirewall (ProxmoxHttpClient client, String nodeName, int vmid) {

    public PveQemuFirewallIpSet getIpSet() {
        return new PveQemuFirewallIpSet(client, nodeName, vmid);
    }

    private String path(String suffix) {
        return "nodes/" + nodeName + "/qemu/" + vmid + suffix;
    }

    // Options
    /**
     * Gets the firewall options for this VM (/firewall/options, GET).
     */
    public ProxmoxRequest<PveQemuFirewallOptions> getOptions() {
        return new ProxmoxRequest<>(() ->
            client.get(path("/firewall/options"))
                .execute(PveQemuFirewallOptions.class)
        );
    }

    /**
     * Updates the firewall options for this VM (/firewall/options, PUT).
     */
    public ProxmoxRequest<PveTask> updateOptions(PveQemuFirewallOptionsUpdate options) {
        if (options == null) {
            throw new IllegalArgumentException("options cannot be null");
        }
        return new ProxmoxRequest<>(() ->
            client.put(path("/firewall/options"))
                .params(options.toParams())
                .transformer(new TaskResponseTransformer())
                .execute(PveTask.class)
        );
    }
}
