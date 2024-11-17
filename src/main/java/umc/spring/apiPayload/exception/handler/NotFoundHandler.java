package umc.spring.apiPayload.exception.handler;

import umc.spring.apiPayload.code.BaseErrorCode;
import umc.spring.apiPayload.exception.GeneralException;

public class NotFoundHandler extends GeneralException {
    public NotFoundHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
