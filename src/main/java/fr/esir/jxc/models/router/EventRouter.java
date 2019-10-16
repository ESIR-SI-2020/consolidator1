package fr.esir.jxc.models.router;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.esir.jxc.models.events.*;
import fr.esir.jxc.models.events.models.Event;
import fr.esir.jxc.models.user.UserEventsHandler;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.function.Consumer;


@Service
@Log4j2
public class EventRouter {

    private final ObjectMapper objectMapper;
    private final UserEventsHandler eventsHandler;

    public EventRouter(@Autowired ObjectMapper objectMapper, @Autowired UserEventsHandler eventsHandler) {
        this.objectMapper = objectMapper;
        this.eventsHandler = eventsHandler;
    }


    public void route(Event eventModel) {
        switch (eventModel.getEventName()){
            case "USER_ADDED":
                handleEvent(eventModel, UserAdded.class, this.eventsHandler::handleUserCreatedEvent);
                break;
            case "USER_DELETED":
                handleEvent(eventModel, UserDeleted.class, this.eventsHandler::handeleUserDeletedEvent);
                break;
            case "ARTICLE_ADDED":
                handleEvent(eventModel, ArticleAdded.class,this.eventsHandler::handleArticleAddedEvent);
                break;
            case "ARTICLE_DELETED":
                handleEvent(eventModel, ArticleDeleted.class,this.eventsHandler::handleArticleDeletedEvent);
                break;
            case "ARTICLE_TAG_SETTED":
                handleEvent(eventModel, ArticleTagSetted.class,this.eventsHandler::handleArticleTagSettedEvent);
                break;
            case "PASSWORD_UPDATED":
                handleEvent(eventModel,PasswordUpdated.class,this.eventsHandler::handlePasswordUpdatedEvent);
                break;
            case "USER_FRIENDLIST_ADDED":
                handleEvent(eventModel,UserFriendListAdded.class,this.eventsHandler::handleUserFriendListAddedEvent);
                break;
            case "USER_FRIENDLIST_DELETED":
                handleEvent(eventModel,UserFriendListDeleted.class,this.eventsHandler::handleUserFriendListDeletedEvent);
                break;
            case "ARTICLE_SHARED":
                handleEvent(eventModel,ArticleShared.class,this.eventsHandler::handleArticleSharedEvent);
                break;
            case "ARTICLE_TAGS_REFRESHED":
                handleEvent(eventModel,ArticleTagsRefreshed.class,this.eventsHandler::handleArticleTagsRefreshedEvent);
                break;
            default:
                log.warn("Received an unknown event: " + eventModel.toString());
                break;
            }

        }
    private <T> void handleEvent(Event event, Class<T> tClass, Consumer<T> handler) {
        try {
            final T parsedBody = this.objectMapper.treeToValue(event.getBody(), tClass);
            handler.accept(parsedBody);
        } catch (IOException e) {
            // TODO: handle exceptions more precisely
            log.error(e.getMessage(), e);
        }
    }


}


