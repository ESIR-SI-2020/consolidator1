package fr.esir.jxc.models.user.requests;

import lombok.Value;

@Value
public class AddUserFriendListRequest {

    private String email;
    private String emailToAdd;
}
