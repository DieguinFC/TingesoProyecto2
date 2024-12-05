package capas.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users") // Define el nombre de la tabla como 'users'
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true) // El correo debe ser único para evitar duplicados
    private String email;

    @Column(nullable = false)
    private String password; // Para almacenar la contraseña del usuario

    @Column(nullable = false)
    private BigDecimal income; // BigDecimal para valores monetarios

    @Column(name = "registration_date", nullable = false)
    private LocalDate registrationDate; // Fecha de registro del usuario
}
