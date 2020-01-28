package fr.esir.jxc.models.user;


import fr.esir.jxc.models.events.*;
import fr.esir.jxc.repositories.ArticleRepository;
import fr.esir.jxc.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserEventsHandler {
    private final  UserRepository userRepository;
    private final  ArticleRepository articleRepository;

    @Autowired
    public UserEventsHandler( UserRepository userRepository,ArticleRepository articleRepository) {
        this.userRepository = userRepository;
        this.articleRepository = articleRepository;
    }

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
        User resultingUser = this.userRepository.save(newUser);
        System.out.println("Successfully created user: " + resultingUser.toString());
    }

    public  void handleUserDeletedEvent(UserDeleted userDeleted) {

        User tmp = new User(
                null,
                null,
                null,
                userDeleted.getEmail(),
                null,
                null,
                null,
                null,
                null,
                null);
        User user =userRepository.deleteByEmail(tmp.getEmail());
        System.out.println("Successfully deleted user: " + user.toString());
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
        Optional<User> u = this.userRepository.findById(passwordUpdated.getEmail());
        User userUpdate=u.get();
        User newUser = new User(
                userUpdate.getId(),
               userUpdate.getUsername(),
                passwordUpdated.getNewPassword(),
                userUpdate.getEmail(),
                userUpdate.getPhotoUrl(),
                userUpdate.getBio(),
                userUpdate.getAddress(),
                userUpdate.getFriendsId(),
                userUpdate.getArticles(),
                userUpdate.getSharedArticles()
        );
        this.userRepository.deleteByEmail(userUpdate.getEmail());
        this.userRepository.save(newUser);
        System.out.println("password updated sucess");
    }

    public void handleUserFriendListAddedEvent(UserFriendListAdded userFriendListAdded) {
       Optional<User> u = this.userRepository.findById(userFriendListAdded.getEmail());
       User userUpdate=u.get();
       userUpdate.getFriendsId().add(userFriendListAdded.getEmailToAdd());
       this.userRepository.save(userUpdate);
       System.out.println(("friend added"));
    }

    public  void handleUserFriendListDeletedEvent(UserFriendListDeleted userFriendListDeleted) {
        Optional<User> u = this.userRepository.findById(userFriendListDeleted.getEmail());
        User userUpdate=u.get();
        userUpdate.getFriendsId().remove(userFriendListDeleted.getEmailToDelete());
        this.userRepository.save(userUpdate);
        System.out.println(("friend deleted"));

    }

    public  void handleArticleSharedEvent(ArticleShared articleShared) {
        //code
    }

    public void handleArticleTagsRefreshedEvent(ArticleTagsRefreshed articleTagsRefreshed) {
        //
    }
}
