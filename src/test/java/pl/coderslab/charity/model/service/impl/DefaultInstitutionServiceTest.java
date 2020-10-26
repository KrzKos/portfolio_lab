package pl.coderslab.charity.model.service.impl;

import org.junit.jupiter.api.*;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import pl.coderslab.charity.model.dto.InstitutionDTO;
import pl.coderslab.charity.model.entity.Institution;
import pl.coderslab.charity.model.repository.InstitutionRepository;
import pl.coderslab.charity.model.service.InstitutionService;

import java.util.Optional;

@DisplayName("Default Institution Service Specification")
public class DefaultInstitutionServiceTest {

    InstitutionRepository institutionRepository;
    InstitutionService institutionService;

    @BeforeEach
    void setUp() {
        institutionRepository = Mockito.mock(InstitutionRepository.class);
        ModelMapper modelMapper = new ModelMapper();
        institutionService = new DefaultInstitutionService(modelMapper, institutionRepository);
    }

    @DisplayName("Create New Institution")
    @Nested
    class CreateNewInstitution {

        InstitutionDTO institutionToCreate;
        Institution institutionCreated;

        @BeforeEach
        void setUp() {
            institutionToCreate = new InstitutionDTO();
            institutionToCreate.setName("test-name");

            institutionCreated = new Institution();
            institutionCreated.setId(1L);
            institutionCreated.setName(institutionToCreate.getName());
        }

        @DisplayName(" - should save using repository")
        @Test
        public void test1() {
            Mockito.when(institutionRepository.save(ArgumentMatchers.any())).thenReturn(institutionCreated);

            institutionService.create(institutionToCreate);

            Mockito.verify(institutionRepository, Mockito.atLeastOnce()).save(ArgumentMatchers.any());
        }

        @DisplayName(" - should save given name")
        @Test
        public void test2() {
            Mockito.when(institutionRepository.save(institutionCreated)).thenReturn(institutionCreated);

            institutionService.create(institutionToCreate);

            Assertions.assertEquals("test-name", institutionToCreate.getName());
        }
    }

    @DisplayName("Find institution by")
    @Nested
    class FindInstitutionById {
        Institution institutionFound;
        InstitutionDTO institutionToFind;

        @BeforeEach
        void setUp() {
            institutionFound = new Institution();
            institutionFound.setId(1L);
            institutionFound.setName("Instytucja");
            institutionFound.setDescription("desc");

            institutionToFind = new InstitutionDTO();
            institutionToFind.setId(institutionFound.getId());
            institutionToFind.setName(institutionFound.getName());

        }

        @DisplayName(" name - should find institution by given any name")
        @Test
        public void test1() {
            Mockito.when(institutionRepository.findByName(Mockito.anyString())).thenReturn(institutionFound);

            InstitutionDTO result = institutionService.findByName(institutionToFind.getName());

            Assertions.assertNotNull(result);
        }

        @DisplayName(" id - should find institution by given id")
        @Test
        public void test() {
            Mockito.when(institutionRepository.findById(ArgumentMatchers.isNull())).thenThrow(new RuntimeException("Not founld"));

            institutionService.findById(1L);

            org.assertj.core.api.Assertions.shouldHaveThrown(RuntimeException.class);


        }

    }

}