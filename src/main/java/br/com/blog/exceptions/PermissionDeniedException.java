package br.com.blog.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class PermissionDeniedException extends RuntimeException {

  public static final long serialVersionUID = 1L;

  public PermissionDeniedException(String message) {
    super(message);
  }
}
