package pl.coderslab.charity.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class CategoryDTO {
    private Long id;
    private String name;
}
