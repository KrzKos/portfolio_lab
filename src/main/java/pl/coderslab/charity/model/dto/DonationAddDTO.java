package pl.coderslab.charity.model.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import pl.coderslab.charity.model.entity.Category;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class DonationAddDTO {

    private Integer quantity;
    private List<Category> categories;
    private Long institutionId;
    private String street;
    private String city;
    private String zipCode;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate pickUpDate;
    private LocalTime pickUpTime;
    private String pickUpComment;
}
