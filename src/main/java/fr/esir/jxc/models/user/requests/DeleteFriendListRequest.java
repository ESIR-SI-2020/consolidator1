package fr.esir.jxc.models.user.requests;

import lombok.Value;

@Value
public class DeleteFriendListRequest {

    private String emai;
    private String emailToDelete;
}
