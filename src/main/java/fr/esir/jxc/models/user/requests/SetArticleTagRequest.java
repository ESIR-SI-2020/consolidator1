package fr.esir.jxc.models.user.requests;

import lombok.Value;

import java.util.List;

@Value
public class SetArticleTagRequest {

    private String email;
    private String articleId;
    private List<String> tags;

}
