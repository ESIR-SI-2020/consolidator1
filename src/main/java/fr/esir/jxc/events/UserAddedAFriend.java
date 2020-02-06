package fr.esir.jxc.events;

import fr.esir.jxc.models.user.requests.AddAFriendRequest;
import lombok.Value;

@Value
public class UserAddedAFriend {

    private String email;
    private String emailToAdd;

    public static UserAddedAFriend of (AddAFriendRequest user) {

        return new UserAddedAFriend(
                user.getEmail(),
                user.getEmailToAdd()
        );
    }
}
