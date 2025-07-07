package com.danidev.MyGuide.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String resourceName, Long id) {
        super(resourceName + " con  ID " + id +  "No fue encontrado");
    }
}
