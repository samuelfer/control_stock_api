package com.marhasoft.stock_control_api.security.controllers;

import com.marhasoft.stock_control_api.security.models.Privilegio;
import com.marhasoft.stock_control_api.security.services.PrivilegioService;
import com.marhasoft.stock_control_api.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PrivilegeController {

    @Autowired
    private PrivilegioService privilegioService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/privileges")
    public List<Privilegio> parameters() {
        return privilegioService.findAll();
    }

    @GetMapping("/privilege/{id}")
    public Privilegio getById(@PathVariable Integer id) {
        return privilegioService.getById(id);
    }

    @PutMapping("/privilege/{id}")
    public Privilegio updatePrivilege(@RequestBody() Privilegio privilegio, @PathVariable("id") Long id){
        return privilegioService.save(privilegio);
    }

    @PostMapping("/privileges")
    public Privilegio save(Privilegio privilegio) {
        return privilegioService.save(privilegio);
    }

    @DeleteMapping("/privilege/delete/{id}")
    public void delete(@PathVariable Integer id) {
        privilegioService.delete(id);
    }
}
