package umc.spring.reataurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.reataurant.domain.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long>, RestaurantRepositoryCustom {
}
