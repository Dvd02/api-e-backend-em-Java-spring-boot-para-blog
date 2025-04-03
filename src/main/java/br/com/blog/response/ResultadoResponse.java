package br.com.blog.response;

public class ResultadoResponse {
  private Boolean isError;
  private String message;
  
  public ResultadoResponse(String message, Boolean isError) {
    this.message = message;
    this.isError = isError;
  }

  public Boolean getIsError() {
    return isError;
  }

  public String getMessage() {
    return message;
  }
}
