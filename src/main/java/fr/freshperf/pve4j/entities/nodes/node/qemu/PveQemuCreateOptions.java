package fr.freshperf.pve4j.entities.nodes.node.qemu;

import fr.freshperf.pve4j.entities.options.ParameterizedParamsConvertible;
import fr.freshperf.pve4j.util.ParamsHelpers;

import java.util.HashMap;
import java.util.Map;

/**
 * Options for creating a QEMU VM.
 * Use the builder pattern to configure VM settings.
 */
public class PveQemuCreateOptions implements ParameterizedParamsConvertible<Integer> {

    private String name;
    private String description;
    private Integer memory;
    private Integer cores;
    private Integer sockets;
    private String ostype;
    private String scsihw;
    private String boot;
    private String cdrom;
    private String machine;
    private String bios;
    private String cpu;
    private Integer balloon;
    private Boolean onboot;
    private Boolean agent;
    private String pool;
    private Boolean start;
    private Boolean template;
    
    // Disk configurations (scsi0, virtio0, etc.)
    private final Map<String, String> disks = new HashMap<>();
    
    // Network configurations (net0, net1, etc.)
    private final Map<String, String> networks = new HashMap<>();
    
    // IDE configurations (ide0, ide1, etc.)
    private final Map<String, String> ide = new HashMap<>();

    /**
     * Creates a new builder for VM create options.
     *
     * @return a new PveQemuCreateOptions instance
     */
    public static PveQemuCreateOptions builder() {
        return new PveQemuCreateOptions();
    }

    @Override
    public void addRequiredParam(Map<String, Object> params, Integer vmid) {
        params.put("vmid", String.valueOf(vmid));
    }

    @Override
    public void populateParams(Map<String, Object> params) {
        ParamsHelpers.put(params, "name", name);
        ParamsHelpers.put(params, "description", description);
        ParamsHelpers.putInt(params, "memory", memory);
        ParamsHelpers.putInt(params, "cores", cores);
        ParamsHelpers.putInt(params, "sockets", sockets);
        ParamsHelpers.put(params, "ostype", ostype);
        ParamsHelpers.put(params, "scsihw", scsihw);
        ParamsHelpers.put(params, "boot", boot);
        ParamsHelpers.put(params, "cdrom", cdrom);
        ParamsHelpers.put(params, "machine", machine);
        ParamsHelpers.put(params, "bios", bios);
        ParamsHelpers.put(params, "cpu", cpu);
        ParamsHelpers.putInt(params, "balloon", balloon);
        ParamsHelpers.putBool(params, "onboot", onboot);
        ParamsHelpers.putBool(params, "agent", agent);
        ParamsHelpers.put(params, "pool", pool);
        ParamsHelpers.putBool(params, "start", start);
        ParamsHelpers.putBool(params, "template", template);
        
        // Add disk configurations
        for (Map.Entry<String, String> entry : disks.entrySet()) {
            params.put(entry.getKey(), entry.getValue());
        }
        
        // Add network configurations
        for (Map.Entry<String, String> entry : networks.entrySet()) {
            params.put(entry.getKey(), entry.getValue());
        }
        
        // Add IDE configurations
        for (Map.Entry<String, String> entry : ide.entrySet()) {
            params.put(entry.getKey(), entry.getValue());
        }
    }

    /** Sets the VM name. */
    public PveQemuCreateOptions name(String name) {
        this.name = name;
        return this;
    }

    /** Sets the VM description. */
    public PveQemuCreateOptions description(String description) {
        this.description = description;
        return this;
    }

    /** Sets the memory in MB. */
    public PveQemuCreateOptions memory(Integer memory) {
        this.memory = memory;
        return this;
    }

    /** Sets the number of CPU cores. */
    public PveQemuCreateOptions cores(Integer cores) {
        this.cores = cores;
        return this;
    }

    /** Sets the number of CPU sockets. */
    public PveQemuCreateOptions sockets(Integer sockets) {
        this.sockets = sockets;
        return this;
    }

