package fr.freshperf.proxmox4j.entities.nodes.node.qemu.firewall.ipset;

import java.util.HashMap;
import java.util.Map;

/**
 * Options for adding a member to an IP set (POST /nodes/{node}/qemu/{vmid}/firewall/ipset/{name}).
 */
public class PveQemuFirewallIpSetMemberCreateOptions {

    private String comment;
    private Boolean nomatch;

    public static PveQemuFirewallIpSetMemberCreateOptions builder() {
        return new PveQemuFirewallIpSetMemberCreateOptions();
    }

    public Map<String, String> toParams(String cidr) {
        Map<String, String> params = new HashMap<>();
        params.put("cidr", cidr);
        put(params, "comment", comment);
        putBool(params, "nomatch", nomatch);
        return params;
    }

    public PveQemuFirewallIpSetMemberCreateOptions comment(String comment) { this.comment = comment; return this; }
    public PveQemuFirewallIpSetMemberCreateOptions nomatch(Boolean nomatch) { this.nomatch = nomatch; return this; }

    private static void put(Map<String, String> params, String key, String value) {
        if (value != null && !value.isBlank()) {
            params.put(key, value);
        }
    }

    private static void putBool(Map<String, String> params, String key, Boolean value) {
        if (value != null) {
            params.put(key, value ? "1" : "0");
        }
    }
}
