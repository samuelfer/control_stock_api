package com.marhasoft.stock_control_api.services;

import com.marhasoft.stock_control_api.models.SubCategoria;
import com.marhasoft.stock_control_api.repositories.SubCategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SubCategoriaService {

    private SubCategoriaRepository subCategoriaRepository;

    @Autowired
    public SubCategoriaService(SubCategoriaRepository subCategoriaRepository) {
        this.subCategoriaRepository = subCategoriaRepository;
    }

    public List<SubCategoria> getAllSubCategorias(){
        return subCategoriaRepository.findAll();
    }

    public SubCategoria getSubCategoriaById(Long id) {
        return subCategoriaRepository.findById(id).orElse(null);
    }

    public SubCategoria save(SubCategoria subCategoria) {
        return subCategoriaRepository.save(subCategoria);
    }

    public void deleteSubCategoria(Long id){
        subCategoriaRepository.deleteById(id);
    }
}
