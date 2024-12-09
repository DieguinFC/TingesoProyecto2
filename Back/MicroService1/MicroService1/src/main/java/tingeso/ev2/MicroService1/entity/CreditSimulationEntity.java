package tingeso.ev2.MicroService1.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreditSimulationEntity {

    private Long id;
    private BigDecimal loanAmount;
    private float annualInterestRate;
    private int term;
    private String email;
}
