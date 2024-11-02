package study.service.StoreService;

import study.domain.Store;

import java.util.*;

public interface StoreQueryService {
    Optional<Store> findStore(Long id);
    List<Store> findStoresByNameAndScore(String name, Float score);
}
