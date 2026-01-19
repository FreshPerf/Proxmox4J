package fr.freshperf.pve4j.entities.nodes.node.qemu.firewall.rules;

import fr.freshperf.pve4j.entities.options.ParamsConvertible;
import fr.freshperf.pve4j.util.ParamsHelpers;

import java.util.Map;

/**
 * Options for creating a firewall rule.
 * Use the builder pattern to configure rule settings.
 */
public class PveFirewallRuleCreateOptions implements ParamsConvertible {

    private String type;
    private String action;
    private Boolean enable;
    private String source;
    private String dest;
    private String proto;
    private String sport;
    private String dport;
    private String comment;
    private String macro;
    private String iface;
    private String log;
    private Integer pos;

    /**
     * Creates a new builder for firewall rule create options.
     *
     * @return a new PveFirewallRuleCreateOptions instance
     */
    public static PveFirewallRuleCreateOptions builder() {
        return new PveFirewallRuleCreateOptions();
    }

    @Override
    public void populateParams(Map<String, Object> params) {
        ParamsHelpers.put(params, "type", type);
        ParamsHelpers.put(params, "action", action);
        ParamsHelpers.putBool(params, "enable", enable);
        ParamsHelpers.put(params, "source", source);
        ParamsHelpers.put(params, "dest", dest);
        ParamsHelpers.put(params, "proto", proto);
        ParamsHelpers.put(params, "sport", sport);
        ParamsHelpers.put(params, "dport", dport);
        ParamsHelpers.put(params, "comment", comment);
        ParamsHelpers.put(params, "macro", macro);
        ParamsHelpers.put(params, "iface", iface);
        ParamsHelpers.put(params, "log", log);
        ParamsHelpers.putInt(params, "pos", pos);
    }

    /**
     * Sets the rule type (REQUIRED).
     *
     * @param type "in" (incoming), "out" (outgoing), or "group"
     * @return this instance for method chaining
     */
    public PveFirewallRuleCreateOptions type(String type) {
        this.type = type;
        return this;
    }

    /**
     * Sets the rule action (REQUIRED).
     *
     * @param action "ACCEPT", "DROP", or "REJECT"
     * @return this instance for method chaining
     */
    public PveFirewallRuleCreateOptions action(String action) {
        this.action = action;
        return this;
    }

    /**
     * Sets whether the rule is enabled.
     *
     * @param enable true to enable
     * @return this instance for method chaining
     */
    public PveFirewallRuleCreateOptions enable(Boolean enable) {
        this.enable = enable;
        return this;
    }

    /**
     * Sets the source address/network.
     *
     * @param source IP, CIDR, or alias (e.g., "192.168.1.0/24")
     * @return this instance for method chaining
     */
    public PveFirewallRuleCreateOptions source(String source) {
        this.source = source;
        return this;
    }

    /**
     * Sets the destination address/network.
     *
     * @param dest IP, CIDR, or alias
     * @return this instance for method chaining
     */
    public PveFirewallRuleCreateOptions dest(String dest) {
        this.dest = dest;
        return this;
    }

    /**
     * Sets the protocol.
     *
     * @param proto "tcp", "udp", "icmp", etc.
     * @return this instance for method chaining
     */
    public PveFirewallRuleCreateOptions proto(String proto) {
        this.proto = proto;
        return this;
    }

    /**
     * Sets the source port(s).
     *
     * @param sport port number or range (e.g., "80", "1024:65535")
     * @return this instance for method chaining
     */
    public PveFirewallRuleCreateOptions sport(String sport) {
        this.sport = sport;
        return this;
    }

    /**
     * Sets the destination port(s).
     *
     * @param dport port number or range (e.g., "443", "8000:8080")
     * @return this instance for method chaining
     */
    public PveFirewallRuleCreateOptions dport(String dport) {
        this.dport = dport;
        return this;
    }

    /**
     * Sets the rule comment.
     *
     * @param comment description of the rule
     * @return this instance for method chaining
     */
    public PveFirewallRuleCreateOptions comment(String comment) {
        this.comment = comment;
        return this;
    }

    /**
     * Sets a predefined macro.
     *
     * @param macro macro name (e.g., "SSH", "HTTP", "Ping")
     * @return this instance for method chaining
     */
    public PveFirewallRuleCreateOptions macro(String macro) {
        this.macro = macro;
        return this;
    }

    /**
     * Sets the network interface.
     *
     * @param iface interface name (e.g., "net0")
     * @return this instance for method chaining
     */
    public PveFirewallRuleCreateOptions iface(String iface) {
        this.iface = iface;
        return this;
    }

    /**
     * Sets the log level.
     *
     * @param log "nolog", "info", "warning", "err", "crit", "alert", "emerg"
     * @return this instance for method chaining
     */
    public PveFirewallRuleCreateOptions log(String log) {
        this.log = log;
        return this;
    }

    /**
     * Sets the rule position.
     *
     * @param pos position in the rule list (0-based)
     * @return this instance for method chaining
     */
    public PveFirewallRuleCreateOptions pos(Integer pos) {
        this.pos = pos;
        return this;
    }

    /**
     * Builds and returns the options instance.
     *
     * @return this instance
     */
    public PveFirewallRuleCreateOptions build() {
        return this;
    }
}

