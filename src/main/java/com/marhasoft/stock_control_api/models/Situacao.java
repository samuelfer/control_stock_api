package com.marhasoft.stock_control_api.models;

import com.marhasoft.stock_control_api.security.models.Auditable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Situacao extends Auditable<String> {
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;

	@NotBlank(message = "O nome é obrigatório")
	private String nome;

}
