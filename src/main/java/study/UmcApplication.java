package study;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import study.service.MemberService.MemberQueryService;
import study.service.StoreService.StoreQueryService;

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

            // 파라미터 값 설정
            String name = "요아정";
            Float score = 4.0f;
            Long id = 1L;

            // 쿼리 메서드 호출 및 쿼리 문자열과 파라미터 출력
            System.out.println("Executing findStoresByNameAndScore with parameters:");
            System.out.println("Store Name: " + name);
            System.out.println("Store Score: " + score);
            System.out.println("Member id: " + id);

            storeService.findStoresByNameAndScore(name, score)
                    .forEach(System.out::println);
            System.out.println(memberService.findMemberDetails(id).orElseThrow().toString());
        };
    }

}
