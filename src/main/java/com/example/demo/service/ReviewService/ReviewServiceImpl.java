package com.example.demo.service.ReviewService;

import com.example.demo.base.code.status.ErrorStatus;
import com.example.demo.base.exception.CustomException;
import com.example.demo.converter.ReviewConverter;
import com.example.demo.domain.Member;
import com.example.demo.domain.Review;
import com.example.demo.domain.Store;
import com.example.demo.dto.ReviewRequestDTO;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.ReviewRepository;
import com.example.demo.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public Review createReview(ReviewRequestDTO.PostReviewDTO request) {
        Review review = ReviewConverter.toReview(request);
        Store store = storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new CustomException(ErrorStatus.STORE_NOT_FOUND));
        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new CustomException(ErrorStatus.MEMBER_NOT_FOUND));
        review.setStore(store);
        review.setMember(member);
        return reviewRepository.save(review);
    }

}
