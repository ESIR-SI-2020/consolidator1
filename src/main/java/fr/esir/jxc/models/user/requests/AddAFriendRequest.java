package fr.esir.jxc.models.user.requests;

import lombok.Value;

@Value
public class AddAFriendRequest {

    private String email;
    private String emailToAdd;
}
