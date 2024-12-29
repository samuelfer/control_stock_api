package com.marhasoft.stock_control_api.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.marhasoft.stock_control_api.security.models.Auditable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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

	@NotBlank(message = "O nome é obrigatório")
	@Size(min = 4, message = "O nome precisa ter no mínimo 4 caracteres")
	private String nome;
	private String cnpj;
	private String endereco;
	private String telefone;
	private String email;

	@OneToMany(mappedBy = "fornecedor")
	private List<Item> fornecedorItens;

}
