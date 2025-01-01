package com.marhasoft.stock_control_api.security.models;

import com.marhasoft.stock_control_api.models.Usuario;
import com.marhasoft.stock_control_api.security.services.UsuarioPrivilegioService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserPrincipal implements UserDetails {

    private final UsuarioPrivilegioService usuarioPrivilegioService;
    private final Usuario usuario;

    public UserPrincipal(UsuarioPrivilegioService usuarioPrivilegioService, Usuario usuario) {
        this.usuarioPrivilegioService = usuarioPrivilegioService;
        this.usuario = usuario;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<Privilegio> privilegeList = usuarioPrivilegioService.getUsuarioPrivilegios(usuario.getId());
        return privilegeList.stream()
                .map(privilege -> new SimpleGrantedAuthority(privilege.getDescricao()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return usuario.getPassword();
    }

    @Override
    public String getUsername() {
        return usuario.getPassword();
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
        return usuario.isAccountVerified();
    }

    public Long getUsuarioId() {
        return usuario.getId();
    }
    public String getEmail() {
        return usuario.getEmail();
    }
}
