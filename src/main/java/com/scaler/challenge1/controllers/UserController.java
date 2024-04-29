package com.scaler.challenge1.controllers;

import com.scaler.challenge1.dtos.UserRequestDTO;
import com.scaler.challenge1.dtos.UserResponseDTO;
import com.scaler.challenge1.dtos.ResponseStatus;
import com.scaler.challenge1.models.User;
import com.scaler.challenge1.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController
{
    private UserService userService;

    @Autowired
    public UserController(UserService userService)
    {
        this.userService = userService;
    }

    @GetMapping
    private List<User> getAllUsers()
    {
        return this.userService.getAllUsers();
    }

    @GetMapping("/{id}")
    private User getUserById(@PathVariable(name = "id") int id)
    {
        User user = null;

        try
        {
            user = this.userService.findById(id);
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }

        return user;
    }

    @PostMapping
    private UserResponseDTO createUser(@RequestBody UserRequestDTO requestDTO)
    {
        UserResponseDTO responseDTO = new UserResponseDTO();

        try
        {
            User user = this.userService.createUser(requestDTO.getName(), requestDTO.getEmail(), requestDTO.getPhoneNumber());
            responseDTO.setUser(user);
            responseDTO.setResponseStatus(ResponseStatus.SUCCESS);
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
            responseDTO.setResponseStatus(ResponseStatus.FAILURE);
        }

        return responseDTO;
    }

    @PutMapping("/{id}")
    private UserResponseDTO updateUser(@RequestBody UserRequestDTO requestDTO, @PathVariable(name = "id") int userId)
    {
        UserResponseDTO responseDTO = new UserResponseDTO();

        try
        {
            User user = this.userService.updateUser(userId, requestDTO.getName(), requestDTO.getEmail(), requestDTO.getPhoneNumber());
            responseDTO.setUser(user);
            responseDTO.setResponseStatus(ResponseStatus.SUCCESS);
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
            responseDTO.setResponseStatus(ResponseStatus.FAILURE);
        }

        return responseDTO;
    }

    @DeleteMapping("/{id}")
    private ResponseStatus deleteUser(@PathVariable(name = "id") int userId)
    {
        ResponseStatus responseStatus;
        try
        {
            this.userService.deleteUser(userId);
            responseStatus = ResponseStatus.SUCCESS;
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
            responseStatus = ResponseStatus.FAILURE;
        }
        return responseStatus;
    }
}
