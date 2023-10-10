package ru.ama.inwreaclaste.error;

import ru.ama.inwreaclaste.rest.dto.CustomResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ErrorResolver {

    public static final Logger LOGGER = LoggerFactory.getLogger( ErrorResolver .class );

    protected static final String DEFAULT_ERROR_CODE = AppExceptionCode.UNSPECIFIED_ERROR.name();

    @ExceptionHandler( Exception.class )
    @ResponseBody
    public ResponseEntity<CustomResponse<Object>> handleCustomException( Exception ex ) {
        Error error = resolve( ex );
        LOGGER.error( error.toString() );
        // default value
        return new ResponseEntity<>( new CustomResponse<>( false, null, error ), error.httpStatusCode() );
    }

    public Error resolve( Exception e ) {
        return new Error( calculateStatusCode( e ), calculateCode( e ), e.getMessage(), System.currentTimeMillis() );
    }

    private HttpStatusCode calculateStatusCode( Exception e ) {
        if ( e instanceof AppException )
            return ((AppException) e).getCode().getHttpCode();
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }

    protected String calculateCode( Exception e ) {
        if ( e instanceof AppException )
            return ((AppException) e).getCode().name();
        return DEFAULT_ERROR_CODE;
    }


}
