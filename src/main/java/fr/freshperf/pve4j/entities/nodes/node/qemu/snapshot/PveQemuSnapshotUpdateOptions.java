package fr.freshperf.pve4j.entities.nodes.node.qemu.snapshot;

import fr.freshperf.pve4j.entities.options.ParamsConvertible;
import fr.freshperf.pve4j.util.ParamsHelpers;

import java.util.Map;

/**
 * Options for updating a QEMU VM snapshot.
 * Use the builder pattern to configure update settings.
 */
public class PveQemuSnapshotUpdateOptions implements ParamsConvertible {

    private String description;

    /**
     * Creates a new builder for snapshot update options.
     *
     * @return a new PveQemuSnapshotUpdateOptions instance
     */
    public static PveQemuSnapshotUpdateOptions builder() {
        return new PveQemuSnapshotUpdateOptions();
    }

    @Override
    public void populateParams(Map<String, Object> params) {
        ParamsHelpers.put(params, "description", description);
    }

    /**
     * Sets the new snapshot description.
     *
     * @param description the description text
     * @return this instance for method chaining
     */
    public PveQemuSnapshotUpdateOptions description(String description) {
        this.description = description;
        return this;
    }

    /**
     * Builds and returns the options instance.
     *
     * @return this instance
     */
    public PveQemuSnapshotUpdateOptions build() {
        return this;
    }
}

