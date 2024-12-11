package credit.stuff.Microservice3.repositories;

import credit.stuff.Microservice3.entity.CreditType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CreditTypeRepository extends JpaRepository<CreditType, Long> {
    // Puedes agregar métodos personalizados aquí si es necesario
}