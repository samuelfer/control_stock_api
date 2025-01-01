package com.marhasoft.stock_control_api.security.services;

import com.marhasoft.stock_control_api.models.Usuario;
import com.marhasoft.stock_control_api.repositories.UsuarioRepository;
import com.marhasoft.stock_control_api.security.models.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UsuarioPrivilegioService usuarioPrivilegioService;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public MyUserDetailsService(UsuarioPrivilegioService usuarioPrivilegioService, UsuarioRepository usuarioRepository) {
        this.usuarioPrivilegioService = usuarioPrivilegioService;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Entrei no MyUserDetailService");
        Usuario usuario = usuarioRepository.findByLogin(username);

        if(usuario == null) {
            throw  new UsernameNotFoundException("Usuário não encontrado na base de dados");
        }
        System.out.println("Passei aqio");
        return new UserPrincipal(usuarioPrivilegioService, usuario);
    }
}
