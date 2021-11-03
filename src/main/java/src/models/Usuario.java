package src.models;

public class Usuario {   //Variaveis
    private String nomeDeUsuario;
    private String nomeCompleto;
    private String email;
    private String senha;
    private String telefone;

    public Usuario(String nomeCompleto, String nomeDeUsuario, String email, String senha, String tel) {
        this.nomeCompleto = nomeCompleto;
        this.nomeDeUsuario = nomeDeUsuario;
        this.email = email;
        this.senha = senha;
        this.telefone = tel;
    }

    //construtor padrão
    public Usuario() {
    }

    //modificadores
    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public void setNomeDeUsuario(String nomeDeUsuario) {
        this.nomeDeUsuario = nomeDeUsuario;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setTelefone(String tel) {
        this.telefone = tel;
    }


    //Metodos de acesso

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public String getNomeDeUsuario() {
        return nomeDeUsuario;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public String getTelefone() {
        return telefone;
    }
}