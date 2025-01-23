package com.aviva.aem.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.commons.osgi.PropertiesUtil;

import com.adobe.acs.commons.wcm.datasources.DataSourceOption;

import com.aviva.aem.test.AudienceService;

@Component(label = "Audience Service", description = "Get Configurations",
    immediate = true)
@Service(AudienceService.class)
@Properties({@Property(name = "rootPath", label = "Root Path",
    value = DEFAULT_PATH)})
public class AudienceServiceImpl implements AudienceService {

    public static final String DEFAULT_PATH = "etc/default";

    private String rootPath = DEFAULT_PATH;

    public List<DataSourceOption> getAudienceAsOptions(SlingHttpServletRequest request) {
        List<DataSourceOption> list = new ArrayList<>();
        Resource resource = request.getResourceResolver().getResource(rootPath);
        if (resource != null) {
            List<String[]> audienceList = getAudiences(resource);
            for (String audienceName : audienceList) {
                list.add(new DataSourceOption(audienceName, audienceName));
            }
        }
        return list;
    }

    /**
     * Retrieves resource names and titles of child nodes of a specific resource.
     * 
     * @param resource the parent resource whose children are to be retrieved
     * @return a list of string arrays containing the names and titles of the child nodes
     * 
     */
    private static List<String[]> getAudiences(Resource resource) {
        List<String[]> list = new ArrayList<>();
        Iterator<Resource> childNodes = resource.listChildren();
        while (childNodes.hasNext()) {
            list.add(new String[]{childNodes.next().getTitle(), childNodes.next().getName()});
        }
        return list;
    }

    /**
     * {@inheritDoc}
     */
    @Activate
    public void activate(Map<String, Object> properties) {
        rootPath = PropertiesUtil.toString(properties.get("rootPath"), null);
    }
}