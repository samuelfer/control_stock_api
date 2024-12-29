package com.marhasoft.stock_control_api.controllers;

import com.marhasoft.stock_control_api.models.Produto;
import com.marhasoft.stock_control_api.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Produto> getProdutos(){
        return produtoService.getAllProdutos();
    }

    @GetMapping("/{id}")
    public Produto getProduto(@PathVariable("id") Long id){
        return produtoService.getProdutoById(id);
    }

    @PutMapping("/{id}")
    public Produto updateProduto(@RequestBody() Produto product, @PathVariable("id") Long id){
        return produtoService.updateProduto(product);
    }

    @PostMapping
    public Produto addNew(@RequestBody() Produto produto){
        return produtoService.save(produto);
    }

    @DeleteMapping("/{id}")
    public void deleteProduto(@PathVariable("id") Long id){
        produtoService.deleteProduto(id);
    }
}
