package pl.coderslab.charity.model.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.exeption.NotFoundException;
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
@Slf4j
public class DefaultInstitutionService implements InstitutionService {
    private final ModelMapper modelMapper;
    private final InstitutionRepository institutionRepository;

    @Override
    public InstitutionDTO create(InstitutionDTO institutionDTO) {
        Institution institution = modelMapper.map(institutionDTO,Institution.class);
        Institution savedInstitution = institutionRepository.save(institution);

        return modelMapper.map(savedInstitution,  InstitutionDTO.class);
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
                orElseThrow(() -> new NotFoundException(id));
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
        if (institutionRepository.findById(institutionDTO.getId()) == null) {
            throw new NotFoundException(institutionDTO.getId());
        }
        Institution institution = modelMapper.map(institutionDTO,Institution.class);
        log.info(institutionDTO.toString());
        institution.setName(institutionDTO.getName());
        institutionRepository.save(institution);

    }

    @Override
    public void delete(InstitutionDTO institutionDTO) {
        if (findById(institutionDTO.getId()) == null) {
            throw new NotFoundException(institutionDTO.getId());
        }
        institutionRepository.delete(modelMapper.map(institutionDTO,Institution.class));
    }
}
