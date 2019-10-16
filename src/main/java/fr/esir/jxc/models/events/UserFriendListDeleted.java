package fr.esir.jxc.models.events;

import fr.esir.jxc.models.user.requests.DeleteFriendListRequest;
import lombok.Value;

@Value
public class UserFriendListDeleted {

    private String email;
    private String emailToDelete;

    public static UserFriendListDeleted of (DeleteFriendListRequest user) {

        return new UserFriendListDeleted(
                user.getEmai(),
                user.getEmailToDelete()
        );

    }
}
