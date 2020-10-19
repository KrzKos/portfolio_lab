package pl.coderslab.charity.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter @Setter @ToString
@Entity
@Table(name = "donation")
public class Donation extends BaseEntity{

    private Integer quantity;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private List<Category> categories;
    @OneToOne
    private Institution institution;
    private String street;
    @Column(name = "zip_code")
    private String zipCode;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "pick_up_date", nullable = false)
    private LocalDate pickUpDate;
    @Column(name = "pick_up_time", nullable = false)
    private LocalTime pickUpTime;
    @Column(name = "pick_up_comment")
    private String pickUpComment;

}
