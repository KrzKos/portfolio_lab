package pl.coderslab.charity.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter @Setter @ToString
@Entity
@Table(name = "category")
public class Category extends BaseEntity{
    @Column(nullable = false)
    private String name;
}
