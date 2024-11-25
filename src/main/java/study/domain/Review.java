package study.domain;

import jakarta.persistence.*;
import lombok.*;
import study.domain.common.*;
import java.util.*;


@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 1000, nullable = false)
    private String body;

    @Column(precision = 2, nullable = false)
    private Float score;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL)
    private List<ReviewImage> reviewImageList = new ArrayList<>();

    public void setMember(Member member) {
        if(this.member != null)
            this.member.getReviewList().remove(this);
        this.member = member;
        member.getReviewList().add(this);
    }

    public void setStore(Store store) {
        if(this.store != null)
            this.store.getReviewList().remove(this);
        this.store = store;
        store.getReviewList().add(this);
    }
}
