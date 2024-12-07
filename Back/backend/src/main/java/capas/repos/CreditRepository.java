package capas.repos;

import capas.entidades.CreditRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditRepository extends JpaRepository<CreditRequestEntity, Long> {
}
