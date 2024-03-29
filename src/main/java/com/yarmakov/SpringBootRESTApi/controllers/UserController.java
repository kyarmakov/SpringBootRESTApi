package com.yarmakov.SpringBootRESTApi.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;
import com.yarmakov.SpringBootRESTApi.dto.UserRequest;
import com.yarmakov.SpringBootRESTApi.entities.User;
import com.yarmakov.SpringBootRESTApi.exceptions.NotValidJSONException;
import com.yarmakov.SpringBootRESTApi.exceptions.UserAlreadyExistsException;
import com.yarmakov.SpringBootRESTApi.exceptions.UserNotFoundException;
import com.yarmakov.SpringBootRESTApi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.InputStream;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody String requestStr) throws UserAlreadyExistsException,
            JsonProcessingException, NotValidJSONException {
        InputStream schemaAsStream = UserController.class.getClassLoader().getResourceAsStream("model/user.schema.json");
        JsonSchema schema = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V4).getSchema(schemaAsStream);

        ObjectMapper om = new ObjectMapper();
        JsonNode jsonNode = om.readTree(requestStr);

        Set<ValidationMessage> errors = schema.validate(jsonNode);

        String errorsCombined = "";
        for (ValidationMessage error : errors)
            errorsCombined += error.toString() + "\n";

        if (errors.size() > 0)
            throw new NotValidJSONException(errorsCombined);

        UserRequest userRequest = om.readValue(requestStr, UserRequest.class);

        return new ResponseEntity<>(userService.saveUser(userRequest), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") int id) throws UserNotFoundException {
        return ResponseEntity.ok(userService.findUserById(id));
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.findAllUsers());
    }
}
