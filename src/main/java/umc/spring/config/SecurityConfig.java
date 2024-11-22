package umc.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests)->requests
                        .requestMatchers("/", "/home", "/signup", "/users/signup", "/css/**", "/users/login", "/login").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                //폼 기반 로그인 설정
                .formLogin((form) -> form
                        .loginPage("/login") //커스텀 로그인 페이지를 /login 경로로 지정
                        .defaultSuccessUrl("/home", true) //로그인 성공 시 /home으로 리다이렉트
                        .permitAll() //모든 사용자가 접근 가능하도록 설정
                )
                .logout((logout) -> logout
                        .logoutUrl("/logout")  // /logout 경로로 로그아웃 처리
                        .logoutSuccessUrl("/login?logout")  //로그아웃 성공 시 /login?logout으로 리다이렉트
                        .permitAll()
                )
                //OAuth 설정
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/login")
                        .defaultSuccessUrl("/home", true)
                        .permitAll()
                );

        return http.build();
    }

    // 비밀번호를 암호화하여 저장하기 위해 사용
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
