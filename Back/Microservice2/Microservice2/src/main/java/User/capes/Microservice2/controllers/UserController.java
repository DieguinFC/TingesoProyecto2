package User.capes.Microservice2.controllers;

import User.capes.Microservice2.entity.UserEntity;
import User.capes.Microservice2.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    // Endpoint para registrar un usuario
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserEntity user) {
        try {
            userService.registerUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("Usuario registrado con éxito");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // Endpoint para verificar si un usuario existe, será utilizado por Microservicio 3
    @GetMapping("/users/exists")
    public ResponseEntity<Boolean> userExists(@RequestParam String email) {
        boolean exists = userService.userExists(email); // Verifica si el usuario existe en la base de datos
        return ResponseEntity.ok(exists); // Devuelve true si el usuario existe, false si no
    }
}
