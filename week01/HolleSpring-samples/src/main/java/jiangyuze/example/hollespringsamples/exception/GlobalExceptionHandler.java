package jiangyuze.example.hollespringsamples.exception;

import jiangyuze.example.hollespringsamples.VO.ResultVO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResultVO<String> handleIllegalArgumentException(IllegalArgumentException e) {
        return new ResultVO<>(400, "非法参数: " + e.getMessage(), null);
    }

    @ExceptionHandler(IllegalAccessException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResultVO<String> handleIllegalAccessException(IllegalAccessException e) {
        return new ResultVO<>(403, "非法访问: " + e.getMessage(), null);
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultVO<String> handleNullPointerException(NullPointerException e) {
        return new ResultVO<>(500, "系统内部错误: 空指针异常", null);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultVO<String> handleException(Exception e) {
        return new ResultVO<>(500, "系统内部错误: " + e.getMessage(), null);
    }
}
