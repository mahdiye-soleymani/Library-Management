package com.sepehr.librarymanagement.exception;

public class InactiveMemberException extends RuntimeException {
    public InactiveMemberException(String message) {
        super(message);
    }
}
