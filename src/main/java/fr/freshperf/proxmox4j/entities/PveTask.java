package fr.freshperf.proxmox4j.entities;

/**
 * Represents a Proxmox task identifier (UPID).
 * Format: UPID:node:pid:pstart:starttime:type:id:user:
 */
public class PveTask {
    private String upid;
    private String node;
    private String pid;
    private String pstart;
    private String starttime;
    private String type;
    private String id;
    private String user;

    public PveTask() {
    }

    public PveTask(String upid) {
        this.upid = upid;
        parseUpid(upid);
    }

    /**
     * Parses the UPID string into its components
     */
    private void parseUpid(String upid) {
        if (upid != null && upid.startsWith("UPID:")) {
            String[] parts = upid.split(":", -1);
            if (parts.length >= 8) {
                this.node = parts[1];
                this.pid = parts[2];
                this.pstart = parts[3];
                this.starttime = parts[4];
                this.type = parts[5];
                this.id = parts[6];
                this.user = parts[7];
            }
        }
    }

    public String getUpid() {
        return upid;
    }

    public void setUpid(String upid) {
        this.upid = upid;
        parseUpid(upid);
    }

    public String getNode() {
        return node;
    }

    public String getPid() {
        return pid;
    }

    public String getPstart() {
        return pstart;
    }

    public String getStarttime() {
        return starttime;
    }

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public String getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "PveTask{" +
                "upid='" + upid + '\'' +
                ", node='" + node + '\'' +
                ", pid='" + pid + '\'' +
                ", pstart='" + pstart + '\'' +
                ", starttime='" + starttime + '\'' +
                ", type='" + type + '\'' +
                ", id='" + id + '\'' +
                ", user='" + user + '\'' +
                '}';
    }
}

