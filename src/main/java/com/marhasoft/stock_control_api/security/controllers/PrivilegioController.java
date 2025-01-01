package com.marhasoft.stock_control_api.security.controllers;

import com.marhasoft.stock_control_api.security.models.Privilegio;
import com.marhasoft.stock_control_api.security.services.PrivilegioService;
import com.marhasoft.stock_control_api.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/privilegios")
public class PrivilegioController {

    @Autowired
    private PrivilegioService privilegioService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<Privilegio> getAll() {
        return privilegioService.findAll();
    }

    @GetMapping("/{id}")
    public Privilegio getById(@PathVariable Integer id) {
        return privilegioService.getById(id);
    }

    @PutMapping("/{id}")
    public Privilegio updatePrivilegio(@RequestBody() Privilegio privilegio, @PathVariable("id") Long id){
        return privilegioService.save(privilegio);
    }

    @PostMapping
    public Privilegio save(Privilegio privilegio) {
        return privilegioService.save(privilegio);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id) {
        privilegioService.delete(id);
    }
}
