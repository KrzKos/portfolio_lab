package pl.coderslab.charity.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class InstitutionDTO {
    private Long id;
    @NotBlank
    private String name;
    private String description;
}
