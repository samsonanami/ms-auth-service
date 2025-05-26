package com.felid.ms_auth_service.models.dtos;

import lombok.Data;

@Data
public class LoginUserDto {
    private String email;
    private String password;
}
