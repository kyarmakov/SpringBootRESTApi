package com.yarmakov.SpringBootRESTApi.services;

import com.yarmakov.SpringBootRESTApi.dto.UserRequest;
import com.yarmakov.SpringBootRESTApi.entities.User;
import com.yarmakov.SpringBootRESTApi.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(UserRequest userRequest) {
        User user = modelMapper.map(userRequest, User.class);

        return userRepository.save(user);
    }
}
