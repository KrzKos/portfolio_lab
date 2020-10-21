package pl.coderslab.charity.model.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.model.dto.DonationAddDTO;
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
public class DefaultDonationService implements DonationService {
    private final ModelMapper modelMapper;
    private final DonationRepository donationRepository;
    private final InstitutionRepository institutionRepository;
    @Override
    public void create(DonationAddDTO donationAddDTO) {
        Donation donation = modelMapper.map(donationAddDTO, Donation.class);
        Optional<Institution> institutionRepositoryById = institutionRepository.findById(donationAddDTO.getInstitutionId());
        if (institutionRepositoryById.isPresent()) {
            donation.setInstitution(institutionRepositoryById.get());
        } else {
            donation.setInstitution(null);
        }

        List<Category> categoryList = donationAddDTO.getCategories().stream()
                .map(category -> modelMapper.map(category,Category.class))
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
}
