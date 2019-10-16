package fr.esir.jxc.models.events;

import fr.esir.jxc.models.user.requests.SetArticleTagRequest;
import lombok.Value;

import java.util.List;

@Value
public class ArticleTagSetted {

        private String email;
        private String articleId;
        private List<String> tags;

    public static ArticleTagSetted of (SetArticleTagRequest setArticleTagRequest) {

        return new ArticleTagSetted(
                setArticleTagRequest.getEmail(),
                setArticleTagRequest.getArticleId(),
                setArticleTagRequest.getTags()
        );
    }
}
