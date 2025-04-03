package br.com.blog.mapper.curtom;

import java.time.LocalDateTime;

public class PostDO {

    private Long id;
    private String titulo;
    private String texto;
    private LocalDateTime dataehora;
    private UsuarioDO usuario; 

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public LocalDateTime getDataehora() {
        return dataehora;
    }

    public void setDataehora(LocalDateTime dataehora) {
        this.dataehora = dataehora;
    }

    public UsuarioDO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDO usuario) {
        this.usuario = usuario;
    }
}
