package pl.coderslab.charity.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserCreateDTO {


    private String username = this.email;
    @NotBlank(message = "Nie może być puste")
    private String email;
    @NotBlank(message = "Nie może być puste")
    private String password;
    @NotBlank(message = "Nie może być puste")
    private String rePassword;
    private String role;
    private boolean active;

}
