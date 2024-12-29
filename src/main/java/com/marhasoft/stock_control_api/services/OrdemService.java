package com.marhasoft.stock_control_api.services;

import com.marhasoft.stock_control_api.models.Item;
import com.marhasoft.stock_control_api.models.Ordem;
import com.marhasoft.stock_control_api.repositories.ItemRepository;
import com.marhasoft.stock_control_api.repositories.OrdemRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class OrdemService {
    private OrdemRepository ordemRepository;
    private final ItemRepository itemRepository;

    @Autowired
    public OrdemService(OrdemRepository ordemRepository, ItemRepository itemRepository) {
        this.ordemRepository = ordemRepository;
        this.itemRepository = itemRepository;
    }

    public List<Ordem> getAllOrders(){
        return ordemRepository.findAll();
    }

    public Ordem getOrdemById(Long id) {
        return ordemRepository.findById(id).orElse(null);
    }

    @Transactional
    public Ordem save(Ordem ordem) {

        ordem.getOrdemItens().forEach(orderItem -> {
            Item item = orderItem.getItem();
            Short newQuantity = (short) (item.getQuantidade() - orderItem.getQuantidade());
            if(newQuantity < item.getQuantidadeMinina()) {
                throw new IllegalArgumentException(String.format("Quantidade de item %s não disponível. Por favor diminua a quantidade para %s ou menor",
                        item.getDescricao(), item.getQuantidadeMinina()));
            }
            item.setQuantidade(newQuantity);
            itemRepository.save(item);
        });

        return ordemRepository.save(ordem);
    }

    public void deleteOrdem(Long id){
        ordemRepository.deleteById(id);
    }
}