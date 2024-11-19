package umc.spring.review.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.review.domain.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    Page<Review> findByRestaurant_Id(Long RestaurantId, Pageable pageable);

    Page<Review> findByUser_Id(Long userId, Pageable pageRequest);
}
