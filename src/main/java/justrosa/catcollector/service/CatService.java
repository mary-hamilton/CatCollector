package justrosa.catcollector.service;

import justrosa.catcollector.domain.Cat;
import justrosa.catcollector.domain.User;
import justrosa.catcollector.domain.dto.CatDTO;
import justrosa.catcollector.repository.CatRepository;
import justrosa.catcollector.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.stream.Collectors;

@Service
@Transactional
public class CatService {

    private final CatRepository catRepository;

    private final UserRepository userRepository;

    public CatService(CatRepository catRepository, UserRepository userRepository) {
        this.catRepository = catRepository;
        this.userRepository = userRepository;
    }

    public CatDTO addCat(String username, CatDTO catDTO) {
        User collector = userRepository.findUserByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found"));
        if (collector.getCats().stream().noneMatch(cat -> cat.getPrimaryName().equals(catDTO.getPrimaryName()))) {
            Cat newCat = new Cat(catDTO.getPrimaryName(), catDTO.getCoatColours(), catDTO.getCoatLength(), collector, catDTO.getSpottedLocations());
            return catRepository.save(newCat).dto();

        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cat name not available");
        }
    }
}

