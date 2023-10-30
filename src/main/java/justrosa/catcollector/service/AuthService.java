package justrosa.catcollector.service;

import justrosa.catcollector.domain.AuthUserDetails;
import justrosa.catcollector.domain.User;
import justrosa.catcollector.domain.dto.AuthSuccessDTO;
import justrosa.catcollector.domain.dto.SignupDTO;
import justrosa.catcollector.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService {

    private final UserService userService;

    private final UserRepository userRepository;

    private final AuthUserDetailsService authUserDetailsService;

    private final JwtService jwtService;

    private final PasswordEncoder passwordEncoder;


    public static final String ADMIN_ROLE = "ROLE_ADMIN";
    public static final String USER_ROLE = "ROLE_USER";

    public AuthService(UserService userService, UserRepository userRepository, AuthUserDetailsService authUserDetailsService, JwtService jwtService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.authUserDetailsService = authUserDetailsService;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    public AuthSuccessDTO signup(SignupDTO signupJSON) {
        if (userRepository.existsUserByUsername(signupJSON.getUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username not available");
        }
        User newUser = new User(signupJSON.getUsername(), passwordEncoder.encode(signupJSON.getPassword()), signupJSON.getFirstName(), signupJSON.getLastName(), USER_ROLE);
        userRepository.save(newUser);
        AuthUserDetails userDetails = (AuthUserDetails) authUserDetailsService.loadUserByUsername(newUser.getUsername());
        String jwt = jwtService.generateToken(userDetails);
        return new AuthSuccessDTO(newUser.dto(), jwt);
    }

    public AuthSuccessDTO login(Authentication authentication) {
        String jwt = jwtService.generateToken(authentication);
        User user = userRepository.findUserByUsername(authentication.getName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        return new AuthSuccessDTO(user.dto(), jwt);
    }
}
