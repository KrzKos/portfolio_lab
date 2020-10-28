package pl.coderslab.charity.model.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.model.dto.UserDTO;
import pl.coderslab.charity.model.entity.User;
import pl.coderslab.charity.model.repository.UserRepository;
import pl.coderslab.charity.model.service.UserService;

@RequiredArgsConstructor
@Service
public class DefaultUserService implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public UserDTO findByUserName(String username) {
        User user = userRepository.findByUsername(username);
        return modelMapper.map(user,UserDTO.class);
    }

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }
}
