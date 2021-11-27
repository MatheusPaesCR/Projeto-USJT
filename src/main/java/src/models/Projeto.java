package src.models;

public class Projeto {
    private int registro;
    private String nome;
    private String descricao;
    private Usuario proprietario;

    public Projeto(String nome, String descricao, Usuario proprietario) {
        this.nome = nome;
        this.descricao = descricao;
        this.proprietario = proprietario;
    }

    public Projeto(int registro, String nome, String descricao, Usuario proprietario) {
        this.registro = registro;
        this.nome = nome;
        this.descricao = descricao;
        this.proprietario = proprietario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Usuario getProprietario() {
        return proprietario;
    }

    public void setProprietario(Usuario proprietario) {
        this.proprietario = proprietario;
    }

    public int getRegistro() {
        return registro;
    }

    public void setRegistro(int registro) {
        this.registro = registro;
    }
}