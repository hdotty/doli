package org.disz.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.lang.NonNull;

public class AuthenticationDto {
    @JsonProperty
    private String email;
    @JsonProperty
    private String password;

    public AuthenticationDto() {}

    public AuthenticationDto(final @NonNull String email, final @NonNull String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
