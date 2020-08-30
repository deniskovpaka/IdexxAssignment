package com.idexx.demo.exception;

public class SearchResultException extends RuntimeException {
    public static final String ERR_MESSAGE = "Incorrect search criteria";
    public SearchResultException() {
        super(ERR_MESSAGE);
    }
}
