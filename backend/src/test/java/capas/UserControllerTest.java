package capas;

import capas.controladores.UserController;
import capas.entidades.UserEntity;
import capas.servicios.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void testRegisterUser_Success() throws Exception {
        // Datos de entrada para el usuario
        UserEntity user = new UserEntity();
        user.setEmail("testuser@example.com");
        user.setPassword("securePassword");

        // Comportamiento simulado con doAnswer (para simular un método void)
        doAnswer(invocation -> {
            // Aquí puedes agregar lógica extra si es necesario
            return null;  // Simula que no hace nada
        }).when(userService).registerUser(any(UserEntity.class));

        // Realiza la solicitud POST y verifica la respuesta
        mockMvc.perform(post("/api/register")
                        .contentType("application/json")
                        .content("{\"email\":\"testuser@example.com\", \"password\":\"securePassword\"}")
                )
                .andExpect(status().isCreated())  // Verifica que el estado sea 201
                .andExpect(content().string("Usuario registrado con éxito"));  // Verifica el mensaje

        // Verifica que el servicio fue llamado correctamente
        verify(userService).registerUser(any(UserEntity.class));  // Verifica que registerUser fue llamado con cualquier objeto UserEntity
    }


    @Test
    public void testRegisterUser_Failure() throws Exception {
        // Datos de entrada para el usuario
        UserEntity user = new UserEntity();
        user.setEmail("testuser@example.com");
        user.setPassword("securePassword");

        // Simulamos un error al registrar el usuario
        when(userService.registerUser(any(UserEntity.class)))
                .thenThrow(new IllegalArgumentException("El correo electrónico ya está registrado"));

        // Realiza la solicitud POST y verifica que la respuesta sea un error 400
        mockMvc.perform(post("/api/register")
                        .contentType("application/json")
                        .content("{\"email\":\"testuser@example.com\", \"password\":\"securePassword\"}")
                )
                .andExpect(status().isBadRequest())
                .andExpect(content().string("El correo electrónico ya está registrado"));

        // Verifica que el servicio fue llamado correctamente
        verify(userService).registerUser(any(UserEntity.class));
    }
}
