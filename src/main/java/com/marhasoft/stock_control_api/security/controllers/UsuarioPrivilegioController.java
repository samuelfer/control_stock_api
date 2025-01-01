package com.marhasoft.stock_control_api.security.controllers;

import com.marhasoft.stock_control_api.models.Usuario;
import com.marhasoft.stock_control_api.security.models.Privilegio;
import com.marhasoft.stock_control_api.security.models.UsuarioPrivilegio;
import com.marhasoft.stock_control_api.security.services.UsuarioPrivilegioService;
import com.marhasoft.stock_control_api.services.UsuarioService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario-privilegios")
public class UsuarioPrivilegioController {

    @Autowired
    private UsuarioPrivilegioService usuarioPrivilegioService;
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<UsuarioPrivilegio> getAll() {
        return usuarioPrivilegioService.findAll();
    }

    @GetMapping("/{id}")
    public UsuarioPrivilegio getById(@PathVariable Long id) {
        return usuarioPrivilegioService.getById(id);
    }

    @PostMapping
    public UsuarioPrivilegio save(@RequestBody UsuarioPrivilegio usuarioPrivilegio) {
        return usuarioPrivilegioService.save(usuarioPrivilegio);
    }

    @DeleteMapping("/{id}")
    public void deleteUsuarioPrivilegio(@PathVariable("id") Long id){
        usuarioPrivilegioService.delete(id);
    }

    @PostMapping("/usuario/{usuarioId}/privilegios")
    @Transactional
    public ResponseEntity<String> salvaPrivilegiosToUsuario(@PathVariable("usuarioId") Long usuarioId, @RequestBody List<Privilegio> privilegios) {
       try {
           Usuario usuario = usuarioService.getUsuarioByIdOrErro(usuarioId);
           usuarioPrivilegioService.savePrivilegios(privilegios, usuario);
           return ResponseEntity.status(HttpStatus.CREATED).body("Privilégios salvos com sucesso");
       } catch (Exception ex) {
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao tentar salvar: " + ex.getMessage());
       }
    }

    @GetMapping("/usuario/{usuarioId}/privilegios")
    public  List<Privilegio> getUsuarioPrivilegios(@PathVariable("usuarioId") Long usuarioId) {
        return usuarioPrivilegioService.getUsuarioPrivilegios(usuarioId);
    }

    @GetMapping("/privilegio/{privilegioId}/usuarios")
    public  List<Usuario> getUsuariosByPrivilegio(@PathVariable("privilegioId") Long privilegioId) {
        return usuarioPrivilegioService.getUsuariosByPrivilegio(privilegioId);
    }

    @DeleteMapping("/usuario/{usuarioId}/privileges/clear")
    public ResponseEntity<String> deletePrivilegioByUsuario(@PathVariable("usuarioId") Long usuarioId) {
        try {
            usuarioPrivilegioService.deletePrivilegios(usuarioId);
            return ResponseEntity.status(HttpStatus.CREATED).body("Privilegio deletado com sucesso");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao tentar deletar privilégio: " + ex.getMessage());
        }
    }

}
