package justrosa.catcollector.service;

import justrosa.catcollector.domain.Cat;
import justrosa.catcollector.domain.User;
import justrosa.catcollector.domain.dto.CatDTO;
import justrosa.catcollector.repository.CatRepository;
import justrosa.catcollector.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
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

    public boolean userIsAdmin(JwtAuthenticationToken principal) {
        return principal.getAuthorities()
                .stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));
    }

    public boolean ownerOrAdmin(JwtAuthenticationToken principal, Cat cat) {
        return principal.getName().equals(cat.getCollector().getUsername()) || userIsAdmin(principal);
    }

    public Cat findCatById(Integer catID) {
        return catRepository.findById(catID)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cat not found"));
    }

    public CatDTO addCat(JwtAuthenticationToken principal, CatDTO catDTO) {
        User collector = userRepository.findUserByUsername(principal.getName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        if (collector.getCats().stream().noneMatch(cat -> cat.getPrimaryName().equals(catDTO.getPrimaryName()))) {
            Cat newCat = new Cat(catDTO.getPrimaryName(), catDTO.getCoatColours(), catDTO.getCoatLength(), collector, catDTO.getSpottedLocations());
            return catRepository.save(newCat).dtoWithCollector();
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cat name not available");
        }
    }

    public List<CatDTO> getCats(JwtAuthenticationToken principal) {
        // I want to pull all the cats that are owned by the user. So I should search for the user and then
        // so the way I've currently got this set up I can just call getUser which returns a userDTO with cats,
        // and stream user.getCats to dto?
        User foundUser = userRepository.findUserByUsername(principal.getName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        return foundUser.getCats()
                .stream()
                .map(Cat::dto)
                .collect(Collectors.toList());
    }

    public CatDTO getCat(JwtAuthenticationToken principal, Integer catID) {
        //should I put something in here just to check the user hasn't been deleted? Would require another
        //database call, not sure if that's bad or not

        //did I need to do any of this?? could I just have handled it all with ID? Under what circumstances
        //will the user actually be requesting a specific cat? If only from a list of already loaded cats
        //then the IDs will be available. I mean it does make the URL look nicer...

        //sadly I do need to do this with ID. A learning experience!

//        String concatId = principal.getName() + catName;
//         Cat foundCat = catRepository.findCatByConcatId(concatId)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cat not found"));
//         if(principal.getName().equals(foundCat.getCollector().getUsername()) ||
//                 principal.getAuthorities()
//                 .stream()
//                 .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"))) {
//            return foundCat.dto();
//         } else {
//             throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access denied");
//         }
        if (!userRepository.existsUserByUsername(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User does not exist");
        }
        Cat foundCat = findCatById(catID);
         if(ownerOrAdmin(principal, foundCat)) {
            return foundCat.dto();
         } else {
             throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access denied");
         }
    }

    public List<CatDTO> getAllTheCats() {
        return catRepository.findAll()
                .stream()
                .map(Cat::dtoWithCollector)
                .collect(Collectors.toList());
    }

    public CatDTO editCat(JwtAuthenticationToken principal, Integer catID, CatDTO catDTO) {
        // I will assume that any unchanged values are going to get repopulated with their original values
        //client-side, therefore I don't need to handle that here
        // I could handle it here with if (x != null) etc BUT that would mean you couldn't delete fields I think?
        // Need to think about what I want to happen if someone client side wants to delete the contents of
        // a field - do I want to set them to null or to empty (I would think null?) and how will I achieve
        // that client side - maybe have a button to delete field contents in which case I send one response
        // back, whereas if they start doing something in that field and then delete it (eg. it is empty)
        // I leave the value unchanged server-side.
        // So something like:
        // if (!catDTO.get<field>().isEmpty()) {
        //     cat.set<field>(catDTO.get<field>())
        //}
        Cat catToEdit = findCatById(catID);
        if(ownerOrAdmin(principal, catToEdit)) {
            catToEdit.setPrimaryName(catDTO.getPrimaryName());
            catToEdit.setNames(catDTO.getNames());
            catToEdit.setCoatColours(catDTO.getCoatColours());
            catToEdit.setCoatLength(catDTO.getCoatLength());
            // this line would only be used in admin requests I think? A user cannot reassign one of their cats
            // to another user
            catToEdit.setCollector(userRepository.findUserByUsername(catDTO.getCollectorUsername())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User does not exist")));
            catToEdit.setTimesSpotted(catDTO.getTimesSpotted());
            catToEdit.setSpottedLocations(catDTO.getSpottedLocations());
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access denied");
        }
        return catRepository.save(catToEdit).dto();
    }

}

