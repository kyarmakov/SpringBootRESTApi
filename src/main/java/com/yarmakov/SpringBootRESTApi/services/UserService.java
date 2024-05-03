package com.yarmakov.SpringBootRESTApi.services;

import com.yarmakov.SpringBootRESTApi.dto.UserRequest;
import com.yarmakov.SpringBootRESTApi.entities.User;
import com.yarmakov.SpringBootRESTApi.exceptions.UserAlreadyExistsException;
import com.yarmakov.SpringBootRESTApi.exceptions.UserNotFoundException;
import com.yarmakov.SpringBootRESTApi.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(UserRequest userRequest) throws UserAlreadyExistsException {
        Optional<User> userFoundByEmail = userRepository.findByEmail(userRequest.getEmail());

        if (userFoundByEmail.isPresent())
            throw new UserAlreadyExistsException("User with such email already exists");

        User user = modelMapper.map(userRequest, User.class);

        return userRepository.save(user);
    }

    public User findUserById(int id) throws UserNotFoundException {
        findUserOrThrowException(id);

        return userRepository.findById(id).get();
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUserById(int id) throws UserNotFoundException {
        findUserOrThrowException(id);

        userRepository.deleteById(id);
    }

    public User updateUser(UserRequest userRequest, int id) throws UserNotFoundException {
        findUserOrThrowException(id);

        User user = modelMapper.map(userRequest, User.class);
        user.setId(id);

        return userRepository.save(user);
    }

    private void findUserOrThrowException(int id) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty())
            throw new UserNotFoundException("User with id: " + id + " cannot be found");
    }
}
