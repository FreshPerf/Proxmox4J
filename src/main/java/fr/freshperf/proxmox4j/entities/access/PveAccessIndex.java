package fr.freshperf.proxmox4j.entities.access;

public class PveAccessIndex {

    private String subdir;

    public String getSubdir() {
        return subdir;
    }

    @Override
    public String toString() {
        return "PveAccessIndex{" +
                "subdir='" + subdir + '\'' +
                '}';
    }
}

