package com.marhasoft.stock_control_api.security.services;


import com.marhasoft.stock_control_api.models.Usuario;
import com.marhasoft.stock_control_api.repositories.UsuarioRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UsuarioRepository usuarioRepository;

    public AuthenticationService(BCryptPasswordEncoder bCryptPasswordEncoder, UsuarioRepository usuarioRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.usuarioRepository = usuarioRepository;
    }

    public boolean authenticate(String login, String password) {
        Usuario usuario = usuarioRepository.findByLogin(login);

        if(!usuario.isAccountVerified()) {
            throw new BadCredentialsException("Conta não verificada");
        }

        if (!usuario.getLogin().equals(login)){
            throw new UsernameNotFoundException("Usuário não encontrado na base de dados");
        }

        if(!usuario.getPasswordHash().equals(bCryptPasswordEncoder.encode(password))) {
            throw new BadCredentialsException("Usuário ou Senha incorretos");
        }
        return true;
    }

}
