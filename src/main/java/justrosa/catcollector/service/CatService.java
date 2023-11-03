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
        //should I put something in here just to check the user hasn't been deleted? Would require another
        //database call, not sure if that's bad or not

        //did I need to do any of this?? could I just have handled it all with ID? Under what circumstances
        //will the user actually be requesting a specific cat? If only from a list of already loaded cats
        //then the IDs will be available. I mean it does make the URL look nicer...

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

