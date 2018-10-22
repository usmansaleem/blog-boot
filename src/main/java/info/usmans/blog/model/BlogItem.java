package info.usmans.blog.model;

import java.util.List;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class BlogItem {
    public static final int BLOG_ITEMS_PER_PAGE = 10;

    private Long id;
    private String urlFriendlyId;
    private String title;
    private String description;
    private String body;
    private String blogSection;
    private String createdOn;
    private String modifiedOn;
    private String createDay;
    private String createMonth;
    private String createYear;
    private List<Category> categories;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrlFriendlyId() {
        return urlFriendlyId;
    }

    public void setUrlFriendlyId(String urlFriendlyId) {
        this.urlFriendlyId = urlFriendlyId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getBlogSection() {
        return blogSection;
    }

    public void setBlogSection(String blogSection) {
        this.blogSection = blogSection;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(String modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public String getCreateDay() {
        return createDay;
    }

    public void setCreateDay(String createDay) {
        this.createDay = createDay;
    }

    public String getCreateMonth() {
        return createMonth;
    }

    public void setCreateMonth(String createMonth) {
        this.createMonth = createMonth;
    }

    public String getCreateYear() {
        return createYear;
    }

    public void setCreateYear(String createYear) {
        this.createYear = createYear;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BlogItem blogItem = (BlogItem) o;

        return new EqualsBuilder()
        .append(id, blogItem.id)
        .append(urlFriendlyId, blogItem.urlFriendlyId)
        .append(title, blogItem.title)
        .append(description, blogItem.description)
        .append(body, blogItem.body)
        .append(blogSection, blogItem.blogSection)
        .append(createdOn, blogItem.createdOn)
        .append(modifiedOn, blogItem.modifiedOn)
        .append(createDay, blogItem.createDay)
        .append(createMonth, blogItem.createMonth)
        .append(createYear, blogItem.createYear)
        .append(categories, blogItem.categories)
        .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
        .append(id)
        .append(urlFriendlyId)
        .append(title)
        .append(description)
        .append(body)
        .append(blogSection)
        .append(createdOn)
        .append(modifiedOn)
        .append(createDay)
        .append(createMonth)
        .append(createYear)
        .append(categories)
        .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
        .append("id", id)
        .append("urlFriendlyId", urlFriendlyId)
        .append("title", title)
        .append("description", description)
        .append("body", body)
        .append("blogSection", blogSection)
        .append("createdOn", createdOn)
        .append("modifiedOn", modifiedOn)
        .append("createDay", createDay)
        .append("createMonth", createMonth)
        .append("createYear", createYear)
        .append("categories", categories)
        .toString();
    }
}
