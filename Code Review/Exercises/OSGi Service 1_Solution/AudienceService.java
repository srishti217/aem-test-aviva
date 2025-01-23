package com.aviva.aem.service;

import java.util.List;

import org.apache.sling.api.SlingHttpServletRequest;

/**
 * Audience service interface.
 */
@FunctionalInterface
public interface AudienceService {

    /**
     * Get the list of audience names from the given request.
     *
     * @param  request the SlingHttpServletRequest instance
     * @return a list of audience names as DataSourceOption objects
     */
    List<Option> getAudienceAsOptions(SlingHttpServletRequest request);
}
