package fr.esir.jxc.models.user;

import java.util.List;

public class Article {

    private String id;
    private String url;
    private List<String> tags;
    private List<String> suggestedTags;

    public Article(String id, String url, List<String> tags, List<String> suggestedTags) {
        this.id = id;
        this.url = url;
        this.tags = tags;
        this.suggestedTags = suggestedTags;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<String> getSuggestedTags() {
        return suggestedTags;
    }

    public void setSuggestedTags(List<String> suggestedTags) {
        this.suggestedTags = suggestedTags;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id='" + id + '\'' +
                ", url='" + url + '\'' +
                ", tags=" + tags +
                ", suggestedTags=" + suggestedTags +
                '}';
    }
}
