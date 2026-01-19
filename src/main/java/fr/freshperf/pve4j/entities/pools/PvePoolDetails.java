package fr.freshperf.pve4j.entities.pools;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Represents detailed information about a Proxmox resource pool including its members.
 */
public class PvePoolDetails {

    @SerializedName("comment")
    private String comment;

    @SerializedName("members")
    private List<PvePoolMember> members;

    /**
     * Gets the pool comment/description.
     *
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * Gets the list of pool members.
     *
     * @return the members list
     */
    public List<PvePoolMember> getMembers() {
        return members;
    }

    @Override
    public String toString() {
        return "PvePoolDetails{" +
                "comment='" + comment + '\'' +
                ", members=" + (members != null ? members.size() : 0) + " items" +
                '}';
    }
}

