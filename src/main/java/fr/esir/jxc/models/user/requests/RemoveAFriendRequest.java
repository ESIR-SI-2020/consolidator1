package fr.esir.jxc.models.user.requests;

import lombok.Value;

@Value
public class RemoveAFriendRequest {

    private String email;
    private String emailToRemove;
}
