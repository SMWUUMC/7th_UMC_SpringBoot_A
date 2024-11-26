package study.service.StoreService;

import study.domain.Store;

import java.util.*;

public interface StoreQueryService {
    List<Store> findStoresByNameAndScore(String name, Float score);

    boolean isValid(Long id);
}
