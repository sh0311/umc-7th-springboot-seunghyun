package umc.spring.reataurant.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc.spring.reataurant.domain.QRestaurant;
import umc.spring.reataurant.domain.Restaurant;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class RestaurantRepositoryImpl implements RestaurantRepositoryCustom {
    private final QRestaurant restaurant=QRestaurant.restaurant;
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Restaurant> dynamicQueryWithBooleanBuilder(String name, Float totalScore){
        BooleanBuilder predicate = new BooleanBuilder(); //여러 개의 조건을 조합하기 위해 제공되는 빌더 클래스

        if(name!=null){
            predicate.and(restaurant.name.eq(name));
        }
        if(totalScore!=null){
            predicate.and(restaurant.totalScore.goe(4.0f));
        }

        return jpaQueryFactory
                .selectFrom(restaurant)
                .where(predicate)
                .fetch();
    }
}
