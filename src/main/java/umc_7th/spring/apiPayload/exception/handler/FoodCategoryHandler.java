package umc_7th.spring.apiPayload.exception.handler;

import umc_7th.spring.apiPayload.code.BaseErrorCode;
import umc_7th.spring.apiPayload.exception.GeneralException;

public class FoodCategoryHandler extends GeneralException {
    public FoodCategoryHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
