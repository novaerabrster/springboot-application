package br.com.soulit.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Cannot search without a filter")
public class FilterNotSetException extends Exception
{
    private static final long serialVersionUID = 5106099738100274301L;

    public FilterNotSetException(String message)
    {
        super(message);
    }

}
