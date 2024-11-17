package umc.spring.mapping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.mapping.domain.UserPrefer;

public interface UserPreferRepository extends JpaRepository<UserPrefer, Long> {
}
