package capas;

import capas.servicios.CreditSimulationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class CreditSimulationServiceTest {

    private CreditSimulationService creditSimulationService;

    @BeforeEach
    void setUp() {
        creditSimulationService = new CreditSimulationService(); // Instanciamos el servicio
    }

    @Test
    void testCalculateMonthlyPayment() {
        // Datos de entrada
        BigDecimal loanAmount = BigDecimal.valueOf(200000); // Monto del préstamo
        float annualInterestRate = 5.0f; // Tasa de interés anual
        int term = 30; // 30 años

        // Llamada al método de simulación
        BigDecimal monthlyPayment = creditSimulationService.calculateMonthlyPayment(loanAmount, annualInterestRate, term);

        // El valor esperado de la cuota mensual (calculado por la fórmula)
        BigDecimal expectedPayment = BigDecimal.valueOf(1073.64); // Este valor debe ser igual al calculado por la fórmula

        // Verifica que la cuota mensual calculada sea igual al valor esperado con un pequeño margen de error
        double delta = 0.01; // Permitimos un margen de error de 0.01
        assertEquals(expectedPayment.doubleValue(), monthlyPayment.doubleValue(), delta, "El pago mensual calculado no es correcto.");
    }
}
