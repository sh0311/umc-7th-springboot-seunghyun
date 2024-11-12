package umc.spring.apiPayload.code;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@Builder
public class ReasonDto { //응답 해줌
    private HttpStatus httpStatus;

    private final boolean isSuccess;
    private final String code;
    private final String message;

    private boolean getIsSuccess(){
        return isSuccess;
    }
}