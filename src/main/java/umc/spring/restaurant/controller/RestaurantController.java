package umc.spring.restaurant.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import umc.spring.restaurant.service.RestaurantServiceImpl;

@RestController
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantServiceImpl restaurantService;
}
