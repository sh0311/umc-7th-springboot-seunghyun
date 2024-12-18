package umc.spring.apiPayload.code.status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.spring.apiPayload.code.BaseErrorCode;
import umc.spring.apiPayload.code.ErrorReasonDto;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {

    // 가장 일반적인 응답
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST,"COMMON400","잘못된 요청입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED,"COMMON401","인증이 필요합니다."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),


    // 멤버 관려 에러
    MEMBER_NOT_FOUND(HttpStatus.BAD_REQUEST, "MEMBER4001", "사용자가 없습니다."),
    NICKNAME_NOT_EXIST(HttpStatus.BAD_REQUEST, "MEMBER4002", "닉네임은 필수 입니다."),

    // 푸드카테고리 에러
    FOOD_CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND, "Category4001", "해당 음식 카테고리는 존재하지 않습니다"),

    // 유저 에러
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "User4001", "해당 id의 유저가 존재하지 않습니다"),

    // 식당 에러
    RESTAURANT_NOT_FOUND(HttpStatus.NOT_FOUND, "Restaurant4001", "해당 id의 식당이 존재하지 않습니다"),

    // 지역 에러
    REGION_NOT_FOUND(HttpStatus.NOT_FOUND, "Region4001", "해당 id의 지역이 존재하지 않습니다."),

    // 유저미션 에러
    USER_MISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "UserMission4001", "해당 id의 유저미션이 존재하지 않습니다."),
    IS_CHALLENGING_MISSION(HttpStatus.BAD_REQUEST, "UserMission4002", "해당 미션은 이미 도전중입니다."),

    //페이지 에러
    PAGE_BAD_REQUEST(HttpStatus.BAD_REQUEST, "Page4001", "페이지값이 너무 작습니다. 페이지가 1이상이어야 조회 가능합니다."),

    // For test
    TEMP_EXCEPTION(HttpStatus.BAD_REQUEST, "TEMP4001", "이거는 테스트");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ErrorReasonDto getReason(){
        return ErrorReasonDto.builder()
                .isSuccess(false)
                .message(message)
                .code(code)
                .build();
    }

    @Override
    public ErrorReasonDto getReasonHttpStatus(){
        return ErrorReasonDto.builder()
                .isSuccess(false)
                .message(message)
                .code(code)
                .httpStatus(httpStatus)
                .build();
    }

}
