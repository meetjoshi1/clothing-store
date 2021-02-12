package com.galvanize.clothingstore.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DressShirtException extends RuntimeException{

    public DressShirtException(String message){
        super(message);
    }
}
