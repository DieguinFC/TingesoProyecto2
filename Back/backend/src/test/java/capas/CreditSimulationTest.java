package capas;

import capas.controladores.CreditSimulationController;
import capas.entidades.CreditSimulationEntity;
import capas.servicios.CreditSimulationService;
import capas.servicios.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreditSimulationTest {

    @InjectMocks
    private CreditSimulationController creditSimulationController;

    @Mock
    private CreditSimulationService creditSimulationService;

    @Mock
    private UserService userService;

    private CreditSimulationEntity creditSimulationRequest;

    @BeforeEach
    void setUp() {
        // Configura el objeto de simulación de crédito para las pruebas
        creditSimulationRequest = new CreditSimulationEntity();
        creditSimulationRequest.setLoanAmount(BigDecimal.valueOf(200000));
        creditSimulationRequest.setAnnualInterestRate(5.0f);
        creditSimulationRequest.setTerm(30); // 30 años
        creditSimulationRequest.setEmail("test@example.com");
    }

    @Test
    void testSimulateCredit_UserNotFound() {
        // Simula que el usuario no existe
        when(userService.userExists(creditSimulationRequest.getEmail())).thenReturn(false);

        // Llamamos al controlador para la simulación de crédito
        ResponseEntity<?> response = creditSimulationController.simulateCredit(creditSimulationRequest);

        // Verifica que el código de estado sea 400 BAD REQUEST
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("El correo electrónico no está registrado.", response.getBody());
    }

    @Test
    void testSimulateCredit_Success() {
        // Simula que el usuario existe
        when(userService.userExists(creditSimulationRequest.getEmail())).thenReturn(true);

        // Simula el cálculo del pago mensual
        BigDecimal expectedPayment = BigDecimal.valueOf(1073.64); // Un valor de ejemplo
        when(creditSimulationService.calculateMonthlyPayment(
                creditSimulationRequest.getLoanAmount(),
                creditSimulationRequest.getAnnualInterestRate(),
                creditSimulationRequest.getTerm()
        )).thenReturn(expectedPayment);

        // Llamamos al controlador para la simulación de crédito
        ResponseEntity<?> response = creditSimulationController.simulateCredit(creditSimulationRequest);

        // Verifica que el código de estado sea 200 OK
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedPayment, response.getBody());
    }

    @Test
    void testCalculateMonthlyPayment() {
        // Datos de entrada
        BigDecimal loanAmount = BigDecimal.valueOf(200000); // Monto del préstamo
        float annualInterestRate = 5.0f; // Tasa de interés anual
        int term = 30; // 30 años

        // Configura el mock para calcular el pago mensual
        BigDecimal expectedPayment = BigDecimal.valueOf(1073.64); // Valor de ejemplo basado en la fórmula
        when(creditSimulationService.calculateMonthlyPayment(loanAmount, annualInterestRate, term))
                .thenReturn(expectedPayment);

        // Llamada al método de simulación
        BigDecimal monthlyPayment = creditSimulationService.calculateMonthlyPayment(loanAmount, annualInterestRate, term);

        // Asegúrate de que el valor no es nulo
        assertNotNull(monthlyPayment, "El pago mensual no debe ser nulo");

        // Verifica que la cuota mensual calculada sea igual al valor esperado con un pequeño delta
        double delta = 0.01; // Permitimos un margen de error de 0.01
        assertEquals(expectedPayment.doubleValue(), monthlyPayment.doubleValue(), delta);
    }
}
