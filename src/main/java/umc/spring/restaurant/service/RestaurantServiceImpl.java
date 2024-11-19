package umc.spring.restaurant.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.BadRequestHandler;
import umc.spring.restaurant.domain.Restaurant;
import umc.spring.restaurant.repository.RestaurantRepository;
import umc.spring.review.domain.Review;
import umc.spring.review.dto.ReviewPreViewListDTO;
import umc.spring.review.repository.ReviewRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final ReviewRepository reviewRepository;

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

    public boolean existsById(Long restaurantId){
        Optional<Restaurant> restaurant=restaurantRepository.findById(restaurantId);
        return restaurant.isPresent();
    }


    public ReviewPreViewListDTO getReviewList(Long restaurantId, Pageable pageable) {
        int page=pageable.getPageNumber();
        int size=pageable.getPageSize();
        Sort sort=pageable.getSort();

        if(page<1){
            throw new BadRequestHandler(ErrorStatus.PAGE_BAD_REQUEST);
        }

        Pageable pageRequest = PageRequest.of(page, size, sort);
        Page<Review> reviews=reviewRepository.findByRestaurant_Id(restaurantId, pageRequest);

        ReviewPreViewListDTO dtos=ReviewPreViewListDTO.from(reviews);

        return dtos;
    }
}
