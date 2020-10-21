package pl.coderslab.charity.model.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter @Setter @EqualsAndHashCode(of = "id")
@ToString
@NoArgsConstructor @AllArgsConstructor
public class BaseEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "created_on")
    private LocalDateTime createdOn;
    @Column(name = "updated_on")
    private LocalDateTime updatedOn;

    @PrePersist
    public void prePersist() {
        this.createdOn = LocalDateTime.now();
    }
    @PreUpdate
    public void preUpdate() {
        this.updatedOn = LocalDateTime.now();
    }

}
