package umc_7th.spring.domain.mapping;

import jakarta.persistence.*;
import lombok.*;
import umc_7th.spring.domain.Member;
import umc_7th.spring.domain.Mission;
import umc_7th.spring.domain.common.BaseEntity;
import umc_7th.spring.domain.enums.MissionStatus;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MemberMission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private MissionStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id")
    private Mission mission;

    // 4. 가게의 미션을 도전 중인 미션에 추가 (미션 도전하기) API
    public void setMission(Mission mission) {
        this.mission = mission;
        mission.getMemberMissionList().add(this);
    }
}
