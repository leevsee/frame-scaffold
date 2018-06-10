package com.leeves.app.exception;

import com.leeves.base.R;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Description: Controller异常处理
 * Package: com.leeves.app.exception
 *
 * @author Leeves
 * @date 2018-05-09
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    /**
     * 对于Controller产生的BizException异常进行处理
     */
    @ExceptionHandler(BizException.class)
    @ResponseBody
    public R defualErrorHandler(BizException e){
        return R.resultError(e.getMessage());
    }

}
