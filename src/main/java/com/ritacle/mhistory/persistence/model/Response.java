package com.ritacle.mhistory.persistence.model;

import java.util.List;

public class Response<T> {

    private T object;
    private List<InputError> errors;

    public Response(T object, List<InputError> errors) {
        this.object = object;
        this.errors = errors;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }

    public List<InputError> getErrors() {
        return errors;
    }

    public void setErrors(List<InputError> errors) {
        this.errors = errors;
    }

    public boolean isOkay() {
        return errors.size() == 0;
    }
}
