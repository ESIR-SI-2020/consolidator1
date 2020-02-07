package fr.esir.jxc.models.user.requests;

import lombok.Value;

@Value
public class RefreshArticleTagsRequest {

    private String email;
    private String articleId;
}
