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

    public Map<String, Object> toParams(String cidr) {
        Map<String, Object> params = new HashMap<>();
        params.put("cidr", cidr);
        put(params, "comment", comment);
        putBool(params, "nomatch", nomatch);
        return params;
    }

    public PveQemuFirewallIpSetMemberCreateOptions comment(String comment) { this.comment = comment; return this; }
    public PveQemuFirewallIpSetMemberCreateOptions nomatch(Boolean nomatch) { this.nomatch = nomatch; return this; }

    private static void put(Map<String, Object> params, String key, String value) {
        if (value != null && !value.isBlank()) {
            params.put(key, value);
        }
    }

    private static void putBool(Map<String, Object> params, String key, Boolean value) {
        if (value != null) {
            params.put(key, value ? "1" : "0");
        }
    }
}
