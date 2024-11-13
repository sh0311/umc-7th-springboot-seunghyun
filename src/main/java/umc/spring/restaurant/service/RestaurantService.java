package umc.spring.restaurant.service;

import umc.spring.restaurant.domain.Restaurant;

import java.util.List;
import java.util.Optional;

public interface RestaurantService {
    Optional<Restaurant> findRestaurant(Long id);
    List<Restaurant> findRestaurantsByNameAndScore(String name, Float totalScore);
}
