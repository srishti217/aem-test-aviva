package  com.aviva.aem.test.models;

import com.aviva.aem.test.models.LinkModel;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * JUnit test class for LinkModel
 */
@ExtendWith(AemContextExtension.class) 
public class LinkModelTest {

    private static final AemContext context = new AemContext();
    private static final String RESOURCE_PATH = "/content/mypage";

    /**
     * Setup: @BeforeEach annotation in JUnit is used on a method containing Java code
     *  to run before each test case. i.e it runs before each test execution.
     */
    @BeforeEach
    public void setup() {
       
        Resource resource = context.create().resource(RESOURCE_PATH,
                "jcr:title", "Page Title",  
                "sling:resourceType", "aviva/components/linkmodel");

        context.currentResource(resource);
    }

    /**
     * Test method for Title.
     */
    @Test
    void getTitle() {
        
        LinkModel linkModel = context.request().adaptTo(LinkModel.class);
        assertEquals("Page Title", linkModel.getTitle());
    }

    /**
     * Test method for Link.
     */
    @Test
    void getLink() {
       
        LinkModel linkModel = context.request().adaptTo(LinkModel.class)
        assertEquals("/content/mypage.html", linkModel.getLink());
    }
}
