package com.hyeyeoung.study.response.exception;

import com.hyeyeoung.study.response.enums.ApiResponseEnumInterface;
import com.hyeyeoung.study.response.enums.HttpStatusEnum;
import lombok.Getter;

@Getter
public class ServerErrorRuntimeException extends ApiResponseRuntimeException {

    public ServerErrorRuntimeException(ApiResponseEnumInterface apiResponseEnum) {
        super(HttpStatusEnum.INTERNAL_SERVER_ERROR, apiResponseEnum);
    }

    public ServerErrorRuntimeException(ApiResponseEnumInterface apiResponseEnum, Object data) {
        super(HttpStatusEnum.INTERNAL_SERVER_ERROR, apiResponseEnum, data);
    }
}
