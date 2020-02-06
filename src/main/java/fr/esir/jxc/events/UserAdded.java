package fr.esir.jxc.events;

import java.util.List;

import fr.esir.jxc.config.Security;
import fr.esir.jxc.domain.models.Address;
import fr.esir.jxc.models.user.requests.AddUserRequest;
import lombok.Value;
import org.springframework.security.crypto.bcrypt.BCrypt;

@Value
public class UserAdded {

    String id;
    String username;
    String password;
    Address address;
    List<String> friends;

    public static UserAdded of(AddUserRequest user) {

        return new UserAdded(
                user.getEmail(),
                user.getUsername(),
                Security.hash(user.getPassword()),
                user.getAddress(),
                user.getFriends()

        );

    }
}
