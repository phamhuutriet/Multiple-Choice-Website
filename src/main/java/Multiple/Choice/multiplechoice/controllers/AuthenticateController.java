package Multiple.Choice.multiplechoice.controllers;

import Multiple.Choice.multiplechoice.models.Authenticate.AuthenticateRequest;
import Multiple.Choice.multiplechoice.models.Authenticate.AuthenticateResponse;
import Multiple.Choice.multiplechoice.models.security.MyUserDetail;
import Multiple.Choice.multiplechoice.models.security.User;
import Multiple.Choice.multiplechoice.repositories.UserRepo;
import Multiple.Choice.multiplechoice.security.filter.JwtUtil;
import Multiple.Choice.multiplechoice.service.MyUserDetailService;
import Multiple.Choice.multiplechoice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.flyway.FlywayDataSource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/authenticate")
public class AuthenticateController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailService myUserDetailService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepo userRepo;

    @PostMapping("")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticateRequest request) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        }
        catch (Exception e) {
            throw new Exception("Error" + e);
        }
        MyUserDetail userDetail = (MyUserDetail) myUserDetailService.loadUserByUsername(request.getUsername());
        String jwt = jwtUtil.generateToken(userDetail);

        Optional<User> optionalUser = userRepo.findByUsername(request.getUsername());
        if (optionalUser.isEmpty()) throw new Exception("username not found");
        int userId = optionalUser.get().getId();

        return ResponseEntity.ok(new AuthenticateResponse(jwt, userId));
    }

    @PostMapping("/register")
    public ResponseEntity<User> createNewUser(@RequestBody User newUser) {
        return ResponseEntity.ok(userService.createNewUser(newUser));
    }
}
