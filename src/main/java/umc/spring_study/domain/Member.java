package umc.spring_study.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.spring_study.domain.common.BaseEntity;
import umc.spring_study.domain.enums.Gender;
import umc.spring_study.domain.enums.MemberStatus;
import umc.spring_study.domain.enums.SocialType;
import umc.spring_study.domain.mapping.MemberAgree;
import umc.spring_study.domain.mapping.MemberMission;
import umc.spring_study.domain.mapping.MemberPrefer;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    private String specAddress;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    @Enumerated(EnumType.STRING)
    private MemberStatus status;

    private LocalDate inactiveDate;

    private String email;

    private Integer point;

    @OneToMany(mappedBy = "member")
    private List<MemberMission> memberMissions;

    @OneToMany(mappedBy = "member")
    private List<Review> reviews;

    @OneToMany(mappedBy = "member")
    private List<MemberAgree> memberAgrees;

    @OneToMany(mappedBy = "member")
    private List<MemberPrefer> memberPrefers;
}
