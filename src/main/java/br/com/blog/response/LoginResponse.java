package br.com.blog.response;

public class LoginResponse {
    private Long idUsuario;
    private Boolean isError;

    public LoginResponse(Long idUsuario){
        this.idUsuario = idUsuario;
        this.isError = false;
    }

    public Long getIdUsuario(){
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario){
        this.idUsuario = idUsuario;
    }
    public Boolean getIsError(){
        return isError;
    }

    public void setIsError(Boolean isError){
        this.isError = isError;
    }
}
