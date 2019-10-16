package fr.esir.jxc.models.events;

import fr.esir.jxc.models.user.requests.ShareArticleRequest;
import lombok.Value;

@Value
public class ArticleShared {

    private String ownerEmail;
    private String targetEmail;
    private String articleId;

    public static ArticleShared of (ShareArticleRequest shareArticleRequest) {

        return new ArticleShared(
                shareArticleRequest.getOwnerEmail(),
                shareArticleRequest.getTargetEmail(),
                shareArticleRequest.getArticleId()
        );
    }
}
