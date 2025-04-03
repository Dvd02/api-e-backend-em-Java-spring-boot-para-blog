package br.com.blog.requests;

public class UsuarioUpdateRequest {
    private String emailAntigo;
    private String senhaAntiga;
    private String emailNovo;
    private String senhaNova;
    private String nomeNovo;
    private String corFoto;

    public String getEmailAntigo() {
        return emailAntigo;
    }

    public void setEmailAntigo(String emailAntigo) {
        this.emailAntigo = emailAntigo;
    }

    public String getSenhaAntiga() {
        return senhaAntiga;
    }

    public void setSenhaAntiga(String senhaAntiga) {
        this.senhaAntiga = senhaAntiga;
    }

    public String getEmailNovo() {
        return emailNovo;
    }

    public void setEmailNovo(String emailNovo) {
        this.emailNovo = emailNovo;
    }

    public String getSenhaNova() {
        return senhaNova;
    }

    public void setSenhaNova(String senhaNova) {
        this.senhaNova = senhaNova;
    }

    public String getNomeNovo() {
        return nomeNovo;
    }

    public void setNomeNovo(String nomeNovo) {
        this.nomeNovo = nomeNovo;
    }

    public String getCorFoto() {
        return corFoto;
    }

    public void setCorFoto(String corFoto) {
        this.corFoto = corFoto;
    }
}
