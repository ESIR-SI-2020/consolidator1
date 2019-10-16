package fr.esir.jxc.models.events;

import fr.esir.jxc.models.user.requests.AddUserFriendListRequest;
import lombok.Value;

@Value
public class UserFriendListAdded {

    private String email;
    private String emailToAdd;

    public static UserFriendListAdded of (AddUserFriendListRequest user) {

        return new UserFriendListAdded(
                user.getEmail(),
                user.getEmailToAdd()
        );
    }
}
