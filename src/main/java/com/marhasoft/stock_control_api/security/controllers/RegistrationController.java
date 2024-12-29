package com.marhasoft.stock_control_api.security.controllers;

import com.marhasoft.stock_control_api.exception.InvalidTokenException;
import com.marhasoft.stock_control_api.models.Usuario;
import com.marhasoft.stock_control_api.security.services.AuthenticationService;
import com.marhasoft.stock_control_api.services.UsuarioService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class RegistrationController {

    Logger LOGGER = LoggerFactory.getLogger(RegistrationController.class);

    private final AuthenticationService authenticationService;
    private UsuarioService usuarioService;

    @Autowired
    public RegistrationController(AuthenticationService authenticationService, UsuarioService usuarioService) {
        this.authenticationService = authenticationService;
        this.usuarioService = usuarioService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody Usuario usuario) {
        try {
            Usuario usuarioSalvo = usuarioService.register(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioSalvo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/register/verify")
    public void verifyUser(@RequestParam(required = false) String token, HttpServletResponse response) throws IOException {
        LOGGER.info("Token recebido: {}", token);
        if(StringUtils.isEmpty(token)){
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Token não é válido");
        }
        try{
            usuarioService.verifyUsuario(token);
            response.sendRedirect("http://localhost:3000/#/verificationSuccessful");
        } catch (InvalidTokenException e){
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }
    }

}