package com.marhasoft.stock_control_api.controllers;

import com.marhasoft.stock_control_api.models.OrdemItem;
import com.marhasoft.stock_control_api.services.OrdemItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAuthority('VIEW_ORDEM_ITEM')")
    public List<OrdemItem> getOrdemAll(){
        return ordemItemService.getAllOrdemItens();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('VIEW_ORDEM_ITEM')")
    public OrdemItem getById(@PathVariable("id") Long id){
        return ordemItemService.getOrdemItemById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('UPDATE_ORDEM_ITEM')")
    public OrdemItem update(@RequestBody() OrdemItem orderItem, @PathVariable("id") Long id){
        return ordemItemService.save(orderItem);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE_ORDEM_ITEM')")
    public OrdemItem create(@RequestBody() OrdemItem ordemItem){
        return ordemItemService.save(ordemItem);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE_ORDEM_ITEM')")
    public void delete(@PathVariable("id") Long id){
        ordemItemService.deleteOrdemItem(id);
    }
}
