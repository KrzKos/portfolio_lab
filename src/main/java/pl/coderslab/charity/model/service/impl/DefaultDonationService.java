package pl.coderslab.charity.model.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.exeption.NotFoundException;
import pl.coderslab.charity.model.dto.DonationAddDTO;
import pl.coderslab.charity.model.dto.DonationDTO;
import pl.coderslab.charity.model.entity.Category;
import pl.coderslab.charity.model.entity.Donation;
import pl.coderslab.charity.model.entity.Institution;
import pl.coderslab.charity.model.repository.DonationRepository;
import pl.coderslab.charity.model.repository.InstitutionRepository;
import pl.coderslab.charity.model.service.DonationService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class DefaultDonationService implements DonationService {
    private final ModelMapper modelMapper;
    private final DonationRepository donationRepository;
    private final InstitutionRepository institutionRepository;

    @Override
    public void create(DonationAddDTO donationAddDTO) {
        log.info(donationAddDTO.toString());
        Donation donation = modelMapper.map(donationAddDTO, Donation.class);
        Optional<Institution> institutionRepositoryById = institutionRepository.findById(donationAddDTO.getInstitutionId());
        if (institutionRepositoryById.isPresent()) {
            donation.setInstitution(institutionRepositoryById.get());
        } else {
            donation.setInstitution(null);
        }

        List<Category> categoryList = donationAddDTO.getCategories().stream()
                .map(category -> modelMapper.map(category, Category.class))
                .collect(Collectors.toList());
        donation.setCategories(categoryList);
        donationRepository.save(donation);

    }

    @Override
    public Integer sumAllQuantity() {
        return donationRepository.sumQuantity();
    }

    @Override
    public Integer countDonation() {
        return donationRepository.countDonation();
    }

    @Override
    public List<DonationDTO> findAll() {
        List<Donation> all = donationRepository.findAll();
        List<DonationDTO> donationList = all.stream()
                .map(donation -> modelMapper.map(donation, DonationDTO.class))
                .collect(Collectors.toList());
        return donationList;
    }

    @Override
    public DonationDTO findById(long id) {
        Optional<Donation> result = donationRepository.findById(id);
        return result.map(donation -> modelMapper.map(donation, DonationDTO.class)).orElseThrow(() -> new NotFoundException(id));
    }

    @Override
    public void delete(DonationDTO donationDTO) {
        if (donationRepository.findById(donationDTO.getId()).isPresent()) {
            Donation donation = modelMapper.map(donationDTO, Donation.class);
            donationRepository.delete(donation);
        } else {
            throw new NotFoundException(donationDTO.getId());
        }
    }

    @Override
    public void update(DonationAddDTO donationAddDTO) {

        Donation donation = modelMapper.map(donationAddDTO,Donation.class);
        donationRepository.save(donation);

    }
}
