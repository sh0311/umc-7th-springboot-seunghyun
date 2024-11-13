package umc.spring.region.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.region.domain.Region;

public interface RegionRepository extends JpaRepository<Region, Long> {
}
