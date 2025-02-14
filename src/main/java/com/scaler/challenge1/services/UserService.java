package com.scaler.challenge1.services;

import com.scaler.challenge1.exceptions.InvalidUserException;
import com.scaler.challenge1.models.User;
import com.scaler.challenge1.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService
{
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers()
    {
        return  this.userRepository.findAll();
    }

    public User findById(int userId) throws InvalidUserException
    {
        Optional<User> userOptional = this.userRepository.findById(userId);

        if(userOptional.isEmpty())
        {
            throw new InvalidUserException("Invalid User ID");
        }

        return userOptional.get();
    }

    public User createUser(String name, String email, String phoneNumber)
    {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);

        return this.userRepository.save(user);
    }

    public User updateUser(int userId, String name, String email, String phoneNumber) throws InvalidUserException
    {
        Optional<User> userOptional = this.userRepository.findById(userId);
        if(userOptional.isEmpty())
        {
            throw new InvalidUserException("Invalid User");
        }

        User user = userOptional.get();
        user.setName(name);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);

        return this.userRepository.save(user);
    }

    public void deleteUser(int userId)
    {
        this.userRepository.deleteById(userId);
    }
}
