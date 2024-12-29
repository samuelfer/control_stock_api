package com.marhasoft.stock_control_api.controllers;

import com.marhasoft.stock_control_api.models.Marca;
import com.marhasoft.stock_control_api.services.MarcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/marcas")
public class MarcaController {

    private final MarcaService marcaService;

    @Autowired
    public MarcaController(MarcaService marcaService) {
        this.marcaService = marcaService;
    }

    @GetMapping
    public List<Marca> getMarcas(){
        return marcaService.getAllMarcas();
    }

    @GetMapping("/{id}")
    public Marca getMarca(@PathVariable("id") Long id){
        return marcaService.getMarcaById(id);
    }

    @PutMapping("/{id}")
    public Marca updateMarca(@RequestBody() Marca brand, @PathVariable("id") Long id){
        return marcaService.save(brand);
    }

    @PostMapping
    public Marca addNova(@RequestBody() Marca brand){
        return marcaService.save(brand);
    }

    @DeleteMapping("/{id}")
    public void deleteMarca(@PathVariable("id") Long id){
        marcaService.deleteMarca(id);
    }
}
