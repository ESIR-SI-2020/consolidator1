package fr.esir.jxc.models.events;

import fr.esir.jxc.models.user.requests.UpdatePasswordRequest;
import lombok.Value;

@Value
public class PasswordUpdated {

    private String email;
    private String newPassword;

    public static PasswordUpdated of (UpdatePasswordRequest updatePasswordRequest) {

        return new PasswordUpdated(
                updatePasswordRequest.getEmail(),
                Security.hash(updatePasswordRequest.getNewPassword())
        );
    }
}
