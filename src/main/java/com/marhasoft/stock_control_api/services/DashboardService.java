package com.marhasoft.stock_control_api.services;

import com.marhasoft.stock_control_api.repositories.ProdutoRepository;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {

    private final ProdutoRepository produtoRepository;

    public DashboardService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }
}
