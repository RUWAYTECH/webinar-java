package com.ruwaytech.shared.exception;

import lombok.Getter;

@Getter
public class RestaurantException extends RuntimeException{

    private final String message;

    public RestaurantException(String message) {
        this.message = message;
    }

}