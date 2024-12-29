package com.marhasoft.stock_control_api.controllers;

import com.marhasoft.stock_control_api.models.Usuario;
import com.marhasoft.stock_control_api.security.models.Role;
import com.marhasoft.stock_control_api.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<Usuario> getUsuarios(){
        return usuarioService.getAllUsers();
    }

    @GetMapping("/username/{login}")
    public Usuario getUsuarioByUsername(@PathVariable("login") String login){
        return usuarioService.getUsuarioByLogin(login);
    }

    @GetMapping("/{id}")
    public Usuario getUsuario(@PathVariable("id") Long id){
        return usuarioService.getUserById(id);
    }


    @PutMapping("/{id}")
    public Usuario updateUsuario(@RequestBody() Usuario usuario, @PathVariable("id") Long id){
        return usuarioService.updateUsuario(usuario, id);
    }

//    @PostMapping("/users")
//    public ResponseEntity<User> addNew(@RequestBody() User user){
//        User newUser = userService.register(user);
//        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
//    }

    @DeleteMapping("/{id}")
    public void deleteUsuario(@PathVariable("id") Long id){
        usuarioService.deleteUsuario(id);
    }

    @PutMapping("/{id}/roles")
    public Usuario updateUsuario(@RequestBody() List<Role> roles, @PathVariable("id") Long id){
        Usuario usuario = usuarioService.getUserById(id);
        return usuarioService.updateUsuario(usuario, id);
    }
}
