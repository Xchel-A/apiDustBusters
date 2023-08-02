package com.dustbuster.dustbusterApi.Service.impl;

import com.dustbuster.dustbusterApi.Entity.Rol;
import com.dustbuster.dustbusterApi.Entity.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;

    private String correo;

    @JsonIgnore
    private String password;

    private boolean enabled;

    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(String correo, String password, boolean enabled,
                           Collection<? extends GrantedAuthority> authorities) {
        this.correo = correo;
        this.password = password;
        this.enabled = enabled;
        this.authorities = authorities;
    }

    public static UserDetailsImpl build(Usuario usuario) {
        Set<GrantedAuthority> authorities = new HashSet<>();
        for (Rol rol : usuario.getAuthorities()) {
            authorities.add(new SimpleGrantedAuthority(rol.getAuthority()));
        }

        return new UserDetailsImpl(
                usuario.getCorreo(),
                usuario.getPassword(),
                usuario.getEnabled(),
                authorities
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.correo;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
