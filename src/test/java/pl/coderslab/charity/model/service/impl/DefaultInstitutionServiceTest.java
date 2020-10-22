package pl.coderslab.charity.model.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import pl.coderslab.charity.model.dto.InstitutionDTO;
import pl.coderslab.charity.model.entity.Institution;
import pl.coderslab.charity.model.repository.InstitutionRepository;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DefaultInstitutionServiceTest {
    @InjectMocks
    private DefaultInstitutionService institutionService;
    @Mock
    private InstitutionRepository institutionRepository;
    @Mock
    private ModelMapper modelMapper;

    @Test
    public void givenNewInstitution_whenSave_thenReturnInstitution() {
        //given
        Institution institution = new Institution();
        institution.setId(1L);
        institution.setName("Instytucja");

        InstitutionDTO institutionDTO = modelMapper.map(institution, InstitutionDTO.class);

        when(institutionRepository.save(institution)).thenReturn(institution);
        verify(institutionService).create(institutionDTO);

        assertNotNull(institutionDTO);

    }

}