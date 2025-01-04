package umc_7th.spring.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity // Spring Security 활성화
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests // HTTP 요청에 대한 접근 제어
                        // 특정 URL 패턴 접근 권한 설정, 인증없이 접근 가능한 경로 지정, member/signup으로 로그인 하지 않아도 새로 생성한 회원가입 API 호출 가능
                        .requestMatchers("/", "/home", "/signup","/members/signup", "/css/**").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN") // ADMIN 역할 가진 사용자만 접근 가능
                        .anyRequest().authenticated() // 그 외 모든 요청에 대해 인증 요구
                )
                .formLogin((form) -> form
                        .loginPage("/login") // 커스텀 로그인 페이지를 /login 경로로 지정
                        .defaultSuccessUrl("/home", true) // 로그인 성공 시, /home으로 리다이렉트
                        .permitAll() // 모든 사용자 접근 가능
                )
                .logout((logout) -> logout
                        .logoutUrl("/logout") // /logout 경로로 처리
                        .logoutSuccessUrl("/login?logout") // 성공 시, 리다이렉트
                        .permitAll()
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // 비밀번호 암호화하여 저장
    }
}
