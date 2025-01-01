package com.marhasoft.stock_control_api.controllers;

import com.marhasoft.stock_control_api.models.SubCategoria;
import com.marhasoft.stock_control_api.services.SubCategoriaService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subcategorias")
public class SubCategoriaController {

    private final SubCategoriaService subCategoryService;

    public SubCategoriaController(SubCategoriaService subCategoryService) {
        this.subCategoryService = subCategoryService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('VIEW_SUCATEGORIA')")
    public List<SubCategoria> getAll(){
        return subCategoryService.getAllSubCategorias();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('VIEW_SUCATEGORIA')")
    public SubCategoria getById(@PathVariable("id") Long id){
        return subCategoryService.getSubCategoriaById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('UPDATE_SUCATEGORIA')")
    public SubCategoria update(@RequestBody() SubCategoria subCategoria, @PathVariable("id") Long id){
        return subCategoryService.save(subCategoria);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE_SUCATEGORIA')")
    public SubCategoria create(@RequestBody() SubCategoria subCategoria){
        return subCategoryService.save(subCategoria);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE_SUCATEGORIA')")
    public void delete(@PathVariable("id") Long id){
        subCategoryService.deleteSubCategoria(id);
    }
}
