package umc.spring.study.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc.spring.study.domain.Mission;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    // 특정 가게의 미션 목록을 페이징 처리하여 조회하는 메서드
    Page<Mission> findByStoreId(Long storeId, PageRequest pageRequest);


    // 사용자 ID와 미션 상태(진행 중)를 기준으로 미션을 조회하는 메서드 추가
    @Query("SELECT m FROM Mission m JOIN m.memberMissionList mm WHERE mm.member.id = :userId AND mm.status = 'CHALLENGING'")
    Page<Mission> findMissionsInProgressByUserId(@Param("userId") Long userId, Pageable pageable);
}

