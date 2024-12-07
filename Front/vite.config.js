import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [react()],
  server: {
    proxy: {
      '/api': {
        target: 'http://backcontainer:8090', // Dirección del backend de Spring Boot
        changeOrigin: true,
        secure: false,
      },
    },
  },
});
