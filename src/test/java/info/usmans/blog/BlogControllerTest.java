package info.usmans.blog;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import info.usmans.blog.model.BlogItem;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BlogControllerTest {

    @Test
    void testJsonLoading() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<BlogItem> blogItems = objectMapper
        .readValue(getClass().getResource("/data.json"), new TypeReference<List<BlogItem>>() {});

        Assertions.assertNotNull(blogItems);
        Assertions.assertFalse(blogItems.isEmpty());

    }

}
