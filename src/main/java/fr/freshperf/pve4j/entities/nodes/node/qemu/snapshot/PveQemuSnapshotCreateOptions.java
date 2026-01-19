package fr.freshperf.pve4j.entities.nodes.node.qemu.snapshot;

import fr.freshperf.pve4j.entities.options.ParameterizedParamsConvertible;
import fr.freshperf.pve4j.util.ParamsHelpers;

import java.util.Map;

/**
 * Options for creating a QEMU VM snapshot.
 * Use the builder pattern to configure snapshot settings.
 */
public class PveQemuSnapshotCreateOptions implements ParameterizedParamsConvertible<String> {

    private String description;
    private Boolean vmstate;

    /**
     * Creates a new builder for snapshot create options.
     *
     * @return a new PveQemuSnapshotCreateOptions instance
     */
    public static PveQemuSnapshotCreateOptions builder() {
        return new PveQemuSnapshotCreateOptions();
    }

    @Override
    public void addRequiredParam(Map<String, Object> params, String snapname) {
        params.put("snapname", snapname);
    }

    @Override
    public void populateParams(Map<String, Object> params) {
        ParamsHelpers.put(params, "description", description);
        ParamsHelpers.putBool(params, "vmstate", vmstate);
    }

    /**
     * Sets the snapshot description.
     *
     * @param description the description text
     * @return this instance for method chaining
     */
    public PveQemuSnapshotCreateOptions description(String description) {
        this.description = description;
        return this;
    }

    /**
     * Sets whether to include VM RAM state in the snapshot.
     * If true, the VM will be frozen during snapshot creation.
     *
     * @param vmstate true to include RAM state
     * @return this instance for method chaining
     */
    public PveQemuSnapshotCreateOptions vmstate(Boolean vmstate) {
        this.vmstate = vmstate;
        return this;
    }

    /**
     * Builds and returns the options instance.
     *
     * @return this instance
     */
    public PveQemuSnapshotCreateOptions build() {
        return this;
    }
}

