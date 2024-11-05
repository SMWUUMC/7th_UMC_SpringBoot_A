package umc_7th.spring.repository.StoreRepository;

import umc_7th.spring.domain.Store;

import java.util.List;

public interface StoreRepositoryCustom {
    List<Store> dynamicQueryWithBooleanBuilder(String name, Float score);

}
