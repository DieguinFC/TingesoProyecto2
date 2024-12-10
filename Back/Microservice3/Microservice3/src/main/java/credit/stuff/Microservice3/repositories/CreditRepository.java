package credit.stuff.Microservice3.repositories;


import credit.stuff.Microservice3.entity.CreditRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditRepository extends JpaRepository<CreditRequestEntity, Long> {
}
