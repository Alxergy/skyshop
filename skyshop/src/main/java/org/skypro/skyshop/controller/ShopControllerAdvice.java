package org.skypro.skyshop.controller;

import org.skypro.skyshop.model.error.ShopError;
import org.skypro.skyshop.exception.NoSuchProductException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ShopControllerAdvice {
    @ExceptionHandler(NoSuchProductException.class)
    public ResponseEntity<ShopError> handleNoSuchProductException(NoSuchProductException e) {
        return ResponseEntity.status(404).body(new ShopError("PRODUCT_NOT_FOUND", e.getMessage()));
    }
}