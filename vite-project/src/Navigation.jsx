import React from 'react';
import { useNavigate } from 'react-router-dom';

function Navigation() {
  const navigate = useNavigate();

  const goToHome = () => {
    navigate('/'); // Redirige a la página de inicio
  };

  return (
    <nav style={{ padding: '10px', backgroundColor: '#f5f5f5', textAlign: 'right' }}>
      {/* Botón que redirige siempre a la página de inicio */}
      <button onClick={goToHome} style={{ padding: '10px 20px', fontSize: '16px' }}>
        Ir a Home
      </button>
    </nav>
  );
}

export default Navigation;
