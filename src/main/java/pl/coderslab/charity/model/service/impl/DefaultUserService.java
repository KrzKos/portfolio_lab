package pl.coderslab.charity.model.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.model.dto.UserCreateDTO;
import pl.coderslab.charity.model.dto.UserDTO;
import pl.coderslab.charity.model.entity.User;
import pl.coderslab.charity.model.repository.UserRepository;
import pl.coderslab.charity.model.service.UserService;

@RequiredArgsConstructor
@Service
public class DefaultUserService implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDTO findByUserName(String username) {
        User user = userRepository.findByUsername(username);
        return modelMapper.map(user,UserDTO.class);
    }

    @Override
    public boolean create(UserCreateDTO userCreateDTO) {
        if (!userCreateDTO.getRePassword().equals(userCreateDTO.getPassword())) {
            return false;
        }
        User user = modelMapper.map(userCreateDTO, User.class);
        //user.setUsername(userCreateDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userCreateDTO.getPassword()));
        user.setActive(true);
        user.setRole("USER");
        userRepository.save(user);
        return true;
    }
}
