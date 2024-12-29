package com.marhasoft.stock_control_api.models;

import com.marhasoft.stock_control_api.security.models.Auditable;
import jakarta.persistence.*;
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
	private String nome;

}
