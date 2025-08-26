package fitt_nutri.example.demo.service;

import fitt_nutri.example.demo.model.UserModel;
import fitt_nutri.example.demo.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public UserModel createUser(UserModel userModel) {

        if(userRepository.existsByEmail(userModel.getEmail())){
            throw new IllegalArgumentException("Email já cadastrado");
        }
        if(userRepository.existsByCpf(userModel.getCpf())){
            throw new IllegalArgumentException("CPF já cadastrado");
        }
        if (userRepository.existsByCrn(userModel.getCrn())) {
            throw new IllegalArgumentException("CRN já cadastrado");
        }
        return userRepository.save(userModel);
    }

    public List<UserModel> listUsers(){
        return userRepository.findAll();
    }

    public UserModel listUserById(Integer id) {
        if(!userRepository.existsById(id)){
            throw new IllegalArgumentException("Usuário não encontrado");
        } else {
            return userRepository.findById(id).get();
        }
    }

    public boolean deleteUserById(Integer id) {
        return userRepository.findById(id).map(user -> {
            userRepository.delete(user);
            return true;
        }).orElse(false);
    }

}
