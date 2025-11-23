package com.felid.ms_auth_service.responses;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProfileResponse {
    private String username;
    private long expiresIn;
}
