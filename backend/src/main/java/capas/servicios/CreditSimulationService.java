package capas.servicios;

import org.springframework.stereotype.Service;
        import java.math.BigDecimal;
        import java.math.MathContext;

@Service
public class CreditSimulationService {

    public BigDecimal calculateMonthlyPayment(BigDecimal loanAmount, float annualInterestRate, int term) {
        // Tasa de interés mensual
        float monthlyInterestRate = annualInterestRate / 12 / 100;

        // Número total de pagos (plazo en meses)
        int numberOfPayments = term * 12;

        // Fórmula para calcular la cuota mensual
        BigDecimal r = BigDecimal.valueOf(monthlyInterestRate);
        BigDecimal onePlusRPowerN = r.add(BigDecimal.ONE).pow(numberOfPayments);
        BigDecimal numerator = loanAmount.multiply(r).multiply(onePlusRPowerN);
        BigDecimal denominator = onePlusRPowerN.subtract(BigDecimal.ONE);

        // Cuota mensual (M)
        BigDecimal monthlyPayment = numerator.divide(denominator, MathContext.DECIMAL128);
        return monthlyPayment;
    }
}
