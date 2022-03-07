package Multiple.Choice.multiplechoice.models.Authenticate;

import io.jsonwebtoken.Jwt;
import lombok.Getter;
import lombok.Setter;

public class AuthenticateResponse {
    @Getter @Setter
    private final String jwt;

    @Getter @Setter
    private final int userId;

    public AuthenticateResponse(String jwt, int userId) {
        this.jwt = jwt;
        this.userId = userId;
    }
}
