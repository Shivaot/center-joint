package com.hitachi.epdi2.exception;


import com.hitachi.epdi2.constant.Constant;
import com.hitachi.epdi2.dto.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Slf4j
@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseDto globalExceptionHandler(Exception e) {
        e.printStackTrace();
        return new ResponseDto(Constant.APIMessage.ERROR_MESSAGE, true);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = UserDefinedException.class)
    public ResponseDto userDefinedException(UserDefinedException e) {
        e.printStackTrace();
        return new ResponseDto(e.getMessage(),new Object());
    }

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ModelAndView resourceNotFoundException(HttpSession session, ResourceNotFoundException e) {
        log.error("Error", e);
        ModelAndView mav = new ModelAndView();
        session.setAttribute("message", new ResponseDto<>(e.getMessage(), "alert-danger"));
        mav.setViewName("error/404");
        return mav;
    }

    @ExceptionHandler(value = CheckSheetAlreadyCreatedException.class)
    public ModelAndView checkSheetAlreadyCreatedException(HttpSession session, ResourceNotFoundException e) {
        log.error("Error", e);
        ModelAndView mav = new ModelAndView();
        session.setAttribute("message", new ResponseDto<>(e.getMessage(), "alert-danger"));
        mav.setViewName("error/405");
        return mav;
    }


}
