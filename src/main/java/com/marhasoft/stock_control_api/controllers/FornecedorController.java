package com.marhasoft.stock_control_api.controllers;

import com.marhasoft.stock_control_api.models.Fornecedor;
import com.marhasoft.stock_control_api.services.FornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fornecedores")
public class FornecedorController {
	private final FornecedorService fornecedorService;

	@Autowired
	public FornecedorController(FornecedorService fornecedorService) {
		this.fornecedorService = fornecedorService;
	}

	@GetMapping
	public List<Fornecedor> getFornecedores(){
		return fornecedorService.findAll();
	}

	@GetMapping("/{id}")
	public Fornecedor getFornecedor(@PathVariable("id") Long id){
		return fornecedorService.findById(id);
	}

	@PutMapping("/{id}")
	public Fornecedor updateFornecedor(@RequestBody() Fornecedor fornecedor, @PathVariable("id") Long id){
		return fornecedorService.save(fornecedor);
	}

	@PostMapping
	public Fornecedor addNew(@RequestBody() Fornecedor fornecedor){
		return fornecedorService.save(fornecedor);
	}

	@DeleteMapping("/{id}")
	public void deleteFornecedor(@PathVariable("id") Long id){
		fornecedorService.deleteById(id);
	}
}
