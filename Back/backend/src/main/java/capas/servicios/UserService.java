package capas.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import capas.entidades.UserEntity;
import capas.repos.UserRepository;

import java.time.LocalDate;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public UserEntity registerUser(UserEntity user) {
        // Verificar si el correo electrónico ya está registrado
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("El correo electrónico ya está registrado.");
        }

        // Asignar la fecha de registro
        user.setRegistrationDate(LocalDate.now());

        // Guardar el usuario en la base de datos
        return userRepository.save(user);
    }
    public boolean userExists(String email) {
        return userRepository.existsByEmail(email); // Utiliza el método del repositorio
    }
}

