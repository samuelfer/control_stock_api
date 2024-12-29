package com.marhasoft.stock_control_api.services;


import com.marhasoft.stock_control_api.models.Cliente;
import com.marhasoft.stock_control_api.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> getAllClientes(){
        return clienteRepository.findAll();
    }

    public Cliente getClienteById(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    public Cliente save(Cliente customer) {
        return clienteRepository.save(customer);
    }

    public void deleteCliente(Long id){
        clienteRepository.deleteById(id);
    }

}
