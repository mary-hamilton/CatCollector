package justrosa.catcollector.resource;

import justrosa.catcollector.domain.dto.CatDTO;
import justrosa.catcollector.service.CatService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/cats")
public class CatResource {

    private final CatService catService;

    public CatResource(CatService catService) {
        this.catService = catService;
    }

    @PostMapping("/add")
    public ResponseEntity<CatDTO> addCat(JwtAuthenticationToken principal, @RequestBody CatDTO catDTO) {
        CatDTO addedCatDTO = catService.addCat(principal.getName(), catDTO);
        return new ResponseEntity<>(addedCatDTO, HttpStatus.CREATED);
    }
}
