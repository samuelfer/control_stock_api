package com.marhasoft.stock_control_api.services;

import com.marhasoft.stock_control_api.models.OrdemItem;
import com.marhasoft.stock_control_api.repositories.OrdemItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdemItemService {
    private OrdemItemRepository ordemItemRepository;

    @Autowired
    public OrdemItemService(OrdemItemRepository ordemItemRepository) {
        this.ordemItemRepository = ordemItemRepository;
    }

    public List<OrdemItem> getAllOrdemItens(){
        return ordemItemRepository.findAll();
    }

    public OrdemItem getOrdemItemById(Long id) {
        return ordemItemRepository.findById(id).orElse(null);
    }

    public OrdemItem save(OrdemItem orderItem) {
        return ordemItemRepository.save(orderItem);
    }

    public void deleteOrdemItem(Long id){
        ordemItemRepository.deleteById(id);
    }
}
