package com.marhasoft.stock_control_api.controllers;

import com.marhasoft.stock_control_api.models.Categoria;
import com.marhasoft.stock_control_api.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private CategoriaService categoriaService;

    @Autowired
    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('VIEW_CATEGORIA')")
    public List<Categoria> getAll(){
        return categoriaService.getAllCategorias();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('VIEW_CATEGORIA')")
    public Categoria getById(@PathVariable("id") Long id){
        return categoriaService.getCategoriaById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('UPDATE_CATEGORIA')")
    public Categoria update(@RequestBody() Categoria category, @PathVariable("id") Long id){
        return categoriaService.save(category);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE_CATEGORIA')")
    public Categoria create(@RequestBody() Categoria category){
        return categoriaService.save(category);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE_CATEGORIA')")
    public void delete(@PathVariable("id") Long id){
        categoriaService.deleteCategoria(id);
    }
}
