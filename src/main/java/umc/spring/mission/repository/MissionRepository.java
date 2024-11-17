package umc.spring.mission.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.mission.domain.Mission;

public interface MissionRepository extends JpaRepository<Mission, Long> {
}
