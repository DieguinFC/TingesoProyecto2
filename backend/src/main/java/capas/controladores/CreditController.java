package capas.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import capas.entidades.CreditRequestEntity;
import capas.servicios.CreditService;
import capas.servicios.UserService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class CreditController {

    @Autowired
    private CreditService creditService;

    @Autowired
    private UserService userService; // Inyecta el servicio de usuario

    // Crea una solicitud de crédito
    @PostMapping("/credit-request")
    public ResponseEntity<?> createCreditRequest(@RequestBody CreditRequestEntity creditRequest) {
        // Verifica si el usuario existe
        if (!userService.userExists(creditRequest.getEmail())) {
            return ResponseEntity.badRequest().body("El correo electrónico no está registrado.");
        }

        // Si el correo es válido, procede a crear la solicitud de crédito
        CreditRequestEntity createdRequest = creditService.createCreditRequest(creditRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRequest);
    }

    // Método para obtener todas las solicitudes (opcional, si es necesario)
    @GetMapping("/credit-show")
    public ResponseEntity<List<CreditRequestEntity>> getAllCreditRequests() {
        List<CreditRequestEntity> creditRequests = creditService.getAllCreditRequests();
        return ResponseEntity.ok(creditRequests);
    }

    // Método para obtener una solicitud por ID para revisión (opcional)
    @GetMapping("/credit-request/{id}")
    public ResponseEntity<CreditRequestEntity> getCreditRequestById(@PathVariable Long id) {
        CreditRequestEntity creditRequest = creditService.getCreditRequestById(id);
        return ResponseEntity.ok(creditRequest);
    }
}
