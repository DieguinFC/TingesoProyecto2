package capas.controladores;

import capas.entidades.CreditSimulationEntity;
import capas.servicios.CreditSimulationService;
import capas.servicios.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import capas.entidades.CreditSimulationEntity;
import capas.servicios.CreditSimulationService;
import capas.servicios.UserService; // Importa el servicio de usuario
import java.math.BigDecimal;

@RestController
@RequestMapping("/api/creditsimulation")
@CrossOrigin(origins = "*")
public class CreditSimulationController {

    @Autowired
    private CreditSimulationService creditSimulationService;

    @Autowired
    private UserService userService; // Inyecta el servicio de usuario

    @PostMapping("/simulate")
    public ResponseEntity<?> simulateCredit(@RequestBody CreditSimulationEntity request) {
        // Verifica si el usuario existe
        if (!userService.userExists(request.getEmail())) {
            return ResponseEntity.badRequest().body("El correo electrónico no está registrado.");
        }

        // Si el usuario existe, procede con la simulación
        BigDecimal monthlyPayment = creditSimulationService.calculateMonthlyPayment(
                request.getLoanAmount(),
                request.getAnnualInterestRate(),
                request.getTerm()
        );

        return ResponseEntity.ok(monthlyPayment);
    }
}
