package fr.freshperf.pve4j.entities.nodes.node.qemu.snapshot;

import com.google.gson.annotations.SerializedName;

/**
 * Represents the configuration stored with a QEMU VM snapshot.
 */
public class PveQemuSnapshotConfig {

    @SerializedName("description")
    private String description;

    @SerializedName("memory")
    private Integer memory;

    @SerializedName("cores")
    private Integer cores;

    @SerializedName("sockets")
    private Integer sockets;

    @SerializedName("name")
    private String name;

    @SerializedName("ostype")
    private String ostype;

    @SerializedName("boot")
    private String boot;

    @SerializedName("machine")
    private String machine;

    @SerializedName("scsihw")
    private String scsihw;

    /**
     * Gets the snapshot description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the memory size in MB.
     *
     * @return the memory size
     */
    public Integer getMemory() {
        return memory;
    }

    /**
     * Gets the number of CPU cores.
     *
     * @return the number of cores
     */
    public Integer getCores() {
        return cores;
    }

    /**
     * Gets the number of CPU sockets.
     *
     * @return the number of sockets
     */
    public Integer getSockets() {
        return sockets;
    }

    /**
     * Gets the VM name at snapshot time.
     *
     * @return the VM name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the OS type.
     *
     * @return the OS type
     */
    public String getOstype() {
        return ostype;
    }

    /**
     * Gets the boot order configuration.
     *
     * @return the boot order
     */
    public String getBoot() {
        return boot;
    }

    /**
     * Gets the machine type.
     *
     * @return the machine type
     */
    public String getMachine() {
        return machine;
    }

    /**
     * Gets the SCSI hardware type.
     *
     * @return the SCSI hardware type
     */
    public String getScsihw() {
        return scsihw;
    }

    @Override
    public String toString() {
        return "PveQemuSnapshotConfig{" +
                "description='" + description + '\'' +
                ", memory=" + memory +
                ", cores=" + cores +
                ", sockets=" + sockets +
                ", name='" + name + '\'' +
                ", ostype='" + ostype + '\'' +
                '}';
    }
}

