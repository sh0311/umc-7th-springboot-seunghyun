package umc.spring.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.restaurant.domain.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long>, RestaurantRepositoryCustom {
}
