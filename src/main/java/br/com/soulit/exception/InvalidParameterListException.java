package br.com.soulit.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Invalid parameter list.")
public class InvalidParameterListException extends Exception
{

    private static final long serialVersionUID = -5939083542437788173L;

    public InvalidParameterListException(String message)
    {
        super(message);
    }
}
