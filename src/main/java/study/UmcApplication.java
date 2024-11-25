package study;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import study.service.MemberService.MemberQueryService;
import study.service.MissionService.MissionQueryService;
import study.service.ReviewService.ReviewCommandService;
import study.service.StoreService.StoreQueryService;
import study.web.dto.MissionRequestDTO;
import study.web.dto.MissionResponseDTO;
import study.web.dto.ReviewRequestDTO;
import java.util.List;
@SpringBootApplication
@EnableJpaAuditing
public class UmcApplication {

    public static void main(String[] args) {
        SpringApplication.run(UmcApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(ApplicationContext context) {
        return args -> {
            StoreQueryService storeService = context.getBean(StoreQueryService.class);
            MemberQueryService memberService = context.getBean(MemberQueryService.class);
            MissionQueryService missionService = context.getBean(MissionQueryService.class);
            ReviewCommandService reviewService = context.getBean(ReviewCommandService.class);

            // 파라미터 값 설정
            String name = "요아정";
            Float score = 4.0f;
            Long id = 1L;
            String status = "CHALLENGING";
            Long regionId = 1L;

            // 쿼리 메서드 호출 및 쿼리 문자열과 파라미터 출력
            System.out.println("Executing findStoresByNameAndScore with parameters:");
            System.out.println("Store Name: " + name);
            System.out.println("Store Score: " + score);
            System.out.println("Member id: " + id);
            System.out.println("Member status: " + status);

            storeService.findStoresByNameAndScore(name, score)
                    .forEach(System.out::println);
            System.out.println(memberService.findMemberDetails(id).orElseThrow());
            System.out.println("w진행중 미션w");
            List<MissionResponseDTO.FindIndividualMissionResultDto> missions = missionService.findIndividualMissionByMissionStatus(new MissionRequestDTO.FindIndividualMissionDto(id, status, regionId));
            for(MissionResponseDTO.FindIndividualMissionResultDto dto : missions)
                System.out.println(dto.getMissionId() + dto.getMissionSpec());

            status = "COMPLETE";
            System.out.println("완료 미션w");
            missions = missionService.findIndividualMissionByMissionStatus(new MissionRequestDTO.FindIndividualMissionDto(id, status, regionId));
            for(MissionResponseDTO.FindIndividualMissionResultDto dto : missions)
                System.out.println(dto.getMissionId() +dto.getMissionSpec());

            status = "";
            System.out.println("수령 가능한 미션w");
            missions = missionService.findIndividualMissionByMissionStatus(new MissionRequestDTO.FindIndividualMissionDto(id, status, regionId));
            System.out.println(missions.size());
            for(MissionResponseDTO.FindIndividualMissionResultDto dto : missions)
                System.out.println(dto.getMissionId() + dto.getMissionSpec());
            //System.out.println(reviewService.createReview(new ReviewRequestDTO.JoinDTO(id, missions.get(0).getStoreId(), "냠굿!", 5.0f)).getReviewId());

        };
    }

}
