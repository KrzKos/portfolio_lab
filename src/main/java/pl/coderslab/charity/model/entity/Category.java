package pl.coderslab.charity.model.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "category")
public class Category extends BaseEntity{
    @Column(nullable = false)
    private String name;
}
