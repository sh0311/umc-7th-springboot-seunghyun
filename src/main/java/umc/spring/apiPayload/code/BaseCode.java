package umc.spring.apiPayload.code;

public interface BaseCode { //구체화 하는 Status에서 BaseCode를 반드시 Override 할 것을 강제하는 역할
    ReasonDto getReason();
    ReasonDto getReasonHttpStatus();
}
