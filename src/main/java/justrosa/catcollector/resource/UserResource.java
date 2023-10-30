package justrosa.catcollector.resource;



import justrosa.catcollector.domain.dto.UserDTO;
import justrosa.catcollector.service.JwtService;
import justrosa.catcollector.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Stereotype annotation v
@Controller

// Most annotations in here are Spring Web annotations
@RequestMapping("/api/users")
public class UserResource {

    // dependency injection

    private final UserService userService;


    public UserResource(UserService userService) {
        this.userService = userService;
    }

    // I need my HTTP endpoints in here

    // one to get all the users out of the database
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> allUsers = userService.getAllUsers();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    // one to add a user to the database
    // here we have to get our data to save our of the http request body, we do this using
    // an annotation in the parameters
    @PostMapping("/add")
    public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO userToAddDTO) {
        UserDTO addedUser =  userService.addUser(userToAddDTO);
        return new ResponseEntity<>(addedUser, HttpStatus.CREATED);
    }

    @GetMapping("/{user}")
    public ResponseEntity<UserDTO> getUser(@PathVariable("user") String user) {
        UserDTO foundUser = userService.getUser(user);
        return new ResponseEntity<>(foundUser, HttpStatus.OK);
    }
}
