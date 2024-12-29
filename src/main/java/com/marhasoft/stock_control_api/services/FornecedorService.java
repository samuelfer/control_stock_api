package com.marhasoft.stock_control_api.services;

import com.marhasoft.stock_control_api.models.Fornecedor;
import com.marhasoft.stock_control_api.repositories.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FornecedorService {
	@Autowired
	private FornecedorRepository fornecedorRepository;

	//Get All Suppliers
	public List<Fornecedor> findAll(){
		return fornecedorRepository.findAll();
	}

	//Get Supplier By Id
	public Fornecedor findById(Long id) {
		return fornecedorRepository.findById(id).orElse(null);
	}

	//Delete Supplier
	public void deleteById(Long id) {
		fornecedorRepository.deleteById(id);
	}

	//Update Supplier
	public Fornecedor save(Fornecedor supplier) {
		return fornecedorRepository.save(supplier);
	}

}
