package fr.esir.jxc.models.user.requests;

import lombok.Value;

@Value
public class ShareArticleRequest {

    private String ownerEmail;
    private String targetEmail;
    private String articleId;
}
