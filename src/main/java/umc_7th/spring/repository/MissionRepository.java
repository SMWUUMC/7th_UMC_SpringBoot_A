package umc_7th.spring.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import umc_7th.spring.domain.Mission;
import umc_7th.spring.domain.Store;

public interface MissionRepository extends JpaRepository<Mission, Long> {
    Page<Mission> findAllByStore(Store store, PageRequest pageRequest); // 2. 특정 가게의 미션 목록 조회하기 API
}
