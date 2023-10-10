package ru.ama.inwreaclaste.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

public enum AppExceptionCode {

    USER_LOGIN_ERROR( HttpStatus.OK, "Invalid username or password" ),
    ALREADY_TAKEN_REGISTRATION_ERROR( HttpStatus.OK, "%s is already taken" ),

    UNSPECIFIED_ERROR( HttpStatus.OK, "Unspecified error" );

    private final HttpStatusCode httpCode;
    private final String text;

    AppExceptionCode( HttpStatusCode httpCode, String text ) {
        this.httpCode = httpCode;
        this.text = text;
    }

    public HttpStatusCode getHttpCode() {
        return httpCode;
    }

    public String getText() {
        return text;
    }
}
