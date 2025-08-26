package fitt_nutri.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Nome não pode estar vazio")
    @Column(nullable = false)
    private String nome;

    @NotBlank(message = "CPF não pode estar vazio")
    @CPF(message = "CPF inválido")
    @Column(nullable = false, unique = true)
    private String cpf;

    @Pattern(regexp = "^\\d{1,6}/[A-Z]{2}$", message = "CRN deve estar no formato XXXXX/UF")
    @Column(nullable = false)
    private String crn;

    @PastOrPresent(message = "A data de nascimento não pode estar no futuro")
    @Column(nullable = false)
    private LocalDate dtNascimento;

    @NotBlank(message = "Email não pode estar vazio")
    @Email(message = "Email inválido")
    @Column(nullable = false, unique = true)
    private String email;

    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", message = "A senha deve ter no mínimo 8 caracteres, incluindo letras e números")
    @Column(nullable = false)
    private String senha;
}







