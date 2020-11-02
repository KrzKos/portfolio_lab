package pl.coderslab.charity.controller.api;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.exeption.NotFoundException;
import pl.coderslab.charity.model.dto.InstitutionDTO;
import pl.coderslab.charity.model.service.InstitutionService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/institution")
public class InstitutionApiController {
    private final InstitutionService institutionService;

    @GetMapping
    public List<InstitutionDTO> getAllInstitution(){
        return institutionService.findAll();
    }

    @PostMapping
    public ResponseEntity saveInstitution(@Valid @RequestBody InstitutionDTO institution, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(ResponseErrors.of(result));
        }
        institutionService.create(institution);
        return ResponseEntity.created(URI.create("/api/institution/" + institution.getId())).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity findInstitution(@PathVariable long id) {
        InstitutionDTO result = institutionService.findById(id);
        if (result != null) {
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public void update(@PathVariable long id, @Valid @RequestBody InstitutionDTO institutionDTO) {
        if(institutionDTO.getId() != id) {
            throw new NotFoundException(id);
        }
        institutionService.update(institutionDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable long id) {
        InstitutionDTO institutionDTO = institutionService.findById(id);
        if(institutionDTO == null) {
            return ResponseEntity.notFound().build();
        }
        institutionService.delete(institutionDTO);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
