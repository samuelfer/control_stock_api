package com.marhasoft.stock_control_api.controllers;

import com.marhasoft.stock_control_api.models.OrdemItem;
import com.marhasoft.stock_control_api.services.OrdemItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ordemitems")
public class OrdemItemController {

    private OrdemItemService ordemItemService;

    @Autowired
    public OrdemItemController(OrdemItemService ordemItemService) {
        this.ordemItemService = ordemItemService;

    }

    @GetMapping
    public List<OrdemItem> getOrdemItens(){
        return ordemItemService.getAllOrdemItens();
    }

    @GetMapping("/{id}")
    public OrdemItem getOrdemItem(@PathVariable("id") Long id){
        return ordemItemService.getOrdemItemById(id);
    }

    @PutMapping("/{id}")
    public OrdemItem updateOrdemItem(@RequestBody() OrdemItem orderItem, @PathVariable("id") Long id){
        return ordemItemService.save(orderItem);
    }

    @PostMapping
    public OrdemItem addNew(@RequestBody() OrdemItem ordemItem){
        return ordemItemService.save(ordemItem);
    }

    @DeleteMapping("/{id}")
    public void deleteOrdemItem(@PathVariable("id") Long id){
        ordemItemService.deleteOrdemItem(id);
    }
}
