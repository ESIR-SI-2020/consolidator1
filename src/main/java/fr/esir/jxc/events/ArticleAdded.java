package fr.esir.jxc.events;

import lombok.Value;

@Value
public class ArticleAdded {

  private final String email;
  private final String articleUrl;

}
