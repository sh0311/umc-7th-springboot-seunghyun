package umc.spring.mapping.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.mapping.domain.UserMission;
import umc.spring.mapping.repository.UserMissionRepository;
import umc.spring.mission.domain.Mission;
import umc.spring.mission.dto.MissionRequestDto;
import umc.spring.user.domain.User;
import umc.spring.user.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly=true)
public class UserMissionService {

    private final UserRepository userRepository;
    private final UserMissionRepository userMissionRepository;

    @Transactional
    public void createUserMission(Mission mission) {

        //모든 유저 찾기
        List<User> allUsers=userRepository.findAll();

        //모든 유저에게 새로운 UserMission 추가해주기
        allUsers.forEach(user -> {
            UserMission userMission = UserMission.builder()
                    .user(user)
                    .mission(mission)
                    .build();
            userMissionRepository.save(userMission);
        });

    }
}