    /** Sets the OS type (e.g., "l26" for Linux 2.6+, "win10" for Windows 10). */
    public PveQemuCreateOptions ostype(String ostype) {
        this.ostype = ostype;
        return this;
    }

    /** Sets the SCSI hardware type (e.g., "virtio-scsi-pci"). */
    public PveQemuCreateOptions scsihw(String scsihw) {
        this.scsihw = scsihw;
        return this;
    }

    /** Sets the boot order (e.g., "order=scsi0;ide2;net0"). */
    public PveQemuCreateOptions boot(String boot) {
        this.boot = boot;
        return this;
    }

    /** Sets the CD-ROM device (e.g., "local:iso/ubuntu.iso"). */
    public PveQemuCreateOptions cdrom(String cdrom) {
        this.cdrom = cdrom;
        return this;
    }

    /** Sets the machine type (e.g., "q35", "pc"). */
    public PveQemuCreateOptions machine(String machine) {
        this.machine = machine;
        return this;
    }

    /** Sets the BIOS type ("seabios" or "ovmf"). */
    public PveQemuCreateOptions bios(String bios) {
        this.bios = bios;
        return this;
    }

    /** Sets the CPU type (e.g., "host", "kvm64"). */
    public PveQemuCreateOptions cpu(String cpu) {
        this.cpu = cpu;
        return this;
    }

    /** Sets the balloon device memory target in MB (0 to disable). */
    public PveQemuCreateOptions balloon(Integer balloon) {
        this.balloon = balloon;
        return this;
    }

    /** Sets whether to start VM on boot. */
    public PveQemuCreateOptions onboot(Boolean onboot) {
        this.onboot = onboot;
        return this;
    }

    /** Sets whether to enable QEMU Guest Agent. */
    public PveQemuCreateOptions agent(Boolean agent) {
        this.agent = agent;
        return this;
    }

    /** Sets the resource pool to add this VM to. */
    public PveQemuCreateOptions pool(String pool) {
        this.pool = pool;
        return this;
    }

    /** Sets whether to start VM after creation. */
    public PveQemuCreateOptions start(Boolean start) {
        this.start = start;
        return this;
    }

    /** Sets whether to create as template. */
    public PveQemuCreateOptions template(Boolean template) {
        this.template = template;
        return this;
    }

    /**
     * Adds a SCSI disk configuration.
     *
     * @param index the disk index (0, 1, 2, etc.)
     * @param config the disk configuration (e.g., "local-lvm:32,format=qcow2")
     * @return this instance for method chaining
     */
    public PveQemuCreateOptions scsi(int index, String config) {
        disks.put("scsi" + index, config);
        return this;
    }

    /**
     * Adds a VirtIO disk configuration.
     *
     * @param index the disk index (0, 1, 2, etc.)
     * @param config the disk configuration
     * @return this instance for method chaining
     */
    public PveQemuCreateOptions virtio(int index, String config) {
        disks.put("virtio" + index, config);
        return this;
    }

    /**
     * Adds a SATA disk configuration.
     *
     * @param index the disk index (0, 1, 2, etc.)
     * @param config the disk configuration
     * @return this instance for method chaining
     */
    public PveQemuCreateOptions sata(int index, String config) {
        disks.put("sata" + index, config);
        return this;
    }

    /**
     * Adds an IDE device configuration.
     *
     * @param index the IDE index (0, 1, 2, 3)
     * @param config the device configuration (e.g., "local:iso/ubuntu.iso,media=cdrom")
     * @return this instance for method chaining
     */
    public PveQemuCreateOptions ide(int index, String config) {
        ide.put("ide" + index, config);
        return this;
    }

    /**
     * Adds a network interface configuration.
     *
     * @param index the network index (0, 1, 2, etc.)
     * @param config the network configuration (e.g., "virtio,bridge=vmbr0")
     * @return this instance for method chaining
     */
    public PveQemuCreateOptions net(int index, String config) {
        networks.put("net" + index, config);
        return this;
    }

    /**
     * Builds and returns the options instance.
     *
     * @return this instance
     */
    public PveQemuCreateOptions build() {
        return this;
    }
}

