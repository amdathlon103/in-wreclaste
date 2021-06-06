package ru.ama.inwreaclaste.rest.dto;

import org.springframework.lang.Nullable;

public class CustomResponse<T> {

    private int status;

    @Nullable
    private String[] errors;

    @Nullable
    private T body;

    public CustomResponse( @Nullable T body, @Nullable String[] errors, int status ) {
        this.status = status;
        this.errors = errors;
        this.body = body;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Nullable
    public String[] getErrors() {
        return errors;
    }

    public void setErrors(@Nullable String[] errors) {
        this.errors = errors;
    }

    @Nullable
    public T getBody() {
        return body;
    }

    public void setBody(@Nullable T body) {
        this.body = body;
    }
}
