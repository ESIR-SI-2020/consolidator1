package fr.esir.jxc.models.user.requests;

import lombok.Value;

@Value
public class DeleteArticleRequest {

    private String email;
    private String articleId;
}
