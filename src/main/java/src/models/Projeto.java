package src.models;

public class Projeto {
    private int id;
    private String nome;
    private String descricao;
    private Usuario proprietario;


    public Projeto(String nome, String descricao, Usuario proprietario) {
        this.nome = nome;
        this.descricao = descricao;
        this.proprietario = proprietario;
    }

    public Projeto(int registro, String nome, String descricao, Usuario proprietario) {
        this.id = registro;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}