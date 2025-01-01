package com.marhasoft.stock_control_api.controllers;

import com.marhasoft.stock_control_api.models.Fornecedor;
import com.marhasoft.stock_control_api.services.FornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
	@PreAuthorize("hasAuthority('VIEW_FORNECEDOR')")
	public List<Fornecedor> getAll(){
		return fornecedorService.findAll();
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('VIEW_FORNECEDOR')")
	public Fornecedor getById(@PathVariable("id") Long id){
		return fornecedorService.findById(id);
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('UPDATE_FORNECEDOR')")
	public Fornecedor update(@RequestBody() Fornecedor fornecedor, @PathVariable("id") Long id){
		return fornecedorService.save(fornecedor);
	}

	@PostMapping
	@PreAuthorize("hasAuthority('CREATE_FORNECEDOR')")
	public Fornecedor create(@RequestBody() Fornecedor fornecedor){
		return fornecedorService.save(fornecedor);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('DELETE_FORNECEDOR')")
	public void delete(@PathVariable("id") Long id){
		fornecedorService.deleteById(id);
	}
}
