package capas.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true) // Asegura que el nombre del tipo de crédito sea único
    private String name; // Nombre del tipo de préstamo, ej. "Primera Vivienda"

    @Column(nullable = false)
    private int maxTerm; // Plazo máximo en años, ej. 30

    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal minInterestRate; // Tasa mínima de interés anual, ej. 3.5

    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal maxInterestRate; // Tasa máxima de interés anual, ej. 5.0

    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal maxFinancingPercentage; // Porcentaje máximo de financiamiento, ej. 80%
}
