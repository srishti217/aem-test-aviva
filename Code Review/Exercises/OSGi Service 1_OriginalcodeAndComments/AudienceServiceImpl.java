package com.aviva.aem.test; //Issue: Package name does not follow best conventions. Instead of `test`, we should use `service.impl` to better represent the functionality. ex - 'com.aviva.aem.service.impl;'


import static org.apache.commons.lang3.StringUtils.EMPTY; //Issue: Unused import

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

    public List getAudienceAsOptions(SlingHttpServletRequest request) {
        List list = new ArrayList<>(); //Issue: We should use parameterized generics to ensure type safety and avoid runtime errors like ClassCastException.
        Resource resource = request.getResourceResolver().getResource(rootPath);
        if (resource != null) {
            List<String[]> audienceList = getAudiences(resource);
            for (String audienceName : audienceList) {
                list.add(new Option(audienceName, audienceName)); //Issue: Option is undefined. It should be replaced with DataSourceOption or the correct class. 
            }
        }
        return list;
    }

    /**
     * Returns resource name and page title of the childNodes of a specific resource. //Issue: JavaDoc for getAudiences is incomplete and lacks details.
     *
     * @param resource
     */
    private static List<String> getAudiences(Resource resource) {
        List<String> list = new List<>(); // Issue: List incorrectly created with new List<>(). It should be new ArrayList<>().
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