package com.marhasoft.stock_control_api.security.controllers;

import com.marhasoft.stock_control_api.models.Usuario;
import com.marhasoft.stock_control_api.security.models.UserPrincipal;
import com.marhasoft.stock_control_api.security.services.AuthenticationService;
import com.marhasoft.stock_control_api.security.models.LoginRequest;
import com.marhasoft.stock_control_api.security.services.TokenService;
import com.marhasoft.stock_control_api.security.services.UsuarioPrivilegioService;
import com.marhasoft.stock_control_api.services.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final UsuarioService usuarioService;

    private final UsuarioPrivilegioService usuarioPrivilegioService;
    private final TokenService tokenService;
    private final AuthenticationService authenticationService;

    public AuthController(UsuarioService usuarioService, UsuarioPrivilegioService usuarioPrivilegioService, TokenService tokenService, AuthenticationService authenticationService) {
        this.usuarioService = usuarioService;
        this.usuarioPrivilegioService = usuarioPrivilegioService;
        this.tokenService = tokenService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest, HttpSession session) {
        try {
            boolean isAuthenticated = authenticationService.authenticate(loginRequest.getLogin(), loginRequest.getPassword());
            if (isAuthenticated) {
                Usuario usuario = usuarioService.getUsuarioByLogin(loginRequest.getLogin());
                UserPrincipal principal = new UserPrincipal(usuarioPrivilegioService, usuario);
                Authentication authentication = new UsernamePasswordAuthenticationToken(usuario, null, principal.getAuthorities());
                String jwtToken = tokenService.generateToken(principal, authentication);
                session.setAttribute("usuario", loginRequest.getLogin());
                return ResponseEntity.ok(jwtToken);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login ou senha inválidos");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate();  // Invalidate the session
        return ResponseEntity.ok("Deslogado com sucesso");
    }

}
