package umc_7th.spring.repository.StoreRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc_7th.spring.domain.Store;

public interface StoreRepository extends JpaRepository<Store, Long>, StoreRepositoryCustom {
}
