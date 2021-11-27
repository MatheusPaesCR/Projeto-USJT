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

    public Complexidade getComplexidade() {
        return complexidade;
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

    public Fase getFase() {
        return fase;
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

    public void setPrioridade(Prioridade prioridade) {
        this.prioridade = prioridade;
    }

    public void setComplexidade(Complexidade complexidade) {
        this.complexidade = complexidade;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public void setFase(Fase fase) {
        this.fase = fase;
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

    public static Prioridade getPrioridadeByIndex(int index) {
        switch (index) {
            case 4:
                return Prioridade.URGENTE;
            case 3:
                return  Prioridade.ALTA;
            case 2:
                return Prioridade.MEDIA;
            case 1:
                return Prioridade.BAIXA;
            default:
                return Prioridade.SEM_PRIORIDADE;
        }
    }

    public static int getPrioridadeIndex(Prioridade prioridade) {
        switch (prioridade) {
            case URGENTE:
                return 4;
            case ALTA:
                return 3;
            case MEDIA:
                return 2;
            case BAIXA:
                return 1;
            default:
                return 0;
        }
    }

    public static String getPrioridadeText(Prioridade prioridade) {
        switch (prioridade) {
            case URGENTE:
                return "Urgente";
            case ALTA:
                return "Alta";
            case MEDIA:
                return "Media";
            case BAIXA:
                return "Baixa";
            default:
                return "Sem prioridade";
        }
    }

    public static String getPrioridadeTextByIndex(int index) {
        switch (index) {
            case 4:
                return "Urgente";
            case 3:
                return "Alta";
            case 2:
                return "Media";
            case 1:
                return "Baixa";
            default:
                return "Sem prioridade";
        }
    }

    public static String getComplexidadeText(Complexidade complexidade) {
        switch (complexidade) {
            case MUITO_ALTA:
                return "Muito Alta";
            case ALTA:
                return "Alta";
            case MEDIA:
                return "Média";
            case BAIXA:
                return "Baixa";
            default:
                return "Muito Baixa";
        }
    }

    public static String getComplexidadeTextByIndex(int index) {
        switch (index) {
            case 4:
                return "Muito Alta";
            case 3:
                return "Alta";
            case 2:
                return "Média";
            case 1:
                return "Baixa";
            default:
                return "Muito Baixa";
        }
    }

    public static Complexidade getComplexidadeByIndex(int index) {
        switch (index) {
            case 4:
                return Complexidade.MUITO_ALTA;
            case 3:
                return Complexidade.ALTA;
            case 2:
                return Complexidade.MEDIA;
            case 1:
                return Complexidade.BAIXA;
            default:
                return Complexidade.MUITO_BAIXA;
        }
    }

    public static int getComplexidadeIndex(Complexidade complexidade) {
        switch (complexidade) {
            case MUITO_ALTA:
                return 4;
            case ALTA:
                return 3;
            case MEDIA:
                return 2;
            case BAIXA:
                return 1;
            default:
                return 0;
        }
    }

    public static String getEstadoText(Estado estado) {
        switch (estado) {
            case IMPLEMENTADO:
                return "Implementado";
            case EM_DESENVOLVIMENTO:
                return "Em desenvolvimento";
            default:
                return "Não iniciado";
        }
    }

    public static String getEstadoTextByIndex(int index) {
        switch (index) {
            case 2:
                return "Implementado";
            case 1:
                return "Em desenvolvimento";
            default:
                return "Não iniciado";
        }
    }

    public static int getEstadoIndex(Estado estado) {
        switch (estado) {
            case IMPLEMENTADO:
                return 2;
            case EM_DESENVOLVIMENTO:
                return 1;
            default:
                return 0;
        }
    }

    public static Estado getEstadoByIndex(int index) {
        switch (index) {
            case 2:
                return Estado.IMPLEMENTADO;
            case 1:
                return Estado.EM_DESENVOLVIMENTO;
            default:
                return Estado.NAO_INICIADO;
        }
    }

    public static String getFaseText(Fase fase) {
        switch (fase) {
            case CONCLUIDO:
                return "Concluído";
            case DESENVOLVIMENTO:
                return "Desenvolvimento";
            case TESTES:
                return "Testes";
            default:
                return "Análise";
        }
    }

    public static String getFaseTextByIndex(int index) {
        switch (index) {
            case 3:
                return "Concluído";
            case 2:
                return "Desenvolvimento";
            case 1:
                return "Testes";
            default:
                return "Análise";
        }
    }

    public static int getFaseIndex(Fase fase) {
        switch (fase) {
            case CONCLUIDO:
                return 3;
            case DESENVOLVIMENTO:
                return 2;
            case TESTES:
                return 1;
            default:
                return 0;
        }
    }

    public static Fase getFaseByIndex(int index) {
        switch (index) {
            case 3:
                return Fase.CONCLUIDO;
            case 2:
                return Fase.DESENVOLVIMENTO;
            case 1:
                return Fase.TESTES;
            default:
                return Fase.ANALISE;
        }
    }
}
