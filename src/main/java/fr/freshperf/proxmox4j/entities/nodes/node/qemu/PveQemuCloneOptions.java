package fr.freshperf.proxmox4j.entities.nodes.node.qemu;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Options for cloning a QEMU VM/template.
 */
public class PveQemuCloneOptions {

    private String name;
    private String description;
    private String target;
    private String storage;
    private String pool;
    private String snapname;
    private String format;
    private Integer bwlimit;
    private Boolean full;

    public static PveQemuCloneOptions builder() {
        return new PveQemuCloneOptions();
    }

    public PveQemuCloneOptions name(String name) {
        this.name = name;
        return this;
    }

    public PveQemuCloneOptions description(String description) {
        this.description = description;
        return this;
    }

    public PveQemuCloneOptions target(String target) {
        this.target = target;
        return this;
    }

    public PveQemuCloneOptions storage(String storage) {
        this.storage = storage;
        return this;
    }

    public PveQemuCloneOptions pool(String pool) {
        this.pool = pool;
        return this;
    }

    public PveQemuCloneOptions snapname(String snapname) {
        this.snapname = snapname;
        return this;
    }

    public PveQemuCloneOptions format(String format) {
        if (format != null && !format.equals("raw") && !format.equals("qcow2") && !format.equals("vmdk")) {
            throw new IllegalArgumentException("format must be one of: raw, qcow2, vmdk");
        }
        this.format = format;
        return this;
    }

    public PveQemuCloneOptions bwlimit(Integer bwlimit) {
        if (bwlimit != null && bwlimit < 0) {
            throw new IllegalArgumentException("bwlimit must be >= 0");
        }
        this.bwlimit = bwlimit;
        return this;
    }

    public PveQemuCloneOptions full(Boolean full) {
        this.full = full;
        return this;
    }

    public static Map<String, Object> toParams(int newVmid, PveQemuCloneOptions options) {
        Map<String, Object> params = new HashMap<>();
        params.put("newid", String.valueOf(newVmid));

        if (options == null) {
            return params;
        }

        if (options.name != null) {
            params.put("name", options.name);
        }
        if (options.description != null) {
            params.put("description", options.description);
        }
        if (options.target != null) {
            params.put("target", options.target);
        }
        if (options.storage != null) {
            params.put("storage", options.storage);
        }
        if (options.pool != null) {
            params.put("pool", options.pool);
        }
        if (options.snapname != null) {
            params.put("snapname", options.snapname);
        }
        if (options.format != null) {
            params.put("format", options.format);
        }
        if (options.bwlimit != null) {
            params.put("bwlimit", String.valueOf(options.bwlimit));
        }
        if (options.full != null) {
            params.put("full", options.full ? "1" : "0");
        }

        return params;
    }
}
