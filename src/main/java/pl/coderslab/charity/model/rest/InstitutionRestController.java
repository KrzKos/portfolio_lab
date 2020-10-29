package pl.coderslab.charity.model.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.model.dto.InstitutionDTO;
import pl.coderslab.charity.model.service.InstitutionService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/institutions")
public class InstitutionRestController {

    private final InstitutionService institutionService;

    @GetMapping
    public List<InstitutionDTO> getAllInstitutions() {
        return institutionService.findAll();
    }

    @PostMapping
    // 201 Create + nagłówek Location zawierają adres utworzonego zasobu, np. /api/institutions/81
    public ResponseEntity saveInstitution(@Valid @RequestBody InstitutionDTO institution) {
        institutionService.create(institution);
        return ResponseEntity.created(URI.create("/api/institutions/" + institution.getId())).build();
//        return ResponseEntity.status(HttpStatus.CREATED).location(URI.create("/api/institutions/" + institution.getId())).build();
    }

    @GetMapping("/{id}")
    // 200 OK - kiedy znaleziono + obiekt
    // 404 Not found - jeżeli nie znaleziono
    public ResponseEntity getOne(@PathVariable Long id) {
        InstitutionDTO institution = institutionService.findById(id);
        if (institution != null) {
            return ResponseEntity.ok(institution);
//            return ResponseEntity.status(HttpStatus.OK).body(institution);
        } else {
            return ResponseEntity.notFound().build();
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{id}")
    // 200 OK
    public void saveEditedInstitution(@PathVariable long id, @Valid @RequestBody InstitutionDTO institution) {
        if (institution.getId() != id) {
            // ???
        }
        institutionService.update(institution);
    }

    @DeleteMapping("/{id}")
    // 200 OK
    // 204 No content
    public ResponseEntity deleteInstitutionById(@PathVariable long id){
        InstitutionDTO institution = institutionService.findById(id);
        if (institution == null) {
            return ResponseEntity.notFound().build();
        }
        institutionService.delete(institution);
        return ResponseEntity.noContent().build();
    }


}
