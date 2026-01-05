package fr.freshperf.proxmox4j.entities.nodes.node.qemu.firewall.ipset;

import java.util.HashMap;
import java.util.Map;

/**
 * Options for creating/renaming an IP set (POST /nodes/{node}/qemu/{vmid}/firewall/ipset).
 */
public class PveQemuFirewallIpSetCreateOptions {

    private String comment;
    private String digest;
    private String rename;

    public static PveQemuFirewallIpSetCreateOptions builder() {
        return new PveQemuFirewallIpSetCreateOptions();
    }

    public Map<String, String> toParams(String name) {
        Map<String, String> params = new HashMap<>();
        params.put("name", name);
        put(params, "comment", comment);
        put(params, "digest", digest);
        put(params, "rename", rename);
        return params;
    }

    public PveQemuFirewallIpSetCreateOptions comment(String comment) { this.comment = comment; return this; }
    public PveQemuFirewallIpSetCreateOptions digest(String digest) { this.digest = digest; return this; }
    public PveQemuFirewallIpSetCreateOptions rename(String rename) { this.rename = rename; return this; }

    private static void put(Map<String, String> params, String key, String value) {
        if (value != null && !value.isBlank()) {
            params.put(key, value);
        }
    }
}
