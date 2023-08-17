package com.dustbuster.dustbusterApi.Model.Bean;

import com.dustbuster.dustbusterApi.Entity.Rol;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AuthRequestBean {

    @NotBlank
    @Size(min = 8, max = 50)
    private String correo;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    private Boolean enabled;

    private List<Rol> authorities;
}