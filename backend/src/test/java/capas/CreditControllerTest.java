package capas;

import capas.controladores.CreditController;
import capas.entidades.CreditRequestEntity;
import capas.servicios.CreditService;
import capas.servicios.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class CreditControllerTest {

    @InjectMocks
    private CreditController creditController;

    @Mock
    private CreditService creditService;

    @Mock
    private UserService userService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(creditController).build();
    }

    @Test
    public void testCreateCreditRequest_Success() throws Exception {
        // Datos de entrada para la solicitud de crédito
        CreditRequestEntity creditRequest = new CreditRequestEntity();
        creditRequest.setEmail("test@example.com");
        creditRequest.setRequestedAmount(BigDecimal.valueOf(200000));
        creditRequest.setTermInYears(30);

        // Comportamiento simulado de los servicios
        when(userService.userExists(creditRequest.getEmail())).thenReturn(true);
        when(creditService.createCreditRequest(creditRequest)).thenReturn(creditRequest);

        // Realiza la solicitud POST y verifica la respuesta
        mockMvc.perform(post("/api/credit-request")
                        .contentType("application/json")
                        .content("{\"email\":\"test@example.com\", \"requestedAmount\":200000, \"termInYears\":30}")
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.email").value("test@example.com"))
                .andExpect(jsonPath("$.requestedAmount").value(200000))
                .andExpect(jsonPath("$.termInYears").value(30));

        // Verifica que los servicios fueron llamados correctamente
        verify(userService).userExists(creditRequest.getEmail());
        verify(creditService).createCreditRequest(creditRequest);
    }

    @Test
    public void testCreateCreditRequest_UserNotFound() throws Exception {
        // Datos de entrada para la solicitud de crédito
        CreditRequestEntity creditRequest = new CreditRequestEntity();
        creditRequest.setEmail("test@example.com");
        creditRequest.setRequestedAmount(BigDecimal.valueOf(200000));
        creditRequest.setTermInYears(30);

        // Comportamiento simulado de los servicios
        when(userService.userExists(creditRequest.getEmail())).thenReturn(false);

        // Realiza la solicitud POST y verifica que el correo no esté registrado
        mockMvc.perform(post("/api/credit-request")
                        .contentType("application/json")
                        .content("{\"email\":\"test@example.com\", \"requestedAmount\":200000, \"termInYears\":30}")
                )
                .andExpect(status().isBadRequest())
                .andExpect(content().string("El correo electrónico no está registrado."));

        // Verifica que el servicio de usuario fue llamado
        verify(userService).userExists(creditRequest.getEmail());
    }

    @Test
    public void testGetCreditRequestById() throws Exception {
        // Datos de entrada
        Long id = 1L;
        CreditRequestEntity creditRequest = new CreditRequestEntity();
        creditRequest.setId(id);
        creditRequest.setEmail("test@example.com");
        creditRequest.setRequestedAmount(BigDecimal.valueOf(200000));
        creditRequest.setTermInYears(30);

        // Comportamiento simulado de los servicios
        when(creditService.getCreditRequestById(id)).thenReturn(creditRequest);

        // Realiza la solicitud GET y verifica la respuesta
        mockMvc.perform(get("/api/credit-request/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.email").value("test@example.com"))
                .andExpect(jsonPath("$.requestedAmount").value(200000))
                .andExpect(jsonPath("$.termInYears").value(30));

        // Verifica que el servicio fue llamado
        verify(creditService).getCreditRequestById(id);
    }

    @Test
    public void testGetAllCreditRequests() throws Exception {
        // Datos de entrada
        CreditRequestEntity creditRequest1 = new CreditRequestEntity(1L, "test1@example.com", BigDecimal.valueOf(100000), 15, null);
        CreditRequestEntity creditRequest2 = new CreditRequestEntity(2L, "test2@example.com", BigDecimal.valueOf(200000), 20, null);

        // Comportamiento simulado de los servicios
        when(creditService.getAllCreditRequests()).thenReturn(List.of(creditRequest1, creditRequest2));

        // Realiza la solicitud GET y verifica la respuesta
        mockMvc.perform(get("/api/credit-show"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].email").value("test1@example.com"))
                .andExpect(jsonPath("$[1].email").value("test2@example.com"));

        // Verifica que el servicio fue llamado
        verify(creditService).getAllCreditRequests();
    }
}
