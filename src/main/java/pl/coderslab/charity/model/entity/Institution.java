package pl.coderslab.charity.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter @Setter @ToString
@Entity
@Table(name = "institution")
public class Institution extends BaseEntity{
    private String name;
    private String description;
}
