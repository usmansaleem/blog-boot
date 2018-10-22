package info.usmans.blog.configuration;

import info.usmans.blog.model.BlogItem;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith({SpringExtension.class})
class BeanFactoryConfigurationTest {

    @Autowired
    private List<BlogItem> blogItems;

    @Autowired
    private Map<Long, BlogItem> blogItemIdMap;

    @Autowired
    private Map<String, BlogItem> blogItemFriendlyUrlIdMap;

    @Autowired
    private Map<Integer, List<BlogItem>> pagedBlogItems;


    @Test
    void testJsonLoading() {

        Assertions.assertNotNull(blogItems);
        Assertions.assertFalse(blogItems.isEmpty());

        Assertions.assertNotNull(blogItemIdMap);
        Assertions.assertFalse(blogItemIdMap.isEmpty());

        Assertions.assertNotNull(blogItemFriendlyUrlIdMap);
        Assertions.assertFalse(blogItemFriendlyUrlIdMap.isEmpty());

        Assertions.assertEquals("SRI Hash Generator", blogItemIdMap.get(185L).getTitle());
        Assertions.assertEquals("SRI Hash Generator", blogItemFriendlyUrlIdMap.get("sri_hash_generator").getTitle());

        Assertions.assertNotNull(pagedBlogItems);
        Assertions.assertFalse(pagedBlogItems.isEmpty());
        
    }

}
