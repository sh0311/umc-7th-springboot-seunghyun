package umc.spring.mapping.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.NotFoundHandler;
import umc.spring.mapping.domain.UserMission;
import umc.spring.mapping.repository.UserMissionRepository;
import umc.spring.mission.domain.Mission;
import umc.spring.mission.repository.MissionRepository;
import umc.spring.user.domain.User;
import umc.spring.user.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly=true)
public class UserMissionService {

    private final UserRepository userRepository;
    private final UserMissionRepository userMissionRepository;
    private final MissionRepository missionRepository;

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

    @Transactional
    public void startChallenge(Long missionId, Long userId) {
        UserMission userMission=userMissionRepository.findByMission_IdAndUser_Id(missionId, userId).orElseThrow(() -> new NotFoundHandler(ErrorStatus.USER_MISSION_NOT_FOUND));
        userMission.updateIsChallenging();
    }

    //이미 유저가 해당 미션진행중이지 않은지 확인 (userMission이 존재하는지
    public boolean isValid(Long missionId, Long userId) {
        UserMission userMission=userMissionRepository.findByMission_IdAndUser_Id(missionId, userId).orElseThrow(() -> new NotFoundHandler(ErrorStatus.USER_MISSION_NOT_FOUND));
        if(userMission.isChallenging()) {
            return false;
        }
        return true;
    }
}
