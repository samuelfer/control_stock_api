package com.marhasoft.stock_control_api.services;


import com.marhasoft.stock_control_api.models.Marca;
import com.marhasoft.stock_control_api.repositories.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarcaService {

    private MarcaRepository marcaRepository;

    @Autowired
    public MarcaService(MarcaRepository marcaRepository) {
        this.marcaRepository = marcaRepository;
    }

    public List<Marca> getAllMarcas(){
        return marcaRepository.findAll();
    }

    public Marca getMarcaById(Long id) {
        return marcaRepository.findById(id).orElse(null);
    }

    public Marca save(Marca brand) {
        return marcaRepository.save(brand);
    }

    public void deleteMarca(Long id){
        marcaRepository.deleteById(id);
    }
}
