package br.com.blog.mapper.curtom;

public class UsuarioDO {
    private Long id;
    private String nome;
    private String corfoto;

    public String getCorFoto() {
        return corfoto;
    }

    public void setCorFoto(String corfoto) {
        this.corfoto = corfoto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
