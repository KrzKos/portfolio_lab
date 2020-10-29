package pl.coderslab.charity.model.service;

import pl.coderslab.charity.model.dto.UserCreateDTO;
import pl.coderslab.charity.model.dto.UserDTO;

public interface UserService {
    UserDTO findByUserName(String username);
    boolean create(UserCreateDTO userCreateDTO);
}
