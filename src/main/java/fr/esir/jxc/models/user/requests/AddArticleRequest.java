package fr.esir.jxc.models.user.requests;

import lombok.Value;

@Value
public class AddArticleRequest {

    private String email;
    private String articleUrl;
}
