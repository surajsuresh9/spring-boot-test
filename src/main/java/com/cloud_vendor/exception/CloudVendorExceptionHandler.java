package com.cloud_vendor.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CloudVendorExceptionHandler {

    @ExceptionHandler(CloudVendorNotFoundException.class)
    ResponseEntity<CloudVendorException> handleCloudVendorException(CloudVendorNotFoundException exception) {
        CloudVendorException cloudVendorException = new CloudVendorException(exception.getMessage(), exception.getCause(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(cloudVendorException, HttpStatus.NOT_FOUND);
    }
}
