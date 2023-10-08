package ru.ama.inwreaclaste.error;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AppException extends RuntimeException {

    private static final Pattern PLACEHOLDER_PATTERN = Pattern.compile( "%\\w" );

    private final AppExceptionCode code;

    public AppException( AppExceptionCode code, Object... params ) {
        super( formatMessage( code.getText(), params ) );
        this.code = code;
    }

    public AppException( AppExceptionCode code, Throwable cause ) {
        super(code.getText(), cause );
        this.code = code;
    }

    public AppException( AppExceptionCode code, Throwable cause, Object... params ) {
        super( formatMessage( code.getText(), params ), cause );
        this.code = code;
    }

    public AppExceptionCode getCode() {
        return code;
    }

    public static String formatMessage(String template, Object... parameters ) {
        Matcher matcher = PLACEHOLDER_PATTERN.matcher( template );
        int placeholderCount = 0;
        while ( matcher.find() )
            ++placeholderCount;

        List<Object> usableParameters = new ArrayList<>( placeholderCount );
        for ( int i = 0; i < placeholderCount; ++i )
            usableParameters.add( i < parameters.length ? parameters[i] : null );

        Object[] usableParametersArray = usableParameters.toArray(new Object[0]);
        return String.format( template, usableParametersArray );
    }
}
