package umc.spring.reataurant.repository;

import umc.spring.reataurant.domain.Restaurant;

import java.util.List;

public interface RestaurantRepositoryCustom {
    List<Restaurant> dynamicQueryWithBooleanBuilder(String name, Float totalScore);
}
