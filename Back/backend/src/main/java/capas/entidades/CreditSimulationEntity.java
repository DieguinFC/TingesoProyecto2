package capas.entidades;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CreditSimulationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Genera un valor único automáticamente
    private Long id;
    private BigDecimal loanAmount;
    private float annualInterestRate;
    private int term;
    private String email;
}

