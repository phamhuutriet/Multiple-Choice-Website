package Multiple.Choice.multiplechoice.models.Authenticate;

import lombok.Getter;
import lombok.Setter;

public class AuthenticateRequest {
    @Getter @Setter
    private String username;

    @Getter @Setter
    private String password;

    public AuthenticateRequest() {
    }

    public AuthenticateRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
