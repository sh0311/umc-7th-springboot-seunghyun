package umc.spring.restaurant.service;

import org.springframework.data.domain.Pageable;
import umc.spring.restaurant.domain.Restaurant;
import umc.spring.review.dto.ReviewPreViewListDTO;

import java.util.List;
import java.util.Optional;

public interface RestaurantService {
    Optional<Restaurant> findRestaurant(Long id);
    List<Restaurant> findRestaurantsByNameAndScore(String name, Float totalScore);
}
