package pl.coderslab.charity.model.service;

import pl.coderslab.charity.model.dto.DonationAddDTO;
import pl.coderslab.charity.model.dto.DonationDTO;

import java.util.List;

public interface DonationService {

    void create(DonationAddDTO donationAddDTO);
    Integer sumAllQuantity();
    Integer countDonation();
    List<DonationDTO> findAll();
    DonationDTO findById(long id);
    void delete(DonationDTO donationDTO);
}
