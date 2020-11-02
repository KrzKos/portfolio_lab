package pl.coderslab.charity.controller.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.model.dto.DonationAddDTO;
import pl.coderslab.charity.model.dto.DonationDTO;
import pl.coderslab.charity.model.service.DonationService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/donation")
public class DonationApiController {
    private final DonationService donationService;

    @GetMapping
    public List<DonationDTO> getAllDonations() {
        return donationService.findAll();
    }

    @PostMapping
    public ResponseEntity saveDonation(@Valid @RequestBody DonationAddDTO donation) {
        donationService.create(donation);
        return ResponseEntity.created(URI.create("/api/donation/" + donation.getId())).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity showDonation(@PathVariable long id) {
        DonationDTO result = donationService.findById(id);
        if (result != null) {
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteDonation(@PathVariable long id, )

}
