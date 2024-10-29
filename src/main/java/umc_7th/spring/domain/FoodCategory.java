package umc_7th.spring.domain;

import jakarta.persistence.*;
import lombok.*;
import umc_7th.spring.domain.common.BaseEntity;
import umc_7th.spring.domain.mapping.MemberPrefer;

import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class FoodCategory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}
