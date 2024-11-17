package umc.spring.restaurant.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.restaurant.domain.Restaurant;
import umc.spring.restaurant.repository.RestaurantRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RestaurantServiceImpl implements RestaurantService {
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
