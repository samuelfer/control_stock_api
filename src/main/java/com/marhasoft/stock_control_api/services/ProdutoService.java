package com.marhasoft.stock_control_api.services;

import com.marhasoft.stock_control_api.models.Produto;
import com.marhasoft.stock_control_api.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {
    private ProdutoRepository produtoRepository;

    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> getAllProdutos(){
        return produtoRepository.findAll();
    }

    public Produto getProdutoById(Long id) {
        return produtoRepository.findById(id).orElse(null);
    }

    public Produto save(Produto produto) {
        return produtoRepository.save(produto);
    }

    public Produto updateProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    public void deleteProduto(Long id){
        produtoRepository.deleteById(id);
    }
}
