package com.marhasoft.stock_control_api.controllers;

import com.marhasoft.stock_control_api.models.Ordem;
import com.marhasoft.stock_control_api.services.OrdemService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Ordem> getOrdens(){
        return ordemService.getAllOrders();
    }

    @GetMapping("/{id}")
    public Ordem getOrdem(@PathVariable("id") Long id){
        return ordemService.getOrdemById(id);
    }

    @PutMapping("/{id}")
    public Ordem updateOrdem(@RequestBody() Ordem ordem, @PathVariable("id") Long id){
        return ordemService.save(ordem);
    }

    @PostMapping
    public Ordem addNew(@RequestBody() Ordem ordem){
        return ordemService.save(ordem);
    }

    @DeleteMapping("/{id}")
    public void deleteOrdem(@PathVariable("id") Long id){
        ordemService.deleteOrdem(id);
    }
}
