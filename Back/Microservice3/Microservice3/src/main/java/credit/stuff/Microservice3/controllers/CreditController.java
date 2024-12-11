package credit.stuff.Microservice3.controllers;

import credit.stuff.Microservice3.entity.CreditRequestEntity;
import credit.stuff.Microservice3.entity.CreditType;
import credit.stuff.Microservice3.services.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")

public class CreditController {

    @Autowired
    private CreditService creditService;

    @Autowired
    private RestTemplate restTemplate; // Inyecta el RestTemplate

    // Crea una solicitud de crédito
    @PostMapping("/credit-request")
    public ResponseEntity<?> createCreditRequest(@RequestBody CreditRequestEntity creditRequest) {
        try {
            // Verifica si el usuario existe consultando al microservicio 2 usando RestTemplate
            String url = "http://microservice2/api/users/exists?email=" + creditRequest.getEmail();
            Boolean userExists = restTemplate.getForObject(url, Boolean.class); // Llamada GET al Microservicio 2

            // Si el usuario no existe, retorna un mensaje de error
            if (userExists == null || !userExists) {
                return ResponseEntity.badRequest().body("El correo electrónico no está registrado.");
            }

            // Verifica si el tipo de crédito proporcionado es válido (se espera un ID)
            if (creditRequest.getCreditType() == null || creditRequest.getCreditType().getId() == null) {
                return ResponseEntity.badRequest().body("Debe proporcionar un ID de tipo de crédito válido.");
            }

            // Busca el tipo de crédito por ID
            CreditType creditType = creditService.findCreditTypeById(creditRequest.getCreditType().getId());

            // Asigna el tipo de crédito encontrado a la solicitud
            creditRequest.setCreditType(creditType);

            // Si el correo es válido, procede a crear la solicitud de crédito
            CreditRequestEntity createdRequest = creditService.createCreditRequest(creditRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdRequest);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Hubo un error al procesar la solicitud de crédito: " + e.getMessage());
        }
    }


    // Método para obtener todas las solicitudes
    @GetMapping("/credit-show")
    public ResponseEntity<List<CreditRequestEntity>> getAllCreditRequests() {
        List<CreditRequestEntity> creditRequests = creditService.getAllCreditRequests();
        return ResponseEntity.ok(creditRequests);
    }

    // Método para obtener una solicitud por ID
    @GetMapping("/credit-request/{id}")
    public ResponseEntity<CreditRequestEntity> getCreditRequestById(@PathVariable Long id) {
        CreditRequestEntity creditRequest = creditService.getCreditRequestById(id);
        return ResponseEntity.ok(creditRequest);
    }
}
