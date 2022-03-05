package Multiple.Choice.multiplechoice.service;

import Multiple.Choice.multiplechoice.models.security.MyUserDetail;
import Multiple.Choice.multiplechoice.models.security.User;
import Multiple.Choice.multiplechoice.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepo.findByUsername(username);
        if (optionalUser.isEmpty()) throw new UsernameNotFoundException("User not found");
        return optionalUser.map(MyUserDetail::new).get();
    }
}
