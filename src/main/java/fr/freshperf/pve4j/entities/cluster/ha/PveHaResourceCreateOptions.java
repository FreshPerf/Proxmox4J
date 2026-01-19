package fr.freshperf.pve4j.entities.cluster.ha;

import fr.freshperf.pve4j.entities.options.ParameterizedParamsConvertible;
import fr.freshperf.pve4j.util.ParamsHelpers;

import java.util.Map;

/**
 * Options for creating an HA resource.
 * Use the builder pattern to configure HA settings.
 */
public class PveHaResourceCreateOptions implements ParameterizedParamsConvertible<String> {

    private String state;
    private String group;
    private Integer maxRestart;
    private Integer maxRelocate;
    private String comment;

    /**
     * Creates a new builder for HA resource create options.
     *
     * @return a new PveHaResourceCreateOptions instance
     */
    public static PveHaResourceCreateOptions builder() {
        return new PveHaResourceCreateOptions();
    }

    @Override
    public void addRequiredParam(Map<String, Object> params, String sid) {
        params.put("sid", sid);
    }

    @Override
    public void populateParams(Map<String, Object> params) {
        ParamsHelpers.put(params, "state", state);
        ParamsHelpers.put(params, "group", group);
        ParamsHelpers.putInt(params, "max_restart", maxRestart);
        ParamsHelpers.putInt(params, "max_relocate", maxRelocate);
        ParamsHelpers.put(params, "comment", comment);
    }

    /**
     * Sets the requested state.
     *
     * @param state "started", "stopped", "disabled", or "ignored"
     * @return this instance for method chaining
     */
    public PveHaResourceCreateOptions state(String state) {
        this.state = state;
        return this;
    }

    /**
     * Sets the HA group name.
     *
     * @param group the group name
     * @return this instance for method chaining
     */
    public PveHaResourceCreateOptions group(String group) {
        this.group = group;
        return this;
    }

    /**
     * Sets the maximum restart attempts on failure.
     *
     * @param maxRestart the max restart count (default: 1)
     * @return this instance for method chaining
     */
    public PveHaResourceCreateOptions maxRestart(Integer maxRestart) {
        this.maxRestart = maxRestart;
        return this;
    }

    /**
     * Sets the maximum relocate attempts.
     *
     * @param maxRelocate the max relocate count (default: 1)
     * @return this instance for method chaining
     */
    public PveHaResourceCreateOptions maxRelocate(Integer maxRelocate) {
        this.maxRelocate = maxRelocate;
        return this;
    }

    /**
     * Sets the resource comment.
     *
     * @param comment the comment
     * @return this instance for method chaining
     */
    public PveHaResourceCreateOptions comment(String comment) {
        this.comment = comment;
        return this;
    }

    /**
     * Builds and returns the options instance.
     *
     * @return this instance
     */
    public PveHaResourceCreateOptions build() {
        return this;
    }
}

