package com.marhasoft.stock_control_api.services;

import com.marhasoft.stock_control_api.exception.InvalidTokenException;
import com.marhasoft.stock_control_api.exception.UserAlreadyExistException;
import com.marhasoft.stock_control_api.mailing.AccountVerificationEmailContext;
import com.marhasoft.stock_control_api.mailing.EmailService;
import com.marhasoft.stock_control_api.models.Usuario;
import com.marhasoft.stock_control_api.repositories.UsuarioRepository;
import com.marhasoft.stock_control_api.security.models.SecureToken;
import com.marhasoft.stock_control_api.security.repositories.SecureTokenRepository;
import com.marhasoft.stock_control_api.security.services.SecureTokenService;
import jakarta.mail.MessagingException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UsuarioService {

    @Value("${site.base.url.https}")
    private String baseURL;

    private final UsuarioRepository usuarioRepository;

    private final SecureTokenRepository secureTokenRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final SecureTokenService secureTokenService;

    private final EmailService emailService;

    public UsuarioService(UsuarioRepository usuarioRepository, SecureTokenRepository secureTokenRepository, BCryptPasswordEncoder bCryptPasswordEncoder, SecureTokenService secureTokenService, EmailService emailService) {
        this.usuarioRepository = usuarioRepository;
        this.secureTokenRepository = secureTokenRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.secureTokenService = secureTokenService;
        this.emailService = emailService;
    }

    public List<Usuario> getAllUsers(){
        return usuarioRepository.findAll();
    }

    public Usuario getUserById(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario updateUsuario(Usuario usuario, Long id){
        return usuarioRepository.save(usuario);
    }

    public Usuario register(Usuario usuario) {
        if(checkIfUserExist(usuario.getEmail())) {
            throw new UserAlreadyExistException("Já existe um usuário cadastrado com o email informado");
        }
        try {
            usuario.setPasswordHash(bCryptPasswordEncoder.encode(usuario.getPassword()));
            usuario.setPassword(bCryptPasswordEncoder.encode(usuario.getPassword()));
            Usuario novoUsuario = usuarioRepository.save(usuario);
            sendRegistrationConfirmationEmail(usuario);
            return novoUsuario;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public boolean checkIfUserExist(String email){
        return usuarioRepository.findByEmail(email) != null;
    }

    public void deleteUsuario(Long id){
        usuarioRepository.deleteById(id);
    }

    public Usuario getUsuarioByLogin(String login) {
        return usuarioRepository.findByLogin(login);
    }

    public void sendRegistrationConfirmationEmail(Usuario usuario) {
        SecureToken secureToken = secureTokenService.createSecureToken();
        secureToken.setUsuario(usuario);
        secureTokenRepository.save(secureToken);

        AccountVerificationEmailContext emailContext = new AccountVerificationEmailContext();
        emailContext.init(usuario);
        emailContext.setToken(secureToken.getToken());
        emailContext.buildVerificationUrl(baseURL, secureToken.getToken());
        try {
            emailService.sendMail(emailContext);
        } catch (MessagingException e) {
            System.out.println("Error "+e.getMessage());
            e.printStackTrace();
        }
    }

    public boolean verifyUsuario(String token) {
        SecureToken secureToken = secureTokenService.findByToken(token);
        if(Objects.isNull(secureToken) || !StringUtils.equals(token, secureToken.getToken()) || secureToken.isExpired()) {
            throw new InvalidTokenException("Token expirado ou não é válido");
        }

        Usuario user = usuarioRepository.getOne(secureToken.getUsuario().getId());
        if(Objects.isNull(user)){
            throw new InvalidTokenException("Usuário não existe");
        }

        user.setAccountVerified(true);
        usuarioRepository.save(user);

        secureTokenService.removeToken(secureToken);
        return  true;
    }

}
