package com.marhasoft.stock_control_api.controllers;

import com.marhasoft.stock_control_api.models.Cliente;
import com.marhasoft.stock_control_api.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClienteController {
    private ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public List<Cliente> getCustomers(){
        return clienteService.getAllClientes();
    }

    @GetMapping("/{id}")
    public Cliente getCustomer(@PathVariable("id") Long id){
        return clienteService.getClienteById(id);
    }

    @PutMapping("/{id}")
    public Cliente updateCustomer(@RequestBody() Cliente customer, @PathVariable("id") Long id){
        return clienteService.save(customer);
    }

    @PostMapping
    public Cliente addNew(@RequestBody() Cliente cliente){
        return clienteService.save(cliente);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable("id") Long id){
        clienteService.deleteCliente(id);
    }

}
