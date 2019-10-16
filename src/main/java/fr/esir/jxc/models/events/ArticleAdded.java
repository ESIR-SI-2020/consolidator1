package fr.esir.jxc.models.events;

import fr.esir.jxc.models.user.requests.AddArticleRequest;
import lombok.Value;

import java.util.UUID;

@Value
public class ArticleAdded {

    private String id;
    private String email;
    private String articleUrl;

    public static  ArticleAdded of (AddArticleRequest addArticleRequest) {

        return new ArticleAdded(
                UUID.randomUUID().toString(),
                addArticleRequest.getEmail(),
                addArticleRequest.getArticleUrl()
        );
    }
}
