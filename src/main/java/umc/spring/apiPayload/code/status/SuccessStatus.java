package umc.spring.apiPayload.code.status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.spring.apiPayload.code.BaseCode;
import umc.spring.apiPayload.code.ReasonDto;

@Getter
@AllArgsConstructor
public enum SuccessStatus implements BaseCode { //성공 응답을 나타냄

    //일반적인 응답
    _OK(HttpStatus.OK, "COMMON200", "성공입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;

    @Override
    public ReasonDto getReason(){
        return ReasonDto.builder()
                .isSuccess(true)
                .message(message)
                .code(code)
                .build();
    }

    @Override
    public ReasonDto getReasonHttpStatus(){
        return ReasonDto.builder()
                .isSuccess(true)
                .message(message)
                .code(code)
                .httpStatus(status)
                .build();
    }


}
