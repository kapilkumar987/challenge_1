package com.scaler.challenge1.dtos;

import com.scaler.challenge1.models.User;
import lombok.Data;

@Data
public class UserResponseDTO
{
    private User user;
    private ResponseStatus responseStatus;
}
