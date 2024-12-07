import React from 'react';
import { BrowserRouter as Router, Routes, Route, Link, useNavigate } from 'react-router-dom';
import './App.css';
import CreditSimulation from './CreditSimulation.jsx';
import Register from './Register.jsx'; 
import CreditRequest from './CreditRequest.jsx'; // Importa el componente de solicitud de crédito

function HomePage() {
  const navigate = useNavigate();

  const handleClick = (path) => {
    navigate(path); // Permite navegar a rutas dinámicas
  };

  return (
    <div className="card">
      <p>Selecciona una opción</p>
      <button onClick={() => handleClick('/simulate')}>Simulación de Crédito</button>
      <button className="button2" onClick={() => handleClick('/register')}>
        Registrarse
      </button>
      <button className="button3" onClick={() => handleClick('/credit-request')}>
        Solicitud de Crédito
      </button> 
    </div>
  );
}

function App() {
  return (
    <Router>
      <header>
        <Link to="/" style={{ textDecoration: 'none', fontSize: '2em', color: 'inherit' }}>
          PrestaBanco
        </Link>
      </header>

      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/simulate" element={<CreditSimulation />} />
        <Route path="/register" element={<Register />} />
        <Route path="/credit-request" element={<CreditRequest />} /> {/* Nueva ruta para solicitud de crédito */}
      </Routes>
    </Router>
  );
}

export default App;
