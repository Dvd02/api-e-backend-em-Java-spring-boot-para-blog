
package br.com.blog.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IllegalArgumentException extends RuntimeException {

    public static final long serialVersionUID = 1L;

    public IllegalArgumentException(String message) {
        super(message);
    }
}
