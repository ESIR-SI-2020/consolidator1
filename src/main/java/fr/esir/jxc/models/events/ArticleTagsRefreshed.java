package fr.esir.jxc.models.events;

import fr.esir.jxc.models.user.requests.RefreshArticleTagsRequest;
import lombok.Value;

@Value
public class ArticleTagsRefreshed {

    private String email;
    private String articleId;

    public static ArticleTagsRefreshed of (RefreshArticleTagsRequest refreshArticleTagsRequest) {

        return new ArticleTagsRefreshed(
                refreshArticleTagsRequest.getEmail(),
                refreshArticleTagsRequest.getArticleId()
        );
    }
}
