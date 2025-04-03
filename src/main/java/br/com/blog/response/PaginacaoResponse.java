package br.com.blog.response;

import java.util.List;

public class PaginacaoResponse<T> {
    private Boolean isError;
    private List<T> conteudo;
    private Boolean temProximaPagina;
    private Integer numeroDaPagina;
    private Integer tamanhoDaPagina;

    public PaginacaoResponse() {}

    public PaginacaoResponse(Boolean isError, List<T> conteudo, Boolean temProximaPagina, Integer numeroDaPagina, Integer tamanhoDaPagina) {
        this.isError = isError;
        this.conteudo = conteudo;
        this.temProximaPagina = temProximaPagina;
        this.numeroDaPagina = numeroDaPagina;
        this.tamanhoDaPagina = tamanhoDaPagina;
    }

    public Boolean getIsError() {
        return isError;
    }

    public void setIsError(Boolean isError) {
        this.isError = isError;
    }

    public List<T> getConteudo() {
        return conteudo;
    }

    public void setConteudo(List<T> conteudo) {
        this.conteudo = conteudo;
    }

    public Boolean getTemProximaPagina() {
        return temProximaPagina;
    }

    public void setTemProximaPagina(Boolean temProximaPagina) {
        this.temProximaPagina = temProximaPagina;
    }

    public Integer getNumeroDaPagina() {
        return numeroDaPagina;
    }

    public void setNumeroDaPagina(Integer numeroDaPagina) {
        this.numeroDaPagina = numeroDaPagina;
    }

    public Integer getTamanhoDaPagina() {
        return tamanhoDaPagina;
    }

    public void setTamanhoDaPagina(Integer tamanhoDaPagina) {
        this.tamanhoDaPagina = tamanhoDaPagina;
    }
}