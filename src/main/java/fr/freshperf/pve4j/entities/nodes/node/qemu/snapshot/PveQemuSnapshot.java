package fr.freshperf.pve4j.entities.nodes.node.qemu.snapshot;

import com.google.gson.annotations.SerializedName;

/**
 * Represents a QEMU VM snapshot.
 */
public class PveQemuSnapshot {

    @SerializedName("name")
    private String name;

    @SerializedName("description")
    private String description;

    @SerializedName("snaptime")
    private Long snaptime;

    @SerializedName("vmstate")
    private Integer vmstate;

    @SerializedName("parent")
    private String parent;

    /**
     * Gets the snapshot name.
     *
     * @return the snapshot name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the snapshot description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the snapshot creation time (Unix timestamp).
     *
     * @return the creation timestamp
     */
    public Long getSnaptime() {
        return snaptime;
    }

    /**
     * Checks if the snapshot includes VM RAM state.
     *
     * @return 1 if vmstate is included, 0 otherwise
     */
    public Integer getVmstate() {
        return vmstate;
    }

    /**
     * Checks if the snapshot includes VM RAM state.
     *
     * @return true if vmstate is included
     */
    public boolean hasVmState() {
        return vmstate != null && vmstate == 1;
    }

    /**
     * Gets the parent snapshot name.
     *
     * @return the parent snapshot name, or null if this is the first snapshot
     */
    public String getParent() {
        return parent;
    }

    @Override
    public String toString() {
        return "PveQemuSnapshot{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", snaptime=" + snaptime +
                ", vmstate=" + vmstate +
                ", parent='" + parent + '\'' +
                '}';
    }
}

