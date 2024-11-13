package umc.spring.mapping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.mapping.domain.UserMission;

public interface UserMissionRepository extends JpaRepository<UserMission, Long> {
}
