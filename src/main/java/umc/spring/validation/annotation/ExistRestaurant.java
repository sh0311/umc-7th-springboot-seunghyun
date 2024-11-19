package umc.spring.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import umc.spring.validation.validator.CategoriesExistValidator;
import umc.spring.validation.validator.RestaurantExistValidator;

import java.lang.annotation.*;

@Documented //사용자 정의 어노테이션을 만들 때 붙인다
@Constraint(validatedBy = RestaurantExistValidator.class) // RestaurantExistValidator라는 클래스를 통해 @ExistCategories가 붙은 대상을 검증
@Target( {ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER }) //어노테이션의 적용 범위를 지정
@Retention(RetentionPolicy.RUNTIME) //어노테이션의 생명 주기를 지정 (여기서는 RUNTIME이기에 실행 하는 동안에만 유효하게 됨)
public @interface ExistRestaurant {

    String message() default "페이지는 1이상 가능합니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
