package src.models;

import java.util.Date;

public class Requisito {
    private int id;
    private String nome;
    private int modulo;
    private String funcionalidades;
    private Date dataCriacao;
    private Date dataUltimaAlteracao;
    private int versao;
    private Prioridade prioridade;
    private Complexidade complexidade;
    private int esforcoHoras;
    private Estado estado;
    private Fase fase;
    private String descricao;
    private int idProjeto;
    private String autor;
    private String autorUltimaAlteracao;

    public Requisito(int id, String nome, int modulo, String funcionalidades, Date dataCriacao, Date dataUltimaAlteracao, int versao, Prioridade prioridade, Complexidade complexidade, int esforcoHoras, Estado estado, Fase fase, String descricao, int idProjeto, String autor, String autorUltimaAlteracao) {
        this.id = id;
        this.nome = nome;
        this.modulo = modulo;
        this.funcionalidades = funcionalidades;
        this.dataCriacao = dataCriacao;
        this.dataUltimaAlteracao = dataUltimaAlteracao;
        this.versao = versao;
        this.prioridade = prioridade;
        this.complexidade = complexidade;
        this.esforcoHoras = esforcoHoras;
        this.estado = estado;
        this.fase = fase;
        this.descricao = descricao;
        this.idProjeto = idProjeto;
        this.autor = autor;
        this.autorUltimaAlteracao = autorUltimaAlteracao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getModulo() {
        return modulo;
    }

    public void setModulo(int modulo) {
        this.modulo = modulo;
    }

    public String getFuncionalidades() {
        return funcionalidades;
    }

    public void setFuncionalidades(String funcionalidades) {
        this.funcionalidades = funcionalidades;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Date getDataUltimaAlteracao() {
        return dataUltimaAlteracao;
    }

    public void setDataUltimaAlteracao(Date dataUltimaAlteracao) {
        this.dataUltimaAlteracao = dataUltimaAlteracao;
    }

    public int getVersao() {
        return versao;
    }

    public void setVersao(int versao) {
        this.versao = versao;
    }

    public Prioridade getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Prioridade prioridade) {
        this.prioridade = prioridade;
    }

    public Complexidade getComplexidade() {
        return complexidade;
    }

    public void setComplexidade(Complexidade complexidade) {
        this.complexidade = complexidade;
    }

    public int getEsforcoHoras() {
        return esforcoHoras;
    }

    public void setEsforcoHoras(int esforcoHoras) {
        this.esforcoHoras = esforcoHoras;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Fase getFase() {
        return fase;
    }

    public void setFase(Fase fase) {
        this.fase = fase;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getIdProjeto() {
        return idProjeto;
    }

    public void setIdProjeto(int idProjeto) {
        this.idProjeto = idProjeto;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getAutorUltimaAlteracao() {
        return autorUltimaAlteracao;
    }

    public void setAutorUltimaAlteracao(String autorUltimaAlteracao) {
        this.autorUltimaAlteracao = autorUltimaAlteracao;
    }

    public enum Prioridade {
        URGENTE,
        ALTA,
        MEDIA,
        BAIXA,
        SEM_PRIORIDADE
    }

    public enum Complexidade {
        MUITO_ALTA,
        ALTA,
        MEDIA,
        BAIXA,
        MUITO_BAIXA
    }

    public enum Estado {
        IMPLEMENTADO,
        EM_DESENVOLVIMENTO,
        NAO_INICIADO
    }

    public enum Fase {
        CONCLUIDO,
        TESTES,
        DESENVOLVIMENTO,
        ANALISE
    }
}
