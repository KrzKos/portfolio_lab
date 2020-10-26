package pl.coderslab.charity.model.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.model.dto.InstitutionDTO;
import pl.coderslab.charity.model.entity.Institution;
import pl.coderslab.charity.model.repository.InstitutionRepository;
import pl.coderslab.charity.model.service.InstitutionService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class DefaultInstitutionService implements InstitutionService {
    private final ModelMapper modelMapper;
    private final InstitutionRepository institutionRepository;

    @Override
    public void create(InstitutionDTO institutionDTO) {
        Institution institution = modelMapper.map(institutionDTO,Institution.class);
        institution.setName("Jan");
        institutionRepository.save(institution);
    }

    @Override
    public InstitutionDTO findByName(String name) {
        Institution institution = institutionRepository.findByName(name);
        return modelMapper.map(institution,InstitutionDTO.class);
    }

    @Override
    public InstitutionDTO findById(long id) {
        Optional<Institution> institutionRepositoryById = institutionRepository.findById(id);
        return institutionRepositoryById.map(institution -> modelMapper.map(institution, InstitutionDTO.class)).
                orElseThrow(() -> new RuntimeException("Not found"));
    }

    @Override
    public List<InstitutionDTO> findAll() {
        List<Institution> institutions = institutionRepository.findAll();
        return institutions.stream()
                .map(institution -> modelMapper.map(institution,InstitutionDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void update(InstitutionDTO institutionDTO) {
        if (findById(institutionDTO.getId()) == null) {
            throw new NoSuchElementException("Nie znaleziono obiektu");
        }
        Institution institution = modelMapper.map(institutionDTO,Institution.class);
        institutionRepository.save(institution);

    }

    @Override
    public void delete(InstitutionDTO institutionDTO) {
        if (findById(institutionDTO.getId()) == null) {
            throw new NoSuchElementException("Nie znaleziono obiektu");
        }
        institutionRepository.delete(modelMapper.map(institutionDTO,Institution.class));
    }
}
