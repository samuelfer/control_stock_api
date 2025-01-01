package com.marhasoft.stock_control_api.controllers;

import com.marhasoft.stock_control_api.models.Usuario;
import com.marhasoft.stock_control_api.security.models.Role;
import com.marhasoft.stock_control_api.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAuthority('VIEW_USUARIO')")
    public List<Usuario> getAll(){
        return usuarioService.getAllUsuarios();
    }

    @GetMapping("/username/{login}")
    @PreAuthorize("hasAuthority('VIEW_USUARIO')")
    public Usuario getByUsername(@PathVariable("login") String login){
        return usuarioService.getUsuarioByLogin(login);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('VIEW_USUARIO')")
    public Usuario getById(@PathVariable("id") Long id){
        return usuarioService.getUsuarioById(id);
    }


    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('UPDATE_USUARIO')")
    public Usuario update(@RequestBody() Usuario usuario, @PathVariable("id") Long id){
        return usuarioService.updateUsuario(usuario, id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE_USUARIO')")
    public void delete(@PathVariable("id") Long id){
        usuarioService.deleteUsuario(id);
    }

    @PutMapping("/{id}/roles")
    @PreAuthorize("hasAuthority('UPDATE_USUARIO')")
    public Usuario updateUsuario(@RequestBody() List<Role> roles, @PathVariable("id") Long id){
        Usuario usuario = usuarioService.getUsuarioById(id);
        return usuarioService.updateUsuario(usuario, id);
    }
}
