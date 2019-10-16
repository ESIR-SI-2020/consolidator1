package fr.esir.jxc.models.user;

import fr.esir.jxc.models.events.*;

import java.util.Collections;

public class UserEventsHandler {

    public void handleUserCreatedEvent(UserAdded userCreated) {
        User newUser = new User(
                userCreated.getId(),
                userCreated.getUsername(),
                userCreated.getPassword(),
                userCreated.getEmail(),
                null,
                null,
                null,
                Collections.emptyList(),
                null,
                null
        );
        System.out.println("user added");
    }

    public  void handeleUserDeletedEvent(UserDeleted userDeleted) {
        //code
    }

    public  void handleArticleAddedEvent(ArticleAdded articleAdded) {
        Article newArticle = new Article(
                articleAdded.getId(),
                articleAdded.getArticleUrl(),
                null,
                null
        );
    }

    public  void handleArticleDeletedEvent(ArticleDeleted articleDeleted) {
        //code
    }

    public void handleArticleTagSettedEvent(ArticleTagSetted articleTagSetted) {
        //code
    }

    public void handlePasswordUpdatedEvent(PasswordUpdated passwordUpdated) {
        //code
    }

    public void handleUserFriendListAddedEvent(UserFriendListAdded userFriendListAdded) {
        //code
    }

    public  void handleUserFriendListDeletedEvent(UserFriendListDeleted userFriendListDeleted) {
        //code
    }

    public  void handleArticleSharedEvent(ArticleShared articleShared) {
        //code
    }

    public void handleArticleTagsRefreshedEvent(ArticleTagsRefreshed articleTagsRefreshed) {
        //
    }
}
