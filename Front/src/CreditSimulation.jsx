import React, { useState } from 'react';
import axios from 'axios';
import './CreditSimulation.css';

function CreditSimulation() {
  // Estados para almacenar los datos del formulario
  const [loanAmount, setLoanAmount] = useState('');
  const [annualInterestRate, setAnnualInterestRate] = useState('');
  const [term, setTerm] = useState('');
  const [email, setEmail] = useState('');
  const [monthlyPayment, setMonthlyPayment] = useState(null);
  const [errorMessage, setErrorMessage] = useState('');

  const handleSimulate = async (e) => {
    e.preventDefault();
    setErrorMessage(''); // Limpiar mensaje de error antes de una nueva solicitud

    // Validación en el cliente
    if (loanAmount <= 0 || annualInterestRate <= 0 || term <= 0) {
      setErrorMessage('Todos los valores deben ser positivos.');
      return;
    }

    try {
      // Envía la solicitud al API Gateway
      const response = await axios.post('http://localhost:8080/api/creditsimulation/simulate', {
        loanAmount: parseFloat(loanAmount),
        annualInterestRate: parseFloat(annualInterestRate),
        term: parseInt(term, 10),
        email,
      });

      // Almacena el resultado en el estado
      setMonthlyPayment(response.data);
    } catch (error) {
      // Manejo de errores
      if (error.response) {
        const { status, data } = error.response;
        if (status === 400) {
          setErrorMessage(data); // Error del backend
        } else if (status === 404) {
          setErrorMessage('Servicio no encontrado.');
        } else {
          setErrorMessage('Error interno del servidor.');
        }
      } else {
        console.error('Error al realizar la simulación:', error);
        setErrorMessage('Error de red. Verifica tu conexión.');
      }
    }
  };

  return (
    <div className="credit-simulation-container">
      <h2 className="credit-simulation-title">Simulación de Crédito Hipotecario</h2>
      <form onSubmit={handleSimulate}>
        <input
          type="email"
          className="credit-simulation-input"
          placeholder="Correo Electrónico"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          required
        />
        <input
          type="number"
          className="credit-simulation-input"
          placeholder="Monto del Préstamo"
          value={loanAmount}
          onChange={(e) => setLoanAmount(e.target.value)}
          required
        />
        <input
          type="number"
          step="0.01"
          className="credit-simulation-input"
          placeholder="Tasa de Interés Anual (%)"
          value={annualInterestRate}
          onChange={(e) => setAnnualInterestRate(e.target.value)}
          required
        />
        <input
          type="number"
          className="credit-simulation-input"
          placeholder="Plazo en años"
          value={term}
          onChange={(e) => setTerm(e.target.value)}
          required
        />

        <button type="submit" className="credit-simulation-button">Calcular Cuota Mensual</button>
      </form>

      {/* Mostrar el resultado si está disponible */}
      {monthlyPayment !== null && (
        <div className="simulation-result">
          <h3>Resultado de la Simulación</h3>
          <p>Cuota Mensual: ${monthlyPayment.toFixed(2)}</p>
        </div>
      )}

      {/* Mostrar el mensaje de error si existe */}
      {errorMessage && (
        <div className="error-message">
          <p>{errorMessage}</p>
        </div>
      )}
    </div>
  );
}

export default CreditSimulation;
