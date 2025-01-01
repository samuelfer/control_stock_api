package com.marhasoft.stock_control_api.controllers;

import com.marhasoft.stock_control_api.models.Produto;
import com.marhasoft.stock_control_api.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProductController {

    private ProdutoService produtoService;

    @Autowired
    public ProductController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('VIEW_PRODUTO')")
    public List<Produto> getAll(){
        return produtoService.getAllProdutos();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('VIEW_PRODUTO')")
    public Produto getById(@PathVariable("id") Long id){
        return produtoService.getProdutoById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('UPDATE_PRODUTO')")
    public Produto update(@RequestBody() Produto produto, @PathVariable("id") Long id){
        return produtoService.updateProduto(produto);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE_PRODUTO')")
    public Produto create(@RequestBody() Produto produto){
        return produtoService.save(produto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE_PRODUTO')")
    public void delete(@PathVariable("id") Long id){
        produtoService.deleteProduto(id);
    }
}
