package com.ritacle.mhistory.persistence.model;

import java.util.List;

public class Response<T> {

    private T object;
    private List<String> errorMessages;

    public Response(T object, List<String> errorMessages) {
        this.object = object;
        this.errorMessages = errorMessages;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }

    public List<String> getErrorMessages() {
        return errorMessages;
    }

    public void setErrorMessages(List<String> errorMessages) {
        this.errorMessages = errorMessages;
    }

    public boolean isOkay() {
        return errorMessages.size() == 0;
    }
}
