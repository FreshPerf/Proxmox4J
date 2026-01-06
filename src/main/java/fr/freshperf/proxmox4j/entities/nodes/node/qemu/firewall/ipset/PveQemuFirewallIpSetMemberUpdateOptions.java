package fr.freshperf.proxmox4j.entities.nodes.node.qemu.firewall.ipset;

import java.util.HashMap;
import java.util.Map;

/**
 * Options for updating a firewall IP set member (/firewall/ipset/{name}/{cidr}, PUT).
 */
public class PveQemuFirewallIpSetMemberUpdateOptions {

    private String comment;
    private Boolean nomatch;
    private String digest;

    public static PveQemuFirewallIpSetMemberUpdateOptions builder() {
        return new PveQemuFirewallIpSetMemberUpdateOptions();
    }

    public Map<String, Object> toParams(String cidr) {
        Map<String, Object> params = new HashMap<>();
        params.put("cidr", cidr);
        put(params, "comment", comment);
        putBool(params, "nomatch", nomatch);
        put(params, "digest", digest);
        return params;
    }

    public PveQemuFirewallIpSetMemberUpdateOptions comment(String comment) { this.comment = comment; return this; }
    public PveQemuFirewallIpSetMemberUpdateOptions nomatch(Boolean nomatch) { this.nomatch = nomatch; return this; }
    public PveQemuFirewallIpSetMemberUpdateOptions digest(String digest) { this.digest = digest; return this; }

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
