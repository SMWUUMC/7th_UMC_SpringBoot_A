package study.repository.MemberRepository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import study.domain.*;

import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;
    private final QMember member = QMember.member;

    @Override
    public Optional<Member> findMemberDetails(Long memberId){
        return Optional.ofNullable(jpaQueryFactory
                .select(Projections.bean(Member.class,
                        member.id,
                        member.name,
                        member.email,
                        member.point
                ))
                .from(member)
                .where(member.id.eq(memberId))
                .fetchOne());
    }
}
