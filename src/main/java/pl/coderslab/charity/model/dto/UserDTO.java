package pl.coderslab.charity.model.dto;

import lombok.Data;

@Data
public class UserDTO {

    private String username;
    private String password;
    private String email;
    private boolean active;
    private String role;
}
