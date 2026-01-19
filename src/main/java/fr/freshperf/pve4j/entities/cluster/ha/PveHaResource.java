package fr.freshperf.pve4j.entities.cluster.ha;

import com.google.gson.annotations.SerializedName;

/**
 * Represents an HA (High Availability) managed resource.
 */
public class PveHaResource {

    @SerializedName("sid")
    private String sid;

    @SerializedName("type")
    private String type;

    @SerializedName("state")
    private String state;

    @SerializedName("status")
    private String status;

    @SerializedName("group")
    private String group;

    @SerializedName("max_restart")
    private Integer maxRestart;

    @SerializedName("max_relocate")
    private Integer maxRelocate;

    @SerializedName("comment")
    private String comment;

    @SerializedName("digest")
    private String digest;

    /**
     * Gets the service ID (e.g., "vm:100", "ct:101").
     *
     * @return the service ID
     */
    public String getSid() {
        return sid;
    }

    /**
     * Gets the resource type ("vm" or "ct").
     *
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Gets the requested state ("started", "stopped", "disabled", "ignored").
     *
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * Gets the current status.
     *
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Gets the HA group name.
     *
     * @return the group name
     */
    public String getGroup() {
        return group;
    }

    /**
     * Gets the maximum restart attempts.
     *
     * @return max restart count
     */
    public Integer getMaxRestart() {
        return maxRestart;
    }

    /**
     * Gets the maximum relocate attempts.
     *
     * @return max relocate count
     */
    public Integer getMaxRelocate() {
        return maxRelocate;
    }

    /**
     * Gets the resource comment.
     *
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * Gets the configuration digest.
     *
     * @return the digest
     */
    public String getDigest() {
        return digest;
    }

    @Override
    public String toString() {
        return "PveHaResource{" +
                "sid='" + sid + '\'' +
                ", type='" + type + '\'' +
                ", state='" + state + '\'' +
                ", status='" + status + '\'' +
                ", group='" + group + '\'' +
                '}';
    }
}

