package com.marhasoft.stock_control_api.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.marhasoft.stock_control_api.security.models.Auditable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Fornecedor extends Auditable<String> {
		
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="id")
	private Long id;
	private String nome;
	private String cnpj;
	private String endereco;
	private String telefone;
	private String email;

	@OneToMany(mappedBy = "fornecedor")
	private List<Item> fornecedorItens;

}
