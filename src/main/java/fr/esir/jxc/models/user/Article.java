package fr.esir.jxc.models.user;

import lombok.Value;

import java.util.List;

@Value
public class Article {

    private String id;
    private String url;
    private List<String> tags;
    private List<String> suggestedTags;

}
