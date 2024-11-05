package umc.spring_study.domain.mapping;

import jakarta.persistence.*;
import lombok.*;
import umc.spring_study.domain.common.BaseEntity;
import umc.spring_study.domain.enums.MissionStatus;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MemberMission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private MissionStatus status;
}