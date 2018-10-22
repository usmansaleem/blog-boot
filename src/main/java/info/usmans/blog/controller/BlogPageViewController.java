package info.usmans.blog.controller;

import info.usmans.blog.model.BlogItem;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BlogPageViewController {

    private Map<String, BlogItem> blogItemFriendlyUrlIdMap;

    public BlogPageViewController(final Map<String, BlogItem> blogItemFriendlyUrlIdMap) {
        this.blogItemFriendlyUrlIdMap = blogItemFriendlyUrlIdMap;
    }

    @RequestMapping(value = "/usmansaleem/blog/{friendUrl}", method = RequestMethod.GET)
    public String blogEntry(@PathVariable String friendUrl, Model model) {
        if (blogItemFriendlyUrlIdMap.containsKey(friendUrl)) {
            model.addAttribute("blogItem", blogItemFriendlyUrlIdMap.get(friendUrl));
        } else {
            BlogItem blogItem = new BlogItem();
            blogItem.setTitle("Not Found");
            model.addAttribute("blogItem", blogItem);
        }

        return "blog";
    }
}
