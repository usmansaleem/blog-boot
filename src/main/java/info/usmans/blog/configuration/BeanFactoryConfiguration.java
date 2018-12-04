package info.usmans.blog.configuration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import info.usmans.blog.model.BlogItem;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.boot.web.embedded.undertow.UndertowBuilderCustomizer;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanFactoryConfiguration {

    private static final TypeReference<?> BLOG_LIST_TYPE_REFERENCE = new BlogListTypeReference();
    private static final String JSON_DATA_PATH = "/data.json";
    private static final int BLOG_ITEMS_PER_PAGE = 10;
    private static final ObjectMapper objectMapper = new ObjectMapper();

    //start additional listener at 8080 (default one is starting on 8443 via application.yaml)
    @Bean
    public UndertowServletWebServerFactory servletWebServerFactory() {
        UndertowServletWebServerFactory factory = new UndertowServletWebServerFactory();
        factory.addBuilderCustomizers((UndertowBuilderCustomizer) builder -> builder.addHttpListener(8080, "0.0.0.0"));
        return factory;
    }

    @Bean
    List<BlogItem> loadBlogItems() throws IOException {
        return objectMapper
        .readValue(getClass().getResource(JSON_DATA_PATH), BLOG_LIST_TYPE_REFERENCE);
    }

    @Bean
    Map<Long, BlogItem> loadBlogItemsIdMap(final List<BlogItem> blogItems) {
        return blogItems.stream().collect(Collectors.toMap(BlogItem::getId, Function.identity()));
    }

    @Bean
    Map<String, BlogItem> loadBlogItemsFriendlyUrlIdMap(final List<BlogItem> blogItems) {
        return blogItems.stream().collect(Collectors.toMap(BlogItem::getUrlFriendlyId, Function.identity()));
    }

    @Bean
    Map<Integer, List<BlogItem>> loadBlogPageList(final List<BlogItem> blogItems) {
        int blogItemsSize = blogItems.size();
        List<BlogItem> sorted = blogItems.stream()
        .sorted(Comparator.comparingLong(BlogItem::getId).reversed())
        .collect(Collectors.toList());

        //page 1 contains latest items, last page contains oldest items
        Map<Integer, List<BlogItem>> blogPageList = new ConcurrentHashMap<>(blogItemsSize);

        int startIdx = 0; //inclusive
        int endIdx = blogItemsSize; //exclusive
        int totalPagesCount = 1;
        //if we have blog items which won't fix on one page ...
        if (blogItemsSize > BLOG_ITEMS_PER_PAGE) {
            endIdx = BLOG_ITEMS_PER_PAGE;
            totalPagesCount = blogItemsSize / BLOG_ITEMS_PER_PAGE;
            int itemsOnLastPage = blogItemsSize % BLOG_ITEMS_PER_PAGE;

            //if remainder is greater than 0, then we need an additional page.
            if (itemsOnLastPage > 0) {
                totalPagesCount++;
            }
        }

        //iterate from page 1 to highest page...
        for (int pageNumber = 1; pageNumber <= totalPagesCount; pageNumber++) {
            blogPageList.put(pageNumber, sorted.subList(startIdx, endIdx));

            //update indexes
            startIdx = endIdx;
            endIdx += BLOG_ITEMS_PER_PAGE;
            if (endIdx > blogItemsSize) {
                endIdx = blogItemsSize;
            }
        }

        return blogPageList;
    }


    private static class BlogListTypeReference extends TypeReference<List<BlogItem>> {

    }
}
