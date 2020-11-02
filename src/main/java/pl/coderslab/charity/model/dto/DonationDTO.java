package pl.coderslab.charity.model.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import pl.coderslab.charity.model.entity.Category;
import pl.coderslab.charity.model.entity.Institution;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class DonationDTO {

    private long id;
    private Integer quantity;
    private List<Category> categories;

    private Institution institution;
    private String street;
    private String city;

    private String zipCode;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate pickUpDate;

    private LocalTime pickUpTime;

    private String pickUpComment;
}
