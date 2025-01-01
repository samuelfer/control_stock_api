package com.marhasoft.stock_control_api.controllers;

import com.marhasoft.stock_control_api.models.Cliente;
import com.marhasoft.stock_control_api.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    private ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('VIEW_CLIENTE')")
    public List<Cliente> getAll(){
        return clienteService.getAllClientes();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('VIEW_CLIENTE')")
    public Cliente getById(@PathVariable("id") Long id){
        return clienteService.getClienteById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('UPDATE_CLIENTE')")
    public Cliente update(@RequestBody() Cliente cliente, @PathVariable("id") Long id){
        return clienteService.save(cliente);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE_CLIENTE')")
    public Cliente create(@RequestBody() Cliente cliente){
        return clienteService.save(cliente);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE_CLIENTE')")
    public void delete(@PathVariable("id") Long id){
        clienteService.deleteCliente(id);
    }

}
