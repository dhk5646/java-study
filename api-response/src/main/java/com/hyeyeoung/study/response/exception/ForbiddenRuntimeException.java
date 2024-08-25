package com.hyeyeoung.study.response.exception;

import com.hyeyeoung.study.response.enums.ApiResponseEnumInterface;
import com.hyeyeoung.study.response.enums.HttpStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class ForbiddenRuntimeException extends ApiResponseRuntimeException {

    public ForbiddenRuntimeException(ApiResponseEnumInterface apiResponseEnum) {
        super(HttpStatusEnum.FORBIDDEN, apiResponseEnum);
    }

    public ForbiddenRuntimeException(ApiResponseEnumInterface apiResponseEnum, Object data) {
        super(HttpStatusEnum.FORBIDDEN, apiResponseEnum, data);
    }
}
