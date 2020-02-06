package fr.esir.jxc.events;

import lombok.Value;

@Value
public class ArticleDeleted {

  private final String email;
  private final String articleId;

}
