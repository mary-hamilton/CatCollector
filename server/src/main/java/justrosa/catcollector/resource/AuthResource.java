package justrosa.catcollector.resource;

import justrosa.catcollector.domain.dto.AuthSuccessDTO;
import justrosa.catcollector.domain.dto.SignupDTO;
import justrosa.catcollector.service.AuthService;
import justrosa.catcollector.service.AuthUserDetailsService;
import justrosa.catcollector.service.JwtService;
import justrosa.catcollector.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/auth")
public class AuthResource {

    private final AuthService authService;

    // do I want a separate AuthService as well?? I feel like it is doing something quite different to the
    // UserService. According to ChatGPT I am correct.

    //So in here I want to have my login and signup methods I think? My signup method can call the addUser
    // method I already have in UserService amd also generate and return a token

    //My login message also needs to generate and return a token, and a user I guess?

    public AuthResource(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<AuthSuccessDTO> signup(@RequestBody SignupDTO signupJSON) {
        AuthSuccessDTO authSuccessDTO = authService.signup(signupJSON);
        return new ResponseEntity<>(authSuccessDTO, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthSuccessDTO> login(Authentication authentication) {
        AuthSuccessDTO authSuccessDTO = authService.login(authentication);
        return new ResponseEntity<>(authSuccessDTO, HttpStatus.OK);
    }
}
