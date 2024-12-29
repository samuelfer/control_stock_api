package com.marhasoft.stock_control_api.controllers;

import com.marhasoft.stock_control_api.models.SubCategoria;
import com.marhasoft.stock_control_api.services.SubCategoriaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subcategorias")
public class SubCategoryController {

    private final SubCategoriaService subCategoryService;

    public SubCategoryController(SubCategoriaService subCategoryService) {
        this.subCategoryService = subCategoryService;
    }

    @GetMapping
    public List<SubCategoria> getSubCategorias(){
        return subCategoryService.getAllSubCategorias();
    }

    @GetMapping("/{id}")
    public SubCategoria getSubCategoria(@PathVariable("id") Long id){
        return subCategoryService.getSubCategoriaById(id);
    }

    @PutMapping("/{id}")
    public SubCategoria updateSubCategoria(@RequestBody() SubCategoria subCategoria, @PathVariable("id") Long id){
        return subCategoryService.save(subCategoria);
    }

    @PostMapping
    public SubCategoria addNew(@RequestBody() SubCategoria subCategoria){
        return subCategoryService.save(subCategoria);
    }

    @DeleteMapping("/{id}")
    public void deleteSubCategoria(@PathVariable("id") Long id){
        subCategoryService.deleteSubCategoria(id);
    }
}
