package info.usmans.blog.controller;

import info.usmans.blog.model.BlogItem;
import info.usmans.blog.model.BlogMeta;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BlogController {

    private final int blogItemsSize;
    private final Map<Long, BlogItem> blogItemIdMap;
    private final Map<Integer, List<BlogItem>> pagedBlogItems;

    public BlogController(List<BlogItem> blogItems, Map<Long, BlogItem> blogItemIdMap,
    Map<Integer, List<BlogItem>> pagedBlogItems) {
        this.blogItemsSize = blogItems.size();
        this.blogItemIdMap = blogItemIdMap;
        this.pagedBlogItems = pagedBlogItems;
    }

    @RequestMapping(value = "/rest/blog/blogMeta", method = RequestMethod.GET)
    ResponseEntity<BlogMeta> getBlogMeta() {
        BlogMeta blogMeta = new BlogMeta();
        blogMeta.setBlogCount(blogItemsSize);
        blogMeta.setPageCount(pagedBlogItems.size());

        return new ResponseEntity<>(blogMeta, HttpStatus.OK);

    }

    @RequestMapping(value = "/rest/blog/blogItems/{pageId}", method = RequestMethod.GET)
    ResponseEntity<List<BlogItem>> getBlogMeta(@PathVariable Integer pageId) {
        if (pagedBlogItems.containsKey(pageId)) {
            return new ResponseEntity<>(pagedBlogItems.get(pageId), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/rest/blog/blogItems/blogItem/{id}", method = RequestMethod.GET)
    ResponseEntity<BlogItem> getBlogItemById(@PathVariable Long id) {
        if (blogItemIdMap.containsKey(id)) {
            return new ResponseEntity<>(blogItemIdMap.get(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
