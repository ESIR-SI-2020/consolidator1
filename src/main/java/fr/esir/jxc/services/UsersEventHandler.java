package fr.esir.jxc.services;


import fr.esir.jxc.domain.models.User;
import fr.esir.jxc.events.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UsersEventHandler {
    private final UsersWriteService usersWriteService;
    private final UsersReadService usersReadService;

    @Autowired
    public UsersEventHandler(
            @Autowired UsersReadService usersReadService,
            @Autowired UsersWriteService usersWriteService
    ) {
        this.usersWriteService = usersWriteService;
        this.usersReadService = usersReadService;
    }

    public void handleUserCreatedEvent(UserAdded userCreated) {
       final User newUser = new User(
               userCreated.getId(),
               userCreated.getUsername(),
               BCrypt.hashpw(userCreated.getPassword(),BCrypt.gensalt(4)),
               userCreated.getAddress(),
               userCreated.getFriends()
        );
        User resultingUser = this.usersWriteService.save(newUser);
        System.out.println("Successfully created user: " + resultingUser.toString());
    }

    public  void handleUserDeletedEvent(UserDeleted userDeleted) {

        usersWriteService.delete(userDeleted.getEmail());
        System.out.println("Successfully deleted user: " + userDeleted.toString());
    }


    public void handlePasswordUpdatedEvent(PasswordUpdated passwordUpdated) {
   this.usersReadService.getUserById(passwordUpdated.getEmail())
                .ifPresent(sourceUser ->{
                    final User userUpdated = new User(
                            sourceUser.getEmail(),
                            sourceUser.getUsername(),
                            passwordUpdated.getNewPassword(),
                            sourceUser.getAddress(),
                            sourceUser.getFriends()
                    );
                    usersWriteService.save(userUpdated);
                    System.out.println("password updated sucess");
                });



    }

    public void handleUserAddedFriendEvent(UserAddedAFriend userAddedAFriend) {
        this.usersReadService.getUserById(userAddedAFriend.getEmail())
                .ifPresent(sourceUser ->{
                     List<String> lst;
                    if(sourceUser.getFriends() == null) {
                        lst = new ArrayList<String>();
                    }
                    else{
                        lst = sourceUser.getFriends();
                    }
                    lst.add(userAddedAFriend.getEmailToAdd());
                    final User userUpdated = new User(
                            sourceUser.getEmail(),
                            sourceUser.getUsername(),
                            sourceUser.getPassword(),
                            sourceUser.getAddress(),
                            lst
                    );
                 User user =   usersWriteService.save(userUpdated);
                    System.out.println("friend added "+user.getFriends());

                });

    }

    public  void handleUserRemovedAFriendEvent(UserRemovedAFriend userRemovedAFriend) {
        this.usersReadService.getUserById(userRemovedAFriend.getEmail())
                .ifPresent(sourceUser ->{
                    final List<String> lst = sourceUser.getFriends();
                    if(lst != null){
                        while(lst.contains(userRemovedAFriend.getEmailToRemove())) {
                            lst.remove(userRemovedAFriend.getEmailToRemove());
                        }
                        final User userUpdated = new User(
                                sourceUser.getEmail(),
                                sourceUser.getUsername(),
                                sourceUser.getPassword(),
                                sourceUser.getAddress(),
                                lst
                        );
                     User user = usersWriteService.save(userUpdated);
                        System.out.println(("friend deleted" + user.getFriends()));
                    }
                    else{
                        System.out.println("no friends to delete");
                    }

                });


    }



}
