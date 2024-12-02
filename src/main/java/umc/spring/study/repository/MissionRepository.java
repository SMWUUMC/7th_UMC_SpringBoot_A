package umc.spring.study.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.study.domain.Mission;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    // 특정 가게의 미션 목록을 페이징 처리하여 조회하는 메서드
    Page<Mission> findByStoreId(Long storeId, PageRequest pageRequest);
}

