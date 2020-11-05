package pl.coderslab.charity.model.service.impl;

import org.assertj.core.api.Assertions;
import org.junit.experimental.categories.Categories;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.modelmapper.ModelMapper;
import pl.coderslab.charity.exeption.NotFoundException;
import pl.coderslab.charity.model.dto.DonationAddDTO;
import pl.coderslab.charity.model.dto.DonationDTO;
import pl.coderslab.charity.model.entity.Category;
import pl.coderslab.charity.model.entity.Donation;
import pl.coderslab.charity.model.repository.DonationRepository;
import pl.coderslab.charity.model.repository.InstitutionRepository;
import pl.coderslab.charity.model.service.DonationService;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.shouldHaveThrown;
import static org.mockito.Mockito.*;

class DefaultDonationServiceTest {


    DonationRepository donationRepository;
    InstitutionRepository institutionRepository;
    DonationService donationService;
    DonationAddDTO donationAddDTO;
    List<Category> categoryList;

    @BeforeEach
    void init() {
        donationRepository = Mockito.mock(DonationRepository.class);
        institutionRepository = Mockito.mock(InstitutionRepository.class);
        ModelMapper modelMapper = new ModelMapper();

        donationService = new DefaultDonationService(modelMapper,donationRepository,institutionRepository);

        Category category = new Category();
        category.setName("Ubrania");

        categoryList = new ArrayList<>();
        categoryList.add(category);

        donationAddDTO = Mockito.mock(DonationAddDTO.class);

    }

    @Test
    void shoudSaveDonation() {
        //given
        Mockito.when(donationAddDTO.getId()).thenReturn(1L);
        Mockito.when(donationAddDTO.getQuantity()).thenReturn(3);
        Mockito.when(donationAddDTO.getCategories()).thenReturn(categoryList);
        Mockito.when(donationAddDTO.getStreet()).thenReturn("Marszałkowska");
        //when
        donationService.create(donationAddDTO);
        //then
        ArgumentCaptor<Donation> captor = ArgumentCaptor.forClass(Donation.class);
        verify(donationRepository).save(captor.capture());

        Donation donation = captor.getValue();
        assertThat(donation).isNotNull();


    }

    @Test
    void shouldHaveAllDonationFields() {
        Mockito.when(donationAddDTO.getStreet()).thenReturn("Marszałkowska");
        //when
        donationService.create(donationAddDTO);
        //then
        ArgumentCaptor<Donation> captor = ArgumentCaptor.forClass(Donation.class);
        verify(donationRepository).save(captor.capture());

        Donation donation = captor.getValue();
        assertThat(donation)
                .hasFieldOrProperty("id")
                .hasFieldOrProperty("quantity")
                .hasFieldOrPropertyWithValue("street", "Marszałkowska");
    }

    @Test
    void shouldUpdateWithNewQuantity() {
        donationAddDTO = Mockito.mock(DonationAddDTO.class);
        Mockito.when(donationAddDTO.getId()).thenReturn(3L);
        Mockito.when(donationAddDTO.getQuantity()).thenReturn(5);

        donationService.update(donationAddDTO);

        ArgumentCaptor<Donation> captor = ArgumentCaptor.forClass(Donation.class);
        verify(donationRepository).save(captor.capture());

        assertThat(captor.getValue().getId()).isEqualTo(3L);
        assertThat(captor.getValue().getQuantity()).isEqualTo(5);

    }

    @Test
    void shouldThrowNotFindException() {
        DonationDTO donationDTO = new DonationDTO();
        donationDTO.setId(2L);
        DonationDTO donationDTO2 = new DonationDTO();
        donationDTO.setId(3L);
        List<DonationDTO> donationDTOS = new ArrayList<>();
        donationDTOS.add(donationDTO);
        donationDTOS.add(donationDTO2);


        NotFoundException nfe = Assertions.catchThrowableOfType(() -> donationService.findById(1L), NotFoundException.class);

        assertThat(nfe).hasMessageContaining("not found");




    }
}