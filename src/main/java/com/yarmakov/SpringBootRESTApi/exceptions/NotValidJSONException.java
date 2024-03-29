package com.yarmakov.SpringBootRESTApi.exceptions;

public class NotValidJSONException extends Exception {
    public NotValidJSONException(String message) {
        super(message);
    }
}
