package capas;

import capas.controladores.UserController;
import capas.entidades.UserEntity;
import capas.repos.UserRepository;
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
class UserTests {

	@InjectMocks
	private UserController userController;  // @InjectMocks solo en el controlador

	@InjectMocks
	private UserService userService;  // Mock de UserService

	@Mock
	private UserRepository userRepository;  // Mock de UserRepository

	@BeforeEach
	void setUp() {
		// Esta línea ya no es necesaria si usamos @ExtendWith(MockitoExtension.class)
		// MockitoAnnotations.openMocks(this);
	}

	@Test
	void testRegisterUser_Success() {
		UserEntity user = new UserEntity();
		user.setEmail("test@example.com");
		user.setName("Test User");
		user.setPassword("password123");
		user.setIncome(BigDecimal.valueOf(1000));

		// Simula que el correo no está registrado en el repositorio
		when(userRepository.existsByEmail(user.getEmail())).thenReturn(false);

		// Simula el comportamiento del repositorio al guardar el usuario
		when(userRepository.save(user)).thenReturn(user);

		// Llamamos al método que estamos probando
		UserEntity result = userService.registerUser(user);

		// Verifica que el resultado no sea null y que la fecha de registro esté establecida
		assertNotNull(result);
		assertEquals(user.getEmail(), result.getEmail());
		assertNotNull(result.getRegistrationDate());  // Verifica que la fecha de registro no sea nula
	}
	@Test
	void testUserService_RegisterUser_Success() {
		UserEntity user = new UserEntity();
		user.setEmail("test@example.com");

		// Simula que el correo no existe en el repositorio
		when(userService.userExists(user.getEmail())).thenReturn(false);
		when(userService.registerUser(user)).thenReturn(user);

		UserEntity savedUser = userService.registerUser(user);

		assertNotNull(savedUser);
		assertEquals(user.getEmail(), savedUser.getEmail());
	}
	@Test
	void testUserService_RegisterUser_EmailAlreadyExists() {
		UserEntity user = new UserEntity();
		user.setEmail("test@example.com");

		// Simula que el correo ya está registrado en el repositorio
		when(userRepository.existsByEmail(user.getEmail())).thenReturn(true);

		// Ahora, al intentar registrar el usuario, debería lanzarse la excepción IllegalArgumentException
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			userService.registerUser(user);  // Esto debería lanzar la excepción
		});

		// Verifica que el mensaje de la excepción sea el esperado
		assertEquals("El correo electrónico ya está registrado.", exception.getMessage());
	}
}