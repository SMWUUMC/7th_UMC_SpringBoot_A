package study.repository.MissionRepository;

import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import study.domain.*;
import study.domain.QMission;
import study.domain.enums.MissionStatus;
import study.domain.mapping.QMemberMission;
import study.web.dto.MissionRequestDTO;
import study.web.dto.MissionResponseDTO;

import java.time.LocalDateTime;
import java.util.List;

import static study.domain.QRegion.region;

@Repository
@RequiredArgsConstructor
public class MissionRepositoryImpl implements MissionRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;
    private final QMission mission = QMission.mission;
    private final QMember member = QMember.member;
    private final QMemberMission memberMission = QMemberMission.memberMission;
    private final QStore store = QStore.store;

    @Override
    public List<MissionResponseDTO.FindIndividualMissionResultDto> findIndividualChallengingMission(Long memberId){
        return jpaQueryFactory.select(Projections.constructor(MissionResponseDTO.FindIndividualMissionResultDto.class,
                        mission.id,
                        store.id,
                        mission.reward,
                        mission.missionSpec,
                        store.name,
                        mission.deadline))
                .from(memberMission)
                .join(memberMission.member,member)
                .join(memberMission.mission,mission)
                .join(mission.store, store)
                .where(member.id.eq(memberId),
                        memberMission.status.eq(MissionStatus.CHALLENGING))
                .orderBy(mission.id.desc())
                .limit(10)
                .fetch();
    }

    @Override
    public List<MissionResponseDTO.FindIndividualMissionResultDto> findIndividualCompleteMission(Long memberId){
        return jpaQueryFactory.select(Projections.constructor(MissionResponseDTO.FindIndividualMissionResultDto.class,
                        mission.id,
                        store.id,
                        mission.reward,
                        mission.missionSpec,
                        store.name,
                        Expressions.constant(LocalDateTime.MIN)))
                .from(memberMission)
                .join(memberMission.member,member)
                .join(memberMission.mission,mission)
                .join(mission.store, store)
                .where(member.id.eq(memberId),
                        memberMission.status.eq(MissionStatus.COMPLETE))
                .orderBy(mission.id.desc())
                .limit(10)
                .fetch();
    }


    //정상 조회 안 됨
    @Override
    public List<MissionResponseDTO.FindIndividualMissionResultDto> findIndividualClaimableMission(MissionRequestDTO.FindIndividualMissionDto request){
        return jpaQueryFactory.select(Projections.constructor(MissionResponseDTO.FindIndividualMissionResultDto.class,
                mission.id,
                store.id,
                mission.reward,
                mission.missionSpec,
                store.name,
                mission.deadline))
                .from(mission)
                .join(mission.store, store)
                .join(mission.region, region)
                .where(
                        mission.region.id.eq(request.getRegionId()),
                        mission.id.notIn(
                                JPAExpressions.select(memberMission.mission.id)
                                        .from(memberMission)
                                        .where(memberMission.member.id.eq(request.getMemberId()))
                        )
                )
                .orderBy(mission.id.desc())
                .limit(10)
                .fetch();
    }
}
