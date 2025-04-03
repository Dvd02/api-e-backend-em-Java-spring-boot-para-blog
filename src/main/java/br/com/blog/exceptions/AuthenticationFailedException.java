package br.com.blog.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class AuthenticationFailedException extends RuntimeException {

  public static final long serialVersionUID = 1L;

  public AuthenticationFailedException(String message) {
    super(message);
  }
}
