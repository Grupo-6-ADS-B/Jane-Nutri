package fitt_nutri.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "USERS")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Nome não pode estar vazio")
    @Column(nullable = false)
    private String nome;

    @NotBlank(message = "CPF não pode estar vazio")
    @Column(nullable = false, unique = true)
    private String cpf;

    @Pattern(regexp = "^\\d{1,6}/[A-Z]{2}$", message = "CRN deve estar no formato 12345/UF")
    @Column(nullable = false)
    private String crn;

    @PastOrPresent(message = "A data de nascimento não pode estar no futuro")
    @Column(nullable = false)
    private LocalDate dtNascimento;

    @NotBlank(message = "Email não pode estar vazio")
    @Email(message = "Email inválido")
    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;
}







