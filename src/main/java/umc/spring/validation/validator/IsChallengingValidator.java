package umc.spring.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.mapping.service.UserMissionService;
import umc.spring.validation.annotation.IsChallenging;

@Component
@RequiredArgsConstructor
public class IsChallengingValidator implements ConstraintValidator<IsChallenging, Long> {

    private final UserMissionService userMissionService;

    @Override
    public void initialize(IsChallenging constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }



    @Override
    public boolean isValid(Long missionId, ConstraintValidatorContext context) {
        boolean isValid = userMissionService.isValid(missionId, 2L); //나중에 로그인 구현하고나서 userId 가져오는 방식 바꾸기 (SecurityContextHolder에서 가져오게끔)

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.IS_CHALLENGING_MISSION.toString()).addConstraintViolation();
        }

        return isValid;
    }
}
