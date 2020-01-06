package com.applet.common.excepion;

import com.applet.common.constant.ResultMsg;
import com.applet.common.vo.RestVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 自定义-业务异常
 *
 * @author 谭良忠
 * @date 2020/1/6 14:17
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 业务异常
     *
     * @param ex BusinessException
     * @return RestVo
     */
    @ExceptionHandler(BusinessException.class)
    public RestVo handleBusinessException(BusinessException ex) {
        ResultMsg result = ex.getResult();
        log.info("BusinessException restVo:{}", result);
        return RestVo.FAIL(result.getCode(), ex.getMessage());
    }

}
