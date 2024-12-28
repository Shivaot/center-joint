package com.hitachi.epdi2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Shiva Created on 08/07/23
 */
@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
public class CheckSheetAlreadyCreatedException extends RuntimeException{

    String message;

    public CheckSheetAlreadyCreatedException(){
    }

    public CheckSheetAlreadyCreatedException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

