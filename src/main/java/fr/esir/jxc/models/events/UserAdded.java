package fr.esir.jxc.models.events;

import java.util.UUID;

import fr.esir.jxc.models.user.requests.AddUserRequest;
import lombok.Value;

@Value
public class UserAdded {

    String id;
    String username;
    String password;
    String email;

    public static UserAdded of(AddUserRequest user) {

        return new UserAdded(
                UUID.randomUUID().toString(),
                user.getUsername(),
                Security.hash(user.getPassword()),
                user.getEmail()
        );
    }
}
