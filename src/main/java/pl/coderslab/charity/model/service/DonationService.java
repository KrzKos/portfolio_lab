package pl.coderslab.charity.model.service;

import pl.coderslab.charity.model.dto.DonationAddDTO;

public interface DonationService {

    void create(DonationAddDTO donationAddDTO);
    Integer sumAllQuantity();
    Integer countDonation();
}
