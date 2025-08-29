package fitt_nutri.example.demo.repository;

import fitt_nutri.example.demo.model.UserModel;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserModel, Integer> {

    boolean existsByCpf(@NotEmpty @CPF String cpf);


    boolean existsByEmail(@NotEmpty String email);

    List<UserModel> findByCrnContaining(String crn);

    boolean existsByCrn(@Pattern(regexp = "^\\d{1,6}/[A-Z]{2}$", message = "CRN deve estar no formato 12345/UF") String crn);
}
