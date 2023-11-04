package justrosa.catcollector.resource;

import justrosa.catcollector.domain.dto.CatDTO;
import justrosa.catcollector.service.CatService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/cats")
public class CatResource {

    private final CatService catService;

    public CatResource(CatService catService) {
        this.catService = catService;
    }

    @PostMapping("/add")
    public ResponseEntity<CatDTO> addCat(JwtAuthenticationToken principal, @RequestBody CatDTO catDTO) {
        CatDTO addedCatDTO = catService.addCat(principal, catDTO);
        return new ResponseEntity<>(addedCatDTO, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<CatDTO>> getCats(JwtAuthenticationToken principal) {
        List<CatDTO> cats = catService.getCats(principal);
        return new ResponseEntity<>(cats, HttpStatus.OK);
    }

    @GetMapping("/{catID}")
    public ResponseEntity<CatDTO> getCat(JwtAuthenticationToken principal, @PathVariable("catID") Integer catID) {
        CatDTO foundCat = catService.getCat(principal, catID);
        return new ResponseEntity<>(foundCat, HttpStatus.OK);
    }

    @GetMapping("/allthecats")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<CatDTO>> getAllTheCats() {
        List<CatDTO> allTheCats = catService.getAllTheCats();
        return new ResponseEntity<>(allTheCats, HttpStatus.OK);
    }

    @PatchMapping("/{catID}/edit")
    public ResponseEntity<CatDTO> editCat(JwtAuthenticationToken principal, @PathVariable("catID") Integer catID, @RequestBody CatDTO catDTO) {
        CatDTO editedCatDTO = catService.editCat(principal, catID, catDTO);
        return new ResponseEntity<>(editedCatDTO, HttpStatus.OK);
    }
}
