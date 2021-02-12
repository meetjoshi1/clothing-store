package com.galvanize.clothingstore.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ShirtSizeException extends RuntimeException{

    public ShirtSizeException(String message){
        super(message);
    }
}
