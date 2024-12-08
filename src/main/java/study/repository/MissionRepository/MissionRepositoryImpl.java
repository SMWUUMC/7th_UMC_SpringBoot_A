package study.repository.MissionRepository;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import study.domain.*;
import study.domain.QMission;
import study.domain.enums.MissionStatus;
import study.domain.mapping.MemberMission;
import study.domain.mapping.QMemberMission;
import study.web.dto.MissionResponseDTO;

import org.springframework.data.domain.Pageable;
import java.time.LocalDateTime;
import java.util.*;

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
        // 페이지네이션 정보에서 offset과 limit을 가져옵니다.
        long offset = pageable.getOffset();
        int limit = pageable.getPageSize();

        // 정렬 정보 가져오기
        List<OrderSpecifier> orderSpecifiers = getOrderSpecifiers(pageable);

        // 쿼리 생성
        List<MemberMission> content = jpaQueryFactory.selectFrom(memberMission)
                .join(memberMission.member, member)
                .join(memberMission.mission, mission)
                .join(mission.store, store)
                .where(member.id.eq(memberId),
                        memberMission.status.eq(MissionStatus.CHALLENGING))
                .offset(offset) // 페이지네이션 시작 인덱스
                .limit(limit)   // 페이지네이션 한 페이지 크기
                .orderBy(orderSpecifiers.toArray(new OrderSpecifier[0])) // 정렬 처리
                .fetch();

        long total = content.size();

        // 페이지 객체 반환
        return new PageImpl<>(content, pageable, total);
    }

    @Override
    public Page<MemberMission> findIndividualCompleteMission(Long memberId, Pageable pageable){
        // 페이지네이션 정보에서 offset과 limit을 가져옵니다.
        long offset = pageable.getOffset();
        int limit = pageable.getPageSize();

        // 정렬 정보 가져오기
        List<OrderSpecifier> orderSpecifiers = getOrderSpecifiers(pageable);

        // 쿼리 생성
        List<MemberMission> content = jpaQueryFactory.selectFrom(memberMission)
                .join(memberMission.member, member)
                .join(memberMission.mission, mission)
                .join(mission.store, store)
                .where(member.id.eq(memberId),
                        memberMission.status.eq(MissionStatus.COMPLETE))
                .offset(offset) // 페이지네이션 시작 인덱스
                .limit(limit)   // 페이지네이션 한 페이지 크기
                .orderBy(orderSpecifiers.toArray(new OrderSpecifier[0])) // 정렬 처리
                .fetch();

        long total = content.size();

        // 페이지 객체 반환
        return new PageImpl<>(content, pageable, total);
    }

    private List<OrderSpecifier> getOrderSpecifiers(Pageable pageable) {
        List<OrderSpecifier> orderSpecifiers = new ArrayList<>();
        for (Sort.Order order : pageable.getSort()) {
            Path<?> path = getPathForOrder(order.getProperty());
            Order orderDirection = order.isDescending() ? Order.DESC : Order.ASC;
            orderSpecifiers.add(new OrderSpecifier(orderDirection, path));
        }
        return orderSpecifiers;
    }

    private Path<?> getPathForOrder(String property) {
        switch (property) {
            case "mission.id":
                return mission.id;
            case "mission.reward":
                return mission.reward;
            case "store.name":
                return store.name;
            default:
                return mission.id; // 기본값은 mission.id로 정렬
        }
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
