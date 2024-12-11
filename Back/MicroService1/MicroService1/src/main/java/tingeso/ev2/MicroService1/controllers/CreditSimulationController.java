package tingeso.ev2.MicroService1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import tingeso.ev2.MicroService1.entity.CreditSimulationEntity;
import tingeso.ev2.MicroService1.services.CreditSimulationService;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/creditsimulation")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")

public class CreditSimulationController {

    @Autowired
    private CreditSimulationService creditSimulationService;

    @Autowired
    private RestTemplate restTemplate; // Inyecta el RestTemplate

    // Endpoint para simular el crédito
    @PostMapping("/simulate")
    public ResponseEntity<?> simulateCredit(@RequestBody CreditSimulationEntity request) {
        // Verifica si el usuario existe consultando al microservicio 2 usando RestTemplate
        String url = "http://microservice2/api/users/exists?email=" + request.getEmail();
        Boolean userExists = restTemplate.getForObject(url, Boolean.class); // Llamada GET al Microservicio 2

        // Si el usuario no existe, retorna un mensaje de error
        if (userExists == null || !userExists) {
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
