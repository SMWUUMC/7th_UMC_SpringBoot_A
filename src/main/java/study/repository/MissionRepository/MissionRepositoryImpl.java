package study.repository.MissionRepository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Repository;
import study.domain.*;
import study.domain.QMission;
import study.domain.enums.MissionStatus;
import study.domain.mapping.MemberMission;
import study.domain.mapping.QMemberMission;
import study.web.dto.MissionResponseDTO;

import org.springframework.data.domain.Pageable;
import java.time.LocalDateTime;

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
    public Page<MemberMission> findIndividualChallengingMission(Long memberId, Pageable pageable){
        return new PageImpl<>(jpaQueryFactory.selectFrom(memberMission)
                .join(memberMission.member,member)
                .join(memberMission.mission,mission)
                .join(mission.store, store)
                .where(member.id.eq(memberId),
                        memberMission.status.eq(MissionStatus.CHALLENGING))
                .orderBy(mission.id.desc())
                .limit(10)
                .fetch());
    }

    @Override
    public Page<MemberMission> findIndividualCompleteMission(Long memberId, Pageable pageable){
        return new PageImpl<>(jpaQueryFactory.selectFrom(memberMission)
                .join(memberMission.member,member)
                .join(memberMission.mission,mission)
                .join(mission.store, store)
                .where(member.id.eq(memberId),
                        memberMission.status.eq(MissionStatus.COMPLETE))
                .orderBy(mission.id.desc())
                .limit(10)
                .fetch());
    }


    @Override
    public Page<Mission> findIndividualClaimableMission(Long memberId, Long regionId, Pageable pageable){
        return new PageImpl<Mission>(jpaQueryFactory.selectFrom(mission)
                .join(mission.store, store)
                .join(mission.region, region)
                .where(
                        mission.region.id.eq(regionId),
                        mission.id.notIn(
                                JPAExpressions.select(memberMission.mission.id)
                                        .from(memberMission)
                                        .where(memberMission.member.id.eq(memberId)
                        )
                ))
                .orderBy(mission.id.desc())
                .limit(10)
                .fetch());
    }
}
