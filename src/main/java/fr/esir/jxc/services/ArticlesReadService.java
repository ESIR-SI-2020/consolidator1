package fr.esir.jxc.services;

import java.util.Optional;

import fr.esir.jxc.utils.ElasticSearchUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Service;

import fr.esir.jxc.domain.models.Article;


@Service
public class ArticlesReadService {

  private final ElasticsearchOperations elasticsearchOperations;

  public ArticlesReadService(@Autowired ElasticsearchOperations elasticsearchOperations) {
    this.elasticsearchOperations = elasticsearchOperations;
  }

  public Optional<Article> getArticleById(String id) {
    return Optional.ofNullable(
      this.elasticsearchOperations.queryForObject(ElasticSearchUtils.getQuery(id), Article.class)
    );
  }


}
