package fr.esir.jxc.events;

import lombok.Value;

@Value
public class ArticleShared {

  private final String ownerEmail;
  private final String targetEmail;
  private final String articleId;

}
