package credit.stuff.Microservice3.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import credit.stuff.Microservice3.entity.CreditRequestEntity;
import credit.stuff.Microservice3.entity.CreditType;
import credit.stuff.Microservice3.repositories.CreditRepository;
import credit.stuff.Microservice3.repositories.CreditTypeRepository;

import java.util.List;

@Service
public class CreditService {

    @Autowired
    private CreditRepository creditRepository;

    @Autowired
    private CreditTypeRepository creditTypeRepository; // Inyecta el repositorio de CreditType

    public CreditRequestEntity createCreditRequest(CreditRequestEntity creditRequest) {
        // Verificar si el tipo de crédito es válido
        CreditType creditType = creditRequest.getCreditType();
        if (creditType == null || creditType.getId() == null) {
            throw new IllegalArgumentException("El tipo de crédito no es válido o no fue proporcionado.");
        }

        // Validación del tipo de crédito, asegurando que existe en la base de datos
        CreditType existingCreditType = creditTypeRepository.findById(creditType.getId())
                .orElseThrow(() -> new RuntimeException("Tipo de crédito no encontrado"));

        // Asignamos el tipo de crédito válido a la solicitud
        creditRequest.setCreditType(existingCreditType);

        return creditRepository.save(creditRequest);
    }

    public List<CreditRequestEntity> getAllCreditRequests() {
        return creditRepository.findAll();
    }

    public CreditRequestEntity getCreditRequestById(Long id) {
        return creditRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Solicitud de crédito no encontrada"));
    }

    public CreditType findCreditTypeById(Long id) {
        return creditTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tipo de crédito no encontrado"));
    }
}
