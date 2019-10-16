package fr.esir.jxc.models.user.requests;


import lombok.Value;

@Value
public class AddUserRequest {

    String username;
    String password;
    String email;

}
