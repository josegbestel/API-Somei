package com.somei.apisomei.exception;

public class NotFoundException extends DomainException {

    private static final long serialVersionUID = 1L;

    public NotFoundException(String message) {
        super(message);
    }
}
