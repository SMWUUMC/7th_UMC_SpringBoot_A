package com.example.demo.service.FoodCategory;

import com.example.demo.repository.FoodCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class FoodCategoryServiceImpl implements FoodCategoryService {
    private final FoodCategoryRepository foodCategoryRepository;

    @Override
    public boolean isAllValid(List<Long> values) {
        return values.stream()
                .allMatch(value-> foodCategoryRepository.existsById(value));
    }
}
