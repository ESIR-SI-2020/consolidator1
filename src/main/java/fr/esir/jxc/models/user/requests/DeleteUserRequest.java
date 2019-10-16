package fr.esir.jxc.models.user.requests;

import lombok.Value;

@Value
public class DeleteUserRequest {
    private String email;
}
