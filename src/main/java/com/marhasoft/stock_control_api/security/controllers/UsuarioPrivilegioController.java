package com.marhasoft.stock_control_api.security.controllers;

import com.marhasoft.stock_control_api.models.Usuario;
import com.marhasoft.stock_control_api.security.models.Privilegio;
import com.marhasoft.stock_control_api.security.models.UsuarioPrivilegio;
import com.marhasoft.stock_control_api.security.services.UsuarioPrivilegioService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Transactional
public class UsuarioPrivilegioController {

    @Autowired
    private UsuarioPrivilegioService usuarioPrivilegioService;

    @GetMapping("/userPrivilegeAssignments")
    public List<UsuarioPrivilegio> parameters(Model model) {
        return usuarioPrivilegioService.findAll();
    }

    @GetMapping("/usuarioprivilegio/{id}")
    public UsuarioPrivilegio getById(@PathVariable Long id) {
        return usuarioPrivilegioService.getById(id);
    }

    @PostMapping("/usuarioprivilegios")
    public UsuarioPrivilegio save(@RequestBody UsuarioPrivilegio usuarioPrivilegio) {
        return usuarioPrivilegioService.save(usuarioPrivilegio);
    }

    @DeleteMapping("/usuarioprivilegio/{id}")
    public void deleteUsuarioPrivilegio(@PathVariable("id") Long id){
        usuarioPrivilegioService.delete(id);
    }

    @Transactional
    @PostMapping("/usuario/{usuarioId}/privilegios")
    public ResponseEntity<String> savePrivileges(@PathVariable("usuarioId") Long usuarioId, @RequestBody List<Privilegio> privilegios) {
       try {
           List<Privilegio> privilegiosSalvo = usuarioPrivilegioService.savePrivilegios(
                   privilegios, usuarioId
           );
           return ResponseEntity.status(HttpStatus.CREATED).body("Privilegios salvos com sucesso");
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

    @DeleteMapping("/user/{userid}/privileges/clear")
    public ResponseEntity<String> clearUserPrivileges(@PathVariable("userid") Long userid) {
        try {
            usuarioPrivilegioService.deletePrivilegios(userid);
            return ResponseEntity.status(HttpStatus.CREATED).body("Privileges were cleared successfully");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred: " + ex.getMessage());
        }
    }

}
