package capas.servicios;

import capas.entidades.CreditRequestEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import capas.entidades.CreditRequestEntity;
import capas.repos.CreditRepository;

import java.util.List;

@Service
public class CreditService {

    @Autowired
    private CreditRepository creditRepository;

    public CreditRequestEntity createCreditRequest(CreditRequestEntity creditRequest) {
        return creditRepository.save(creditRequest);
    }

    // Obtiene todas las solicitudes de crédito (opcional)
    public List<CreditRequestEntity> getAllCreditRequests() {
        return creditRepository.findAll();
    }

    // Obtiene una solicitud de crédito por ID
    public CreditRequestEntity getCreditRequestById(Long id) {
        return creditRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Solicitud de crédito no encontrada"));
    }
}
