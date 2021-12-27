package com.cinema.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public final class UserDTO {
    private long id;
    private String userType;
    private String username;
    private String password;
}
