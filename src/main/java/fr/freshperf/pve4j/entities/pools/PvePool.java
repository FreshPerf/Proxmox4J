package fr.freshperf.pve4j.entities.pools;

import com.google.gson.annotations.SerializedName;

/**
 * Represents a Proxmox resource pool.
 */
public class PvePool {

    @SerializedName("poolid")
    private String poolid;

    @SerializedName("comment")
    private String comment;

    /**
     * Gets the pool ID.
     *
     * @return the pool ID
     */
    public String getPoolid() {
        return poolid;
    }

    /**
     * Gets the pool comment/description.
     *
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    @Override
    public String toString() {
        return "PvePool{" +
                "poolid='" + poolid + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}

