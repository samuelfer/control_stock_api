package com.marhasoft.stock_control_api.controllers;

import com.marhasoft.stock_control_api.models.Ordem;
import com.marhasoft.stock_control_api.services.OrdemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ordens")
public class OrdemController {

    private OrdemService ordemService;

    @Autowired
    public OrdemController(OrdemService ordemService) {
        this.ordemService = ordemService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('VIEW_ORDEM')")
    public List<Ordem> getAll(){
        return ordemService.getAllOrders();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('VIEW_ORDEM')")
    public Ordem getById(@PathVariable("id") Long id){
        return ordemService.getOrdemById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('UPDATE_ORDEM')")
    public Ordem update(@RequestBody() Ordem ordem, @PathVariable("id") Long id){
        return ordemService.save(ordem);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('create_ORDEM')")
    public Ordem create(@RequestBody() Ordem ordem){
        return ordemService.save(ordem);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE_ORDEM')")
    public void delete(@PathVariable("id") Long id){
        ordemService.deleteOrdem(id);
    }
}
