package fr.freshperf.proxmox4j.entities.nodes.node.qemu;

import com.google.gson.JsonObject;
import fr.freshperf.proxmox4j.request.ProxmoxHttpClient;
import fr.freshperf.proxmox4j.request.ProxmoxRequest;

import java.util.HashMap;
import java.util.Map;

public record PveQemuVm(ProxmoxHttpClient client, String nodeName, int vmid) {

    /**
     * Gets the current VM status
     */
    public ProxmoxRequest<PveQemuStatus> getStatus() {
        return new ProxmoxRequest<>(() -> 
            client.get("nodes/" + nodeName + "/qemu/" + vmid + "/status/current")
                .execute(PveQemuStatus.class)
        );
    }

    /**
     * Gets the VM configuration
     */
    public ProxmoxRequest<PveQemuConfig> getConfig() {
        return new ProxmoxRequest<>(() -> 
            client.get("nodes/" + nodeName + "/qemu/" + vmid + "/config")
                .execute(PveQemuConfig.class)
        );
    }

    /**
     * Starts the VM
     */
    public ProxmoxRequest<JsonObject> start() {
        return new ProxmoxRequest<>(() -> 
            client.post("nodes/" + nodeName + "/qemu/" + vmid + "/status/start")
                .execute()
        );
    }

    /**
     * Stops the VM
     */
    public ProxmoxRequest<JsonObject> stop() {
        return new ProxmoxRequest<>(() -> 
            client.post("nodes/" + nodeName + "/qemu/" + vmid + "/status/stop")
                .execute()
        );
    }

    /**
     * Shuts down the VM
     */
    public ProxmoxRequest<JsonObject> shutdown() {
        return new ProxmoxRequest<>(() -> 
            client.post("nodes/" + nodeName + "/qemu/" + vmid + "/status/shutdown")
                .execute()
        );
    }

    /**
     * Resets the VM
     */
    public ProxmoxRequest<JsonObject> reset() {
        return new ProxmoxRequest<>(() -> 
            client.post("nodes/" + nodeName + "/qemu/" + vmid + "/status/reset")
                .execute()
        );
    }

    /**
     * Suspends the VM
     */
    public ProxmoxRequest<JsonObject> suspend() {
        return new ProxmoxRequest<>(() -> 
            client.post("nodes/" + nodeName + "/qemu/" + vmid + "/status/suspend")
                .execute()
        );
    }

    /**
     * Resumes the VM
     */
    public ProxmoxRequest<JsonObject> resume() {
        return new ProxmoxRequest<>(() -> 
            client.post("nodes/" + nodeName + "/qemu/" + vmid + "/status/resume")
                .execute()
        );
    }

    /**
     * Reboots the VM
     */
    public ProxmoxRequest<JsonObject> reboot() {
        return new ProxmoxRequest<>(() -> 
            client.post("nodes/" + nodeName + "/qemu/" + vmid + "/status/reboot")
                .execute()
        );
    }

    /**
     * Deletes the VM
     */
    public ProxmoxRequest<JsonObject> delete() {
        return new ProxmoxRequest<>(() -> 
            client.delete("nodes/" + nodeName + "/qemu/" + vmid)
                .execute()
        );
    }
}

