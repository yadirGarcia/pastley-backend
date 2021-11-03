package com.pastley.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class GenericException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	private HttpStatus httpStatus;

    public GenericException(HttpStatus httpStatus, String mensaje) {
        super(mensaje);
        this.httpStatus = httpStatus;
    }
}
