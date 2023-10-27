package justrosa.catcollector.service;

import justrosa.catcollector.domain.User;
import justrosa.catcollector.domain.dto.UserDTO;
import justrosa.catcollector.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

//Stereotype annotation v
@Service
// Means operations performed in here are transactions; basically they need to be fully completed and leave
// the database in a stable state or else be entirely rolled back.
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
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
        User userToAdd = new User(userToAddDTO.getUsername(), userToAddDTO.getPassword(), userToAddDTO.getFirstName(), userToAddDTO.getLastName());
        UserDTO savedUser = userRepository.save(userToAdd).dto();
        return savedUser;
    }
}
