import React, { useState } from 'react';
import axios from 'axios';
import './Register.css';

function Register() {
  const [formData, setFormData] = useState({
    name: '',
    email: '',
    password: '',
    income: '',
  });
  const [message, setMessage] = useState('');

  // Maneja los cambios en los campos del formulario
  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  // Maneja el envío del formulario
  const handleRegister = async (e) => {
    e.preventDefault();
    setMessage(''); // Limpia el mensaje anterior

    // Validación en el cliente
    if (!formData.name || !formData.email || !formData.password || !formData.income) {
      setMessage('Por favor, complete todos los campos.');
      return;
    }

    try {
      // Envía los datos al backend a través del API Gateway
      const response = await axios.post('http://localhost:8080/api/register', formData);
      setMessage('Registro exitoso. ¡Bienvenido!');
    } catch (error) {
      // Manejo de errores
      if (error.response) {
        const { status, data } = error.response;
        if (status === 400) {
          setMessage(data); // Mensaje del backend
        } else if (status === 500) {
          setMessage('Error interno del servidor.');
        } else {
          setMessage('Hubo un error al registrar el usuario.');
        }
      } else {
        console.error('Error de red:', error);
        setMessage('No se pudo conectar al servidor. Verifique su conexión.');
      }
    }
  };

  return (
    <div className="register-container">
      <h2>Registro de Usuario</h2>
      <form onSubmit={handleRegister}>
        <input
          className="register-input"
          type="text"
          name="name"
          placeholder="Nombre"
          value={formData.name}
          onChange={handleChange}
          required
        />
        <input
          className="register-input"
          type="email"
          name="email"
          placeholder="Correo Electrónico"
          value={formData.email}
          onChange={handleChange}
          required
        />
        <input
          className="register-input"
          type="password"
          name="password"
          placeholder="Contraseña"
          value={formData.password}
          onChange={handleChange}
          required
        />
        <input
          className="register-input"
          type="number"
          name="income"
          placeholder="Ingreso Mensual"
          value={formData.income}
          onChange={handleChange}
          required
        />
        <button className="register-button" type="submit">
          Registrarse
        </button>
      </form>
      {message && <p className="register-text">{message}</p>}
    </div>
  );
}

export default Register;
