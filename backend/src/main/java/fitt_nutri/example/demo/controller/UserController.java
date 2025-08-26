package fitt_nutri.example.demo.controller;


import fitt_nutri.example.demo.model.UserModel;
import fitt_nutri.example.demo.repository.UserRepository;
import fitt_nutri.example.demo.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;


    @PostMapping
    public ResponseEntity<UserModel> createUser(@Valid @RequestBody UserModel userModel) {

            UserModel newUser = userService.createUser(userModel);
            if(userRepository.existsByCpf(userModel.getCpf())){
                return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
            } else if(userRepository.existsByEmail(userModel.getEmail())){
                return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
            } else if(userRepository.existsByCrn(userModel.getCrn())){
                return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
            } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
        }

    }

    @GetMapping
    public ResponseEntity<List<UserModel>> listUsers() {
        try {
            List<UserModel> users = userService.listUsers();
            return ResponseEntity.ok(users);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<UserModel>  listUserById(@PathVariable Integer id){

        UserModel user = userService.listUserById(id);

        if(!userRepository.existsById(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {
            return ResponseEntity.ok(user);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserModel> updateUserById(@PathVariable Integer id, @Valid @RequestBody UserModel userModel){
        if(!userRepository.existsById(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {
            userModel.setId(id);
            UserModel updatedUser = userRepository.save(userModel);
            return ResponseEntity.ok(updatedUser);
        }
    }

    @PutMapping("/{crn}")
    public ResponseEntity<UserModel> updateUserByCrn(@PathVariable String crn, @Valid @RequestBody UserModel userModel){
        if(!userRepository.existsByCrn(crn)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {
            UserModel existingUser = userRepository.findAll().stream()
                    .filter(user -> user.getCrn().equals(crn))
                    .findFirst()
                    .orElse(null);
            if (existingUser != null) {
                userModel.setId(existingUser.getId());
                UserModel updatedUser = userRepository.save(userModel);
                return ResponseEntity.ok(updatedUser);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Integer id) {
        boolean deleted = userService.deleteUserById(id);
        if (deleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping()
    public ResponseEntity<Void> deleteAllUsers() {
        userRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }


}
