package study.repository.StoreRepository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import study.domain.QMission;
import study.domain.QRegion;
import study.domain.QStore;
import study.domain.Store;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class StoreRepositoryImpl implements StoreRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;
    private final QStore store = QStore.store;
    private final QMission mission = QMission.mission;
    private final QRegion region = QRegion.region;

    @Override
    public List<Store> dynamicQueryWithBooleanBuilder(String name, Float score) {
        BooleanBuilder predicate = new BooleanBuilder();

        if(name != null)
            predicate.and(store.name.eq(name));

        if(score != null)
            predicate.and(store.score.goe(4.0f));

        return jpaQueryFactory
                .selectFrom(store)
                .where(predicate)
                .fetch();
    }

    @Override
    public Store findByIdFetchJoinRegion(Long id) {
        return jpaQueryFactory.selectFrom(store)
                .leftJoin(store.region).fetchJoin()
                .where(store.id.eq(id))
                .fetchOne();

    }

}
