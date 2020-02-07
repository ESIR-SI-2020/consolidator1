package fr.esir.jxc.models.user.requests;


import fr.esir.jxc.domain.models.Address;
import lombok.Value;

import java.util.List;

@Value
public class AddUserRequest {

    String username;
    String password;
    String email;
    Address address;
    List<String> friends;

}
