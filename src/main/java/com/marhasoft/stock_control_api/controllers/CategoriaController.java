package com.marhasoft.stock_control_api.controllers;

import com.marhasoft.stock_control_api.models.Categoria;
import com.marhasoft.stock_control_api.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Categoria> getCategorias(){
        return categoriaService.getAllCategorias();
    }

    @GetMapping("/{id}")
    public Categoria getCategory(@PathVariable("id") Long id){
        return categoriaService.getCategoriaById(id);
    }

    @PutMapping("/{id}")
    public Categoria updateCategoria(@RequestBody() Categoria category, @PathVariable("id") Long id){
        return categoriaService.save(category);
    }

    @PostMapping
    public Categoria addNew(@RequestBody() Categoria category){
        return categoriaService.save(category);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable("id") Long id){
        categoriaService.deleteCategoria(id);
    }
}
