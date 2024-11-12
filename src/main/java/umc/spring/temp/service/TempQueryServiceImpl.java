package umc.spring.temp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.TempHandler;

@Service
@RequiredArgsConstructor
public class TempQueryServiceImpl implements TempQueryService {
    @Override
    public void CheckFlag(Integer flag){
        if(flag==1)
            throw new TempHandler(ErrorStatus.TEMP_EXCEPTION);
    }
}
