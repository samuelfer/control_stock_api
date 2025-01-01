package com.marhasoft.stock_control_api.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.marhasoft.stock_control_api.security.models.Auditable;
import com.marhasoft.stock_control_api.security.models.SecureToken;
import com.marhasoft.stock_control_api.security.models.UsuarioPrivilegio;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.*;

@Entity
@Table(name = "usuario", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email"),
        @UniqueConstraint(columnNames = "login")
})
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Usuario extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank(message = "O nome é obrigatório") // Não permite nulo ou vazio
    @Size(max = 50, message = "O nome deve ter no máximo 50 caracteres")
    private String nome;

    @NotBlank(message = "O login é obrigatório")
    @Size(min = 4, message = "O login precisa ter no mínimo 4 caracteres")
    @Column(unique = true)
    private String login;

    @NotBlank(message = "O email é obrigatório")
    @Email(message = "E-mail deve ser válido")
    @Column(unique = true)
    private String email;

    private String imagemUrl;


    @OneToMany(mappedBy = "usuario")
    private List<SecureToken> tokens;

    private boolean accountVerified;
    private boolean loginDisabled;

    private String telefone;
    private Date dataNascimento;

    @NotBlank(message = "A senha é obrigatória")
    private String password;

    private String passwordHash;

    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimoLogin;

    @OneToMany(mappedBy = "usuario")
    private List<UsuarioPrivilegio> privilegios;

}
