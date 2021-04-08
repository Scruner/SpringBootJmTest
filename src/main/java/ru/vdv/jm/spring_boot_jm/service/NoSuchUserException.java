package ru.vdv.jm.spring_boot_jm.service;

public class NoSuchUserException extends RuntimeException {

    public NoSuchUserException(String message) {
        super(message);
    }
}
