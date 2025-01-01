package com.marhasoft.stock_control_api.controllers;

import com.marhasoft.stock_control_api.models.Marca;
import com.marhasoft.stock_control_api.services.MarcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAuthority('VIEW_MARCA')")
    public List<Marca> getAll(){
        return marcaService.getAllMarcas();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('VIEW_MARCA')")
    public Marca getById(@PathVariable("id") Long id){
        return marcaService.getMarcaById(id);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE_MARCA')")
    public Marca create(@RequestBody() Marca brand){
        return marcaService.save(brand);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('UPDATE_MARCA')")
    public Marca update(@RequestBody() Marca brand, @PathVariable("id") Long id){
        return marcaService.save(brand);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE_MARCA')")
    public void delete(@PathVariable("id") Long id){
        marcaService.deleteMarca(id);
    }
}
