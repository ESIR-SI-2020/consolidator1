package fr.esir.jxc.models.user.requests;

import lombok.Value;

@Value
public class UpdatePasswordRequest {

    private String email;
    private String newPassword;
}
