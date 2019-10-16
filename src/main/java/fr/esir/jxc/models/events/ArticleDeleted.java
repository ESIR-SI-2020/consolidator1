package fr.esir.jxc.models.events;

import fr.esir.jxc.models.user.requests.DeleteArticleRequest;

import lombok.Value;

@Value
public class ArticleDeleted {
    private String email;
    private String articleId;

    public static ArticleDeleted of (DeleteArticleRequest deleteArticleRequest) {

        return  new ArticleDeleted(
                deleteArticleRequest.getEmail(),
                deleteArticleRequest.getArticleId()
        );
    }
}
