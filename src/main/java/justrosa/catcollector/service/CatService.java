package justrosa.catcollector.service;

import justrosa.catcollector.domain.Cat;
import justrosa.catcollector.domain.User;
import justrosa.catcollector.domain.dto.CatDTO;
import justrosa.catcollector.repository.CatRepository;
import justrosa.catcollector.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;


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
        String concatId = username + catDTO.getPrimaryName();
        User collector = userRepository.findUserByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found"));
        if (collector.getCats().stream().noneMatch(cat -> cat.getPrimaryName().equals(catDTO.getPrimaryName()))) {
            Cat newCat = new Cat(catDTO.getPrimaryName(), catDTO.getCoatColours(), catDTO.getCoatLength(), collector, catDTO.getSpottedLocations());
            newCat.setConcatId(concatId);
            return catRepository.save(newCat).dtoWithCollector();
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cat name not available");
        }
    }

    public CatDTO getCat(JwtAuthenticationToken principal, String catName) {
        // check if the user owns the cat (OR is an admin)
        //if so, get the cat data out of the database
        //so can we just pull the collector ID using one of the 18 repository methods?
        String concatId = principal.getName() + catName;
         Cat foundCat = catRepository.findCatByConcatId(concatId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cat not found"));
         if(principal.getName().equals(foundCat.getCollector().getUsername()) ||
                 principal.getAuthorities()
                 .stream()
                 .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"))) {
            return foundCat.dto();
         } else {
             throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access denied");
         }
    }
}

