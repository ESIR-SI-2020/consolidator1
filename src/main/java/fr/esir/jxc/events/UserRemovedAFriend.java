package fr.esir.jxc.events;

import fr.esir.jxc.models.user.requests.RemoveAFriendRequest;
import lombok.Value;

@Value
public class UserRemovedAFriend {

    private String email;
    private String emailToRemove;

    public static UserRemovedAFriend of (RemoveAFriendRequest user) {

        return new UserRemovedAFriend(
                user.getEmail(),
                user.getEmailToRemove()
        );

    }
}
