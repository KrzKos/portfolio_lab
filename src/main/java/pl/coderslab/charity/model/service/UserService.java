package pl.coderslab.charity.model.service;

import pl.coderslab.charity.model.dto.UserDTO;
import pl.coderslab.charity.model.entity.User;

public interface UserService {
    UserDTO findByUserName(String username);
    User create(User user);
}
