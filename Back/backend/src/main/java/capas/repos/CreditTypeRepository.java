package capas.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import capas.entidades.CreditType;

@Repository
public interface CreditTypeRepository extends JpaRepository<CreditType, Long> {
    // Puedes agregar métodos personalizados aquí si es necesario
}
