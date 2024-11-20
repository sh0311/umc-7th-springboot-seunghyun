package umc.spring.mapping.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.mapping.domain.UserMission;

import java.util.Optional;

public interface UserMissionRepository extends JpaRepository<UserMission, Long> {
    Optional<UserMission> findByMission_IdAndUser_Id(Long missionId, Long userId);

    Slice<UserMission> findByUser_IdAndIsCompleteAndIdLessThan(Long userId, boolean isComplete, Long userMissionId, Pageable pageable);

}
