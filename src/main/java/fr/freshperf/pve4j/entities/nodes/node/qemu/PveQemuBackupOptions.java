package fr.freshperf.pve4j.entities.nodes.node.qemu;

import fr.freshperf.pve4j.entities.options.ParamsConvertible;
import fr.freshperf.pve4j.util.ParamsHelpers;

import java.util.Map;

/**
 * Options for creating a VM backup.
 * Use the builder pattern to configure backup settings.
 */
public class PveQemuBackupOptions implements ParamsConvertible {

    private String storage;
    private String mode;
    private String compress;
    private String notesTemplate;
    private Boolean remove;
    private Integer bwlimit;
    private Integer ionice;
    private Boolean protected_;
    
    /**
     * Creates a new builder for backup options.
     *
     * @return a new PveQemuBackupOptions instance
     */
    public static PveQemuBackupOptions builder() {
        return new PveQemuBackupOptions();
    }

    @Override
    public void populateParams(Map<String, Object> params) {
        ParamsHelpers.put(params, "storage", storage);
        ParamsHelpers.put(params, "mode", mode);
        ParamsHelpers.put(params, "compress", compress);
        ParamsHelpers.put(params, "notes-template", notesTemplate);
        ParamsHelpers.putBool(params, "remove", remove);
        ParamsHelpers.putInt(params, "bwlimit", bwlimit);
        ParamsHelpers.putInt(params, "ionice", ionice);
        ParamsHelpers.putBool(params, "protected", protected_);
    }

    /**
     * Sets the target storage for the backup.
     *
     * @param storage the storage ID
     * @return this instance for method chaining
     */
    public PveQemuBackupOptions storage(String storage) {
        this.storage = storage;
        return this;
    }

    /**
     * Sets the backup mode.
     *
     * @param mode "snapshot" (default), "suspend", or "stop"
     * @return this instance for method chaining
     */
    public PveQemuBackupOptions mode(String mode) {
        this.mode = mode;
        return this;
    }

    /**
     * Sets the compression algorithm.
     *
     * @param compress "0" (none), "1" (lzo), "gzip", or "zstd"
     * @return this instance for method chaining
     */
    public PveQemuBackupOptions compress(String compress) {
        this.compress = compress;
        return this;
    }

    /**
     * Sets the notes template for the backup.
     *
     * @param notesTemplate template with variables like {{guestname}}, {{vmid}}
     * @return this instance for method chaining
     */
    public PveQemuBackupOptions notesTemplate(String notesTemplate) {
        this.notesTemplate = notesTemplate;
        return this;
    }

    /**
     * Sets whether to remove old backups if storage is full.
     *
     * @param remove true to auto-remove old backups
     * @return this instance for method chaining
     */
    public PveQemuBackupOptions remove(Boolean remove) {
        this.remove = remove;
        return this;
    }

    /**
     * Sets the I/O bandwidth limit in KiB/s.
     *
     * @param bwlimit bandwidth limit (0 for unlimited)
     * @return this instance for method chaining
     */
    public PveQemuBackupOptions bwlimit(Integer bwlimit) {
        this.bwlimit = bwlimit;
        return this;
    }

    /**
     * Sets the I/O nice priority (0-8).
     *
     * @param ionice the ionice value
     * @return this instance for method chaining
     */
    public PveQemuBackupOptions ionice(Integer ionice) {
        this.ionice = ionice;
        return this;
    }

    /**
     * Sets whether to mark backup as protected.
     *
     * @param protected_ true to protect backup from deletion
     * @return this instance for method chaining
     */
    public PveQemuBackupOptions protected_(Boolean protected_) {
        this.protected_ = protected_;
        return this;
    }

    /**
     * Builds and returns the options instance.
     *
     * @return this instance
     */
    public PveQemuBackupOptions build() {
        return this;
    }
}

