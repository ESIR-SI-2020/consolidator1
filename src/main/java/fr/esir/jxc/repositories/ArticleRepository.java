package fr.esir.jxc.repositories;


import fr.esir.jxc.models.user.Article;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends ElasticsearchRepository<Article, String> {

    List<Article> findAllSharedArticle(String userId);
}
