package com.example.demo.domian.repository.StoreRepository;

import com.example.demo.domian.Store;

import java.util.List;


public interface StoreRepositoryCustom {
    List<Store> dynamicQueryWithBooleanBuilder(String name, Float score);
}