package umc.spring.reataurant.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.reataurant.domain.Restaurant;
import umc.spring.reataurant.repository.RestaurantRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RestaurantQueryServiceImpl implements RestaurantQueryService {
    private final RestaurantRepository restaurantRepository;

    @Override
    public Optional<Restaurant> findRestaurant(Long id){
        return restaurantRepository.findById(id);
    }

    @Override
    public List<Restaurant> findRestaurantsByNameAndScore(String name, Float totalScore){
        List<Restaurant> filtered=restaurantRepository.dynamicQueryWithBooleanBuilder(name, totalScore);

        filtered.forEach(restaurant-> System.out.println("Restaurant: " + restaurant.toString()));

        return filtered;
    }

}
