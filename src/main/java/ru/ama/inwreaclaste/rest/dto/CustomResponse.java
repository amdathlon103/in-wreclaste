package ru.ama.inwreaclaste.rest.dto;

public class CustomResponse<T> {

    private int status;

    private String[] errors;

    private T body;

    public CustomResponse( T body, String[] errors, int status ) {
        this.status = status;
        this.errors = errors;
        this.body = body;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus( int status ) {
        this.status = status;
    }

    public String[] getErrors() {
        return errors;
    }

    public void setErrors( String[] errors ) {
        this.errors = errors;
    }

    public T getBody() {
        return body;
    }

    public void setBody( T body ) {
        this.body = body;
    }
}
