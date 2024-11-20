package umc.spring.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.validation.annotation.CheckPage;
import umc.spring.validation.annotation.ExistRestaurant;
import umc.spring.validation.annotation.IsChallenging;

@Component
public class PageValidator implements ConstraintValidator<CheckPage, Pageable> {

  @Override
  public void initialize(CheckPage constraintAnnotation) {
    ConstraintValidator.super.initialize(constraintAnnotation);
  }

  @Override
  public boolean isValid(Pageable pageable, ConstraintValidatorContext context) {
    System.out.println(pageable.getPageNumber());
    if(pageable.getPageNumber()<1){
      context.disableDefaultConstraintViolation();
      context.buildConstraintViolationWithTemplate(ErrorStatus.PAGE_BAD_REQUEST.toString()).addConstraintViolation();
      return false;
    }
    return true;
  }
}
