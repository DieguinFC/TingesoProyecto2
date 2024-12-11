import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';

export default defineConfig({
  plugins: [react()],
  server: {
    proxy: {
      '/api': {
        target: 'http://localhost:8080', // Puerto API Gateway
        changeOrigin: true,
        secure: false, // Cambiar a true si usas HTTPS
      },
    },
  },
});
