package umc.spring.restaurant.repository;

import umc.spring.restaurant.domain.Restaurant;

import java.util.List;

public interface RestaurantRepositoryCustom {
    List<Restaurant> dynamicQueryWithBooleanBuilder(String name, Float totalScore);
}
