package umc.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.PageableHandlerMethodArgumentResolverCustomizer;

//페이지 인덱스를 1부터 시작하게끔 바꿔줌 (안하면 0부터 시작함. 프론트로부터 요청은 1부터 들어옴)
@Configuration
public class CustomPageableConfiguration {
    @Bean
    public PageableHandlerMethodArgumentResolverCustomizer customize(){
        return p->p.setOneIndexedParameters(true);
    }
}
