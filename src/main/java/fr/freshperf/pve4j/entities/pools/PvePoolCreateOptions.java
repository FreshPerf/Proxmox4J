package fr.freshperf.pve4j.entities.pools;

import fr.freshperf.pve4j.entities.options.ParameterizedParamsConvertible;
import fr.freshperf.pve4j.util.ParamsHelpers;

import java.util.Map;

/**
 * Options for creating a resource pool.
 * Use the builder pattern to configure pool settings.
 */
public class PvePoolCreateOptions implements ParameterizedParamsConvertible<String> {

    private String comment;

    /**
     * Creates a new builder for pool create options.
     *
     * @return a new PvePoolCreateOptions instance
     */
    public static PvePoolCreateOptions builder() {
        return new PvePoolCreateOptions();
    }

    @Override
    public void addRequiredParam(Map<String, Object> params, String poolid) {
        params.put("poolid", poolid);
    }

    @Override
    public void populateParams(Map<String, Object> params) {
        ParamsHelpers.put(params, "comment", comment);
    }

    /**
     * Sets the pool comment/description.
     *
     * @param comment the comment text
     * @return this instance for method chaining
     */
    public PvePoolCreateOptions comment(String comment) {
        this.comment = comment;
        return this;
    }

    /**
     * Builds and returns the options instance.
     *
     * @return this instance
     */
    public PvePoolCreateOptions build() {
        return this;
    }
}

