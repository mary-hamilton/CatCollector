package justrosa.catcollector.service;

import justrosa.catcollector.domain.User;
import justrosa.catcollector.domain.dto.UserDTO;
import justrosa.catcollector.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

//Stereotype annotation v
@Service
// Means operations performed in here are transactions; basically they need to be fully completed and leave
// the database in a stable state or else be entirely rolled back.
@Transactional
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // business logic in here
    public List<UserDTO> getAllUsers() {
        return userRepository
                .findAll()
                .stream()
                .map(User::dto)
                .collect(Collectors.toList());
    }

    public UserDTO addUser(UserDTO userToAddDTO) {
        User userToAdd = new User(userToAddDTO.getUsername(), passwordEncoder.encode(userToAddDTO.getPassword()), userToAddDTO.getFirstName(), userToAddDTO.getLastName(), userToAddDTO.getRoles());
        UserDTO savedUser = userRepository.save(userToAdd).dto();
        return savedUser;
    }

    public UserDTO getUser(String user) {
        Pattern pattern = Pattern.compile("^\\d+$");
        Matcher matcher = pattern.matcher(user);
        if (matcher.find()) {
            int userID = Integer.parseInt(user);
            User foundUser = userRepository.findById(userID)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found"));
            return foundUser.dto();
        } else {
            User foundUser = userRepository.findUserByUsername(user)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found"));
            return foundUser.dto();
        }
    }
}
