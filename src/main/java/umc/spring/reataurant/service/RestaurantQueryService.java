package umc.spring.reataurant.service;

import umc.spring.reataurant.domain.Restaurant;

import java.util.List;
import java.util.Optional;

public interface RestaurantQueryService {
    Optional<Restaurant> findRestaurant(Long id);
    List<Restaurant> findRestaurantsByNameAndScore(String name, Float totalScore);
}
