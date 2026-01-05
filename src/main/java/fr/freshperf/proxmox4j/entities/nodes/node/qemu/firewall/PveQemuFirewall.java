package fr.freshperf.proxmox4j.entities.nodes.node.qemu.firewall;

import com.google.gson.reflect.TypeToken;
import fr.freshperf.proxmox4j.entities.PveTask;
import fr.freshperf.proxmox4j.request.ProxmoxHttpClient;
import fr.freshperf.proxmox4j.request.ProxmoxRequest;
import fr.freshperf.proxmox4j.request.TaskResponseTransformer;

import java.util.List;
import java.util.Map;

/**
 * Facade to manage firewall options and IP sets for a QEMU VM.
 */
public class PveQemuFirewall {

    private final ProxmoxHttpClient client;
    private final String nodeName;
    private final int vmid;

    public PveQemuFirewall(ProxmoxHttpClient client, String nodeName, int vmid) {
        this.client = client;
        this.nodeName = nodeName;
        this.vmid = vmid;
    }

    private String path(String suffix) {
        return "nodes/" + nodeName + "/qemu/" + vmid + suffix;
    }

    private static void requireNotBlank(String value, String message) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(message);
        }
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

    /**
     * Lists all firewall IP sets for this VM (/firewall/ipset, GET).
     */
    public ProxmoxRequest<List<PveQemuFirewallIpSetEntry>> listIpSets() {
        return new ProxmoxRequest<>(() ->
            client.get(path("/firewall/ipset"))
                .executeList(new TypeToken<List<PveQemuFirewallIpSetEntry>>() {})
        );
    }

    /**
     * Creates a firewall IP set (/firewall/ipset, POST).
     */
    public ProxmoxRequest<PveTask> createIpSet(String name, PveQemuFirewallIpSetCreateOptions options) {
        requireNotBlank(name, "name cannot be null or blank");
        Map<String, String> params = options != null ? options.toParams(name) : Map.of("name", name);
        return new ProxmoxRequest<>(() ->
            client.post(path("/firewall/ipset"))
                .params(params)
                .transformer(new TaskResponseTransformer())
                .execute(PveTask.class)
        );
    }

    /**
     * Deletes a firewall IP set, optionally forcing member removal (/firewall/ipset/{name}, DELETE).
     */
    public ProxmoxRequest<PveTask> deleteIpSet(String name, Boolean force) {
        requireNotBlank(name, "name cannot be null or blank");
        Map<String, String> params = force != null ? Map.of("force", force ? "1" : "0") : Map.of();
        return new ProxmoxRequest<>(() ->
                client.delete(path("/firewall/ipset/" + name))
                        .params(params)
                        .transformer(new TaskResponseTransformer())
                        .execute(PveTask.class)
        );
    }

    /**
     * Lists members of a firewall IP set (/firewall/ipset/{name}, GET).
     */
    public ProxmoxRequest<List<PveQemuFirewallIpSetMember>> listIpSetMembers(String name) {
        requireNotBlank(name, "name cannot be null or blank");
        return new ProxmoxRequest<>(() ->
            client.get(path("/firewall/ipset/" + name))
                .executeList(new TypeToken<List<PveQemuFirewallIpSetMember>>() {})
        );
    }

    /**
     * Adds a CIDR entry to a firewall IP set (/firewall/ipset/{name}, POST).
     */
    public ProxmoxRequest<PveTask> addIpSetMember(String name, String cidr, PveQemuFirewallIpSetMemberCreateOptions options) {
        requireNotBlank(name, "name cannot be null or blank");
        requireNotBlank(cidr, "cidr cannot be null or blank");
        Map<String, String> params = options != null ? options.toParams(cidr) : Map.of("cidr", cidr);
        return new ProxmoxRequest<>(() ->
            client.post(path("/firewall/ipset/" + name))
                .params(params)
                .transformer(new TaskResponseTransformer())
                .execute(PveTask.class)
        );
    }

    /**
     * Gets a specific IP set member (/firewall/ipset/{name}/{cidr}, GET).
     */
    public ProxmoxRequest<PveQemuFirewallIpSetMember> getIpSetMember(String name, String cidr) {
        requireNotBlank(name, "name cannot be null or blank");
        requireNotBlank(cidr, "cidr cannot be null or blank");
        return new ProxmoxRequest<>(() ->
            client.get(path("/firewall/ipset/" + name + "/" + cidr))
                .execute(PveQemuFirewallIpSetMember.class)
        );
    }

    /**
     * Updates a specific IP set member (/firewall/ipset/{name}/{cidr}, PUT).
     */
    public ProxmoxRequest<PveTask> updateIpSetMember(String name, String cidr, PveQemuFirewallIpSetMemberUpdateOptions options) {
        requireNotBlank(name, "name cannot be null or blank");
        requireNotBlank(cidr, "cidr cannot be null or blank");
        if (options == null) {
            throw new IllegalArgumentException("options cannot be null");
        }
        return new ProxmoxRequest<>(() ->
            client.put(path("/firewall/ipset/" + name + "/" + cidr))
                .params(options.toParams(cidr))
                .transformer(new TaskResponseTransformer())
                .execute(PveTask.class)
        );
    }

    /**
     * Deletes a specific IP set member (/firewall/ipset/{name}/{cidr}, DELETE).
     */
    public ProxmoxRequest<PveTask> deleteIpSetMember(String name, String cidr, String digest) {
        requireNotBlank(name, "name cannot be null or blank");
        requireNotBlank(cidr, "cidr cannot be null or blank");
        Map<String, String> params = digest != null && !digest.isBlank() ? Map.of("digest", digest) : Map.of();
        return new ProxmoxRequest<>(() ->
            client.delete(path("/firewall/ipset/" + name + "/" + cidr))
                .params(params)
                .transformer(new TaskResponseTransformer())
                .execute(PveTask.class)
        );
    }
}
