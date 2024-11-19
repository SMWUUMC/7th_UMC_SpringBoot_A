package com.example.demo.domian;

import com.example.demo.domian.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String body;

    private Float score;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false, foreignKey = @ForeignKey(name = "FK_member_id"))
    private Member member;

    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false, foreignKey = @ForeignKey(name = "FK_store_id"))
    private Store store;
}
