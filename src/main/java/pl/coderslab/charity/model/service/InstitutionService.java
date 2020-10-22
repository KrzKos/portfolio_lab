package pl.coderslab.charity.model.service;

import pl.coderslab.charity.model.dto.InstitutionDTO;
import pl.coderslab.charity.model.entity.Institution;

import java.util.List;

public interface InstitutionService {

    void create(InstitutionDTO institutionDTO);
    InstitutionDTO findById(long id);
    InstitutionDTO findByName(String name);
    List<InstitutionDTO> findAll();
    void update(InstitutionDTO institutionDTO);
    void delete(InstitutionDTO institutionDTO);
}
