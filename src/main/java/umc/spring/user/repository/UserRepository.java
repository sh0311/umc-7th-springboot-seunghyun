package umc.spring.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.user.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
