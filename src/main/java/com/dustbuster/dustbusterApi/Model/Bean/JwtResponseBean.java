package com.dustbuster.dustbusterApi.Model.Bean;

import com.dustbuster.dustbusterApi.Entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class JwtResponseBean {
    private String token;
    private String type = "Bearer";
    private Usuario usuario;
}
