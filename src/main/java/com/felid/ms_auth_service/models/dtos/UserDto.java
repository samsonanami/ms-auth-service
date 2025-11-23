package com.felid.ms_auth_service.models.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
    private String fullName;
    private String email;
    private String userName;
}
