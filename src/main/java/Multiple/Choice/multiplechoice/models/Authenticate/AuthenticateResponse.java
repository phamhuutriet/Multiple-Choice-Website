package Multiple.Choice.multiplechoice.models.Authenticate;

import io.jsonwebtoken.Jwt;
import lombok.Getter;
import lombok.Setter;

public class AuthenticateResponse {
    @Getter @Setter
    private final String jwt;

    public AuthenticateResponse(String jwt) {
        this.jwt = jwt;
    }
}
