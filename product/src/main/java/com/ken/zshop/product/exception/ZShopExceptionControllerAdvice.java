package com.ken.zshop.product.exception;

import com.ken.zshop.common.exception.BizCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import com.ken.zshop.common.utils.R;

import java.util.HashMap;
import java.util.Map;

/**
 * 统一异常处理
 */

@Slf4j
@RestControllerAdvice(basePackages = "com.kkb.cubemall.cubegoods.controller")
public class ZShopExceptionControllerAdvice {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public R handlerVaildException(MethodArgumentNotValidException e){
        log.error("数据校验失败{},异常类型{}",e.getMessage(),e.getClass());
        BindingResult bindingResult = e.getBindingResult();
        Map<String,String> map = new HashMap<>();
        bindingResult.getFieldErrors().forEach((item)->{
            String field = item.getField();
            String message = item.getDefaultMessage();
            map.put(field,message);
        });
        return R.error(BizCodeEnum.VAILD_EXCEPTION.getCode(),BizCodeEnum.VAILD_EXCEPTION.getMessage()).put("data",map);
    }

    @ExceptionHandler(value = Throwable.class)
    public R handlerException(Throwable throwable){
        throwable.printStackTrace();;
        return R.error(BizCodeEnum.UNKNONW_EXCEPTION.getCode(),BizCodeEnum.UNKNONW_EXCEPTION.getMessage());
    }

    @ExceptionHandler(value = RemoteServiceCallExeption.class)
    public R handlerException(RemoteServiceCallExeption exeption){
        exeption.printStackTrace();;
        return R.error(BizCodeEnum.REMOTESERVICE_EXCEPTION.getCode(),exeption.getMessage());
    }
}
