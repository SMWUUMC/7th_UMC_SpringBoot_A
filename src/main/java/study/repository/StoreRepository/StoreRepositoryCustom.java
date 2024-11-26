package study.repository.StoreRepository;
import java.util.List;
import study.domain.Store;
public interface StoreRepositoryCustom {
    List<Store> dynamicQueryWithBooleanBuilder(String name, Float score);

    Store findByIdFetchJoinRegion(Long id);
}
