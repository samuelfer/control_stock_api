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
        @UniqueConstraint(columnNames = "mobile"),
        @UniqueConstraint(columnNames = "username")
})
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Usuario extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(length = 50)
    @NotBlank(message = "O nome é obrigatório") // Não permite nulo ou vazio
    @Size(max = 50, message = "O nome deve ter no máximo 50 caracteres")
    private String nome;

    @Column(length = 50)
    @NotBlank(message = "O login é obrigatório")
    @Size(min = 8, message = "O login precisa ter no mínimo 5 4 caracteres")
    private String login;

    @Column(length = 50)
    @NotBlank(message = "O email é obrigatório")
    @Email(message = "E-mail deve ser válido")
    private String email;

    private String imagemUrl;


    @OneToMany(mappedBy = "usuario")
    private List<SecureToken> tokens;

    private boolean accountVerified;
    private boolean loginDisabled;

    private String telefone;
    private Date dataNascimento;

    @Column(nullable = false)
    @NotBlank(message = "A senha é obrigatória")
    @Size(min = 8, max = 15, message = "A senha deve ter entre 8 e 15 caracteres")
    private String password;

    private String passwordHash;

    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimoLogin;

    @OneToMany(mappedBy = "usuario")
    private List<UsuarioPrivilegio> privilegios;

}
