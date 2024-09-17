package edu.escuelaing.PayNStay.Security;

import edu.escuelaing.PayNStay.Repository.User.UserDto;
import edu.escuelaing.PayNStay.Repository.User.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public JwtUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserDto> optionalUser = userRepository.findByEmail(username);
        if (optionalUser.isPresent()) {
            UserDto userEntity = optionalUser.get();
            List<SimpleGrantedAuthority> authorities = userEntity.getRoles().stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                    .toList();
            return new User(userEntity.getEmail(), userEntity.getPassword(), authorities);
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}

