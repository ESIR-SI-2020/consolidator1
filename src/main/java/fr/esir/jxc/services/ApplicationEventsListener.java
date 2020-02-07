package fr.esir.jxc.services;

import java.io.IOException;
import java.util.function.Consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.esir.jxc.config.Jackson;
import fr.esir.jxc.events.*;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.stereotype.Service;

import fr.esir.jxc.domain.events.Event;

@Service
@Log4j2
public class ApplicationEventsListener implements MessageListener<String, Event> {

  private final ObjectMapper objectMapper;
  private final UsersEventHandler usersEventHandler;
  private final ArticlesEventHandler articlesEventHandler;

  public ApplicationEventsListener(
    @Autowired UsersEventHandler usersEventHandler,
    @Autowired ArticlesEventHandler articlesEventHandler
  ) {
    this.objectMapper = Jackson.OBJECT_MAPPER;
    this.usersEventHandler = usersEventHandler;
    this.articlesEventHandler = articlesEventHandler;
  }

  @Override
  public void onMessage(ConsumerRecord<String, Event> data) {
    routeEvent(data.value());
  }

  private void routeEvent(Event event) {
    switch(event.getType()) {
      case "USER_ADDED":
        handleEvent(event, UserAdded.class, this.usersEventHandler::handleUserCreatedEvent);
        break;
      case "USER_DELETED":
        handleEvent(event, UserDeleted.class, this.usersEventHandler::handleUserDeletedEvent);
        break;
      case "ARTICLE_ADDED":
        handleEvent(event, ArticleAdded.class,this.articlesEventHandler::handleArticleAddedEvent);
        break;
      case "ARTICLE_DELETED":
        handleEvent(event, ArticleDeleted.class,this.articlesEventHandler::handleArticleDeletedEvent);
        break;
      case "ARTICLE_TAG_SET":
        handleEvent(event, ArticleTagsSet.class,this.articlesEventHandler::handleArticleTagSetEvent);
        break;
      case "PASSWORD_UPDATED":
        handleEvent(event, PasswordUpdated.class,this.usersEventHandler::handlePasswordUpdatedEvent);
        break;
      case "USER_ADDED_A_FRIEND":
        handleEvent(event, UserAddedAFriend.class,this.usersEventHandler::handleUserAddedFriendEvent);
        break;
      case "USER_REMOVED_A_FRIEND":
        handleEvent(event, UserRemovedAFriend.class,this.usersEventHandler::handleUserRemovedAFriendEvent);
        break;
      case "ARTICLE_SHARED":
        handleEvent(event,ArticleShared.class,this.articlesEventHandler::handleArticleSharedEvent);
        break;
      case "ARTICLE_TAGS_REFRESHED":
        handleEvent(event,ArticleTagsRefreshed.class,this.articlesEventHandler::handleArticleTagsRefreshedEvent);
        break;
      default:
        log.warn("Received an unknown event: " + event.toString());
        break;
    }

  }

  private <T> void handleEvent(Event event, Class<T> tClass, Consumer<T> handler) {
    try {
      final T parsedBody = this.objectMapper.treeToValue(event.getMetadata(), tClass);
      handler.accept(parsedBody);
    } catch (IOException e) {
      log.error("Event received with incorrect metadata. Was: " + event.toString() + ". Exception message is: " + e.getMessage(), e);
    }
  }

}
