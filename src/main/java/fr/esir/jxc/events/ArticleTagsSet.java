package fr.esir.jxc.events;

import lombok.Value;

import java.util.List;

@Value
public class ArticleTagsSet {

  private final String email;
  private final String articleId;
  private final List<String> tags;

}
