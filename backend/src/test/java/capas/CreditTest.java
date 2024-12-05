package capas;

import capas.entidades.CreditRequestEntity;
import capas.repos.CreditRepository;
import capas.servicios.CreditService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
public class CreditTest {

    @InjectMocks
    private CreditService creditService;  // El servicio que estamos probando

    @Mock
    private CreditRepository creditRepository;  // El repositorio que será mockeado

    private CreditRequestEntity creditRequest;

    @BeforeEach
    void setUp() {
        // Configuramos el objeto de solicitud de crédito
        creditRequest = new CreditRequestEntity();
        creditRequest.setEmail("test@example.com");
        creditRequest.setRequestedAmount(BigDecimal.valueOf(200000)); // Monto solicitado
        creditRequest.setTermInYears(30); // Plazo de 30 años
    }

    @Test
    void testCreateCreditRequest() {
        // Simulamos la llamada al repositorio para que retorne el mismo objeto de solicitud
        when(creditRepository.save(creditRequest)).thenReturn(creditRequest);

        // Llamamos al método del servicio
        CreditRequestEntity createdRequest = creditService.createCreditRequest(creditRequest);

        // Verificamos que el objeto creado no sea nulo y que el correo y monto sean correctos
        assertNotNull(createdRequest);
        assertEquals(creditRequest.getEmail(), createdRequest.getEmail());
        assertEquals(creditRequest.getRequestedAmount(), createdRequest.getRequestedAmount());

        // Verificamos que el repositorio haya sido llamado
        verify(creditRepository).save(creditRequest);
    }

    @Test
    void testGetCreditRequestById() {
        // Simulamos que el repositorio encuentre una solicitud de crédito por ID
        when(creditRepository.findById(1L)).thenReturn(Optional.of(creditRequest));

        // Llamamos al método del servicio
        CreditRequestEntity foundRequest = creditService.getCreditRequestById(1L);

        // Verificamos que la solicitud encontrada no sea nula y que sus valores sean correctos
        assertNotNull(foundRequest);
        assertEquals(creditRequest.getEmail(), foundRequest.getEmail());

        // Verificamos que el repositorio haya sido llamado con el ID esperado
        verify(creditRepository).findById(1L);
    }

    @Test
    void testGetAllCreditRequests() {
        // Simulamos que el repositorio retorne una lista de solicitudes
        when(creditRepository.findAll()).thenReturn(List.of(creditRequest));

        // Llamamos al método del servicio
        List<CreditRequestEntity> creditRequests = creditService.getAllCreditRequests();

        // Verificamos que la lista no sea vacía
        assertNotNull(creditRequests);
        assertFalse(creditRequests.isEmpty());
        assertEquals(1, creditRequests.size());  // Esperamos una solicitud en la lista

        // Verificamos que el repositorio haya sido llamado
        verify(creditRepository).findAll();
    }
}
