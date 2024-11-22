package umc.spring.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import umc.spring.user.domain.User;
import umc.spring.user.enums.Gender;
import umc.spring.user.enums.Role;
import umc.spring.user.repository.UserRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
//카카오 로그인 후 받은 사용자 정보를 처리하고 db에 저장
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        //카카오에서 제공하는 사용자 정보를 OAuth2User 객체로 받아온다.
        OAuth2User oAuth2User=super.loadUser(userRequest);

        //attributes : 사용자의 닉네임등 기본정보가 포함됨
        Map<String, Object> attributes = oAuth2User.getAttributes();
        Map<String, Object> properties=(Map<String, Object>) attributes.get("properties");

        String nickname=(String) properties.get("nickname");
        String email=nickname + "@kakao.com"; //임시 이메일 생성

        //사용자 정보 저장 또는 업데이트
        User user=saveOrUpdateUser(email, nickname);

        //이메일을 Principal(인증된 사용자를 나타내는 객체)로 사용하기 위해 attributes 수정
        Map<String, Object> modifiedAttributes=new HashMap<>(attributes);
        modifiedAttributes.put("email", email);

        return new DefaultOAuth2User(
                oAuth2User.getAuthorities(),
                modifiedAttributes,
                "email"
        );
    }

    private User saveOrUpdateUser(String email, String nickname) {
        User user=userRepository.findByEmail(email)
                .orElse(User.builder()
                                .email(email)
                                .name(nickname)
                                .password(passwordEncoder.encode("OAUTH_USER_" + UUID.randomUUID()))
                                .gender(Gender.NONE)
                                .address("소셜로그인")
                                .role(Role.USER)
                                .build());

        return userRepository.save(user);

    }
}
