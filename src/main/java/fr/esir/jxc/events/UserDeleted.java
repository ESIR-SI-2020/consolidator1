package fr.esir.jxc.events;

import fr.esir.jxc.models.user.requests.DeleteUserRequest;
import lombok.Value;

@Value
public class UserDeleted  {
    private String email;

    public static UserDeleted of (DeleteUserRequest user) {

        return new UserDeleted(user.getEmail());
    }
}
