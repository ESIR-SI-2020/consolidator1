package fr.esir.jxc.events;

import fr.esir.jxc.config.Security;
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
