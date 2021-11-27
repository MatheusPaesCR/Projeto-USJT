package src.db.helper;

import src.db.ConexaoBD;
import src.main.LoginInfo;
import src.models.Requisito;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

public class RequisitoHelper {

    public static ArrayList<Requisito> listar(int idProjeto) {
        ArrayList<Requisito> requisitos = new ArrayList<>();
        String sql = "SELECT * FROM REQUISITO WHERE idProjeto = ? ";

        try (Connection c = ConexaoBD.getConnection()) {
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, idProjeto);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int idRequisito = rs.getInt("idRequisito");
                String nome = rs.getString("nome");
                int modulo = rs.getInt("modulo");
                String funcionalidades = rs.getString("funcionalidades");
                Date data_de_criacao = rs.getDate("data_de_criacao");
                Date data_da_ultima_alteracao = rs.getDate("data_da_ultima_alteracao");
                int versao = rs.getInt("versao");
                Requisito.Prioridade prioridade = Requisito.Prioridade.valueOf(rs.getString("prioridade"));
                Requisito.Complexidade complexidade = Requisito.Complexidade.valueOf(rs.getString("complexidade"));
                int esforco_horas = rs.getInt("esforco_horas");
                Requisito.Estado estado = Requisito.Estado.valueOf(rs.getString("estado"));
                Requisito.Fase fase = Requisito.Fase.valueOf(rs.getString("fase"));
                String descricao = rs.getString("descricao");
                String autor = rs.getString("autor");
                String autorUltimaModificacao = rs.getString("autorUltimaModificacao");

                requisitos.add(new Requisito(idRequisito, nome, modulo, funcionalidades, data_de_criacao, data_da_ultima_alteracao, versao, prioridade, complexidade, esforco_horas, estado, fase, descricao, idProjeto, autor, autorUltimaModificacao));
            }
            return requisitos;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ArrayList<Requisito> listar() {
        ArrayList<Requisito> requisitos = new ArrayList<>();
        String sql = "SELECT * FROM REQUISITO ";

        try (Connection c = ConexaoBD.getConnection()) {
            PreparedStatement ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int idRequisito = rs.getInt("idRequisito");
                String nome = rs.getString("nome");
                int modulo = rs.getInt("modulo");
                String funcionalidades = rs.getString("funcionalidades");
                Date data_de_criacao = rs.getDate("data_de_criacao");
                Date data_da_ultima_alteracao = rs.getDate("data_da_ultima_alteracao");
                int versao = rs.getInt("versao");
                int idProjeto = rs.getInt("idProjeto");
                Requisito.Prioridade prioridade = Requisito.Prioridade.valueOf(rs.getString("prioridade"));
                Requisito.Complexidade complexidade = Requisito.Complexidade.valueOf(rs.getString("complexidade"));
                int esforco_horas = rs.getInt("esforco_horas");
                Requisito.Estado estado = Requisito.Estado.valueOf(rs.getString("estado"));
                Requisito.Fase fase = Requisito.Fase.valueOf(rs.getString("fase"));
                String descricao = rs.getString("descricao");
                String autor = rs.getString("autor");
                String autorUltimaModificacao = rs.getString("autorUltimaModificacao");

                requisitos.add(new Requisito(idRequisito, nome, modulo, funcionalidades, data_de_criacao, data_da_ultima_alteracao, versao, prioridade, complexidade, esforco_horas, estado, fase, descricao, idProjeto, autor, autorUltimaModificacao));
            }
            return requisitos;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Requisito pegar(int idRequisito) {
        String sql = "SELECT * FROM REQUISITO WHERE idRequisito = ? ";

        try (Connection c = ConexaoBD.getConnection()) {
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, idRequisito);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String nome = rs.getString("nome");
                int modulo = rs.getInt("modulo");
                String funcionalidades = rs.getString("funcionalidades");
                Date data_de_criacao = rs.getDate("data_de_criacao");
                Date data_da_ultima_alteracao = rs.getDate("data_da_ultima_alteracao");
                int versao = rs.getInt("versao");
                Requisito.Prioridade prioridade = Requisito.Prioridade.valueOf(rs.getString("prioridade"));
                Requisito.Complexidade complexidade = Requisito.Complexidade.valueOf(rs.getString("complexidade"));
                int esforco_horas = rs.getInt("esforco_horas");
                Requisito.Estado estado = Requisito.Estado.valueOf(rs.getString("estado"));
                Requisito.Fase fase = Requisito.Fase.valueOf(rs.getString("fase"));
                String descricao = rs.getString("descricao");
                int idProjeto = rs.getInt("idProjeto");
                String autor = rs.getString("autor");
                String autorUltimaModificacao = rs.getString("autorUltimaModificacao");

                return new Requisito(idRequisito, nome, modulo, funcionalidades, data_de_criacao, data_da_ultima_alteracao, versao, prioridade, complexidade, esforco_horas, estado, fase, descricao, idProjeto, autor, autorUltimaModificacao);
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void adicionar(String nome, int modulo, String funcionalidade, Requisito.Prioridade prioridade, Requisito.Complexidade complexidade, int esforcoHoras, Requisito.Estado estado, Requisito.Fase fase, String descricao, int idProjeto) {
        String sql = "INSERT INTO Requisito(nome, modulo, funcionalidades, data_de_criacao, data_da_ultima_alteracao, versao, prioridade, complexidade, esforco_horas, estado, fase, descricao, idProjeto, autor, autorUltimaModificacao) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        java.sql.Date dtSql = new java.sql.Date(System.currentTimeMillis());
        String autor = LoginInfo.getInstancia().usuarioLogado.getNomeDeUsuario();

        try (Connection c = ConexaoBD.getConnection()) {
            PreparedStatement ps = c.prepareStatement(sql);

            ps.setString(1, nome);
            ps.setInt(2, modulo);
            ps.setString(3, funcionalidade);
            ps.setDate(4, dtSql);
            ps.setDate(5, dtSql);
            ps.setInt(6, 1);
            ps.setString(7, prioridade.toString());
            ps.setString(8, complexidade.toString());
            ps.setInt(9, esforcoHoras);
            ps.setString(10, estado.toString());
            ps.setString(11, fase.toString());
            ps.setString(12, descricao);
            ps.setInt(13, idProjeto);
            ps.setString(14, autor);
            ps.setString(15, autor);

            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Requisito atualizar(Requisito requisito) {
        String sql = "UPDATE Requisito SET nome = ?, modulo = ?, funcionalidades = ?, data_da_ultima_alteracao = ?, versao = ?, prioridade = ?, complexidade = ?, esforco_horas = ?, estado = ?, fase = ?, descricao = ?, autorUltimaModificacao = ? WHERE idRequisito = ?";
        java.sql.Date dtSql = new java.sql.Date(System.currentTimeMillis());
        String autor = LoginInfo.getInstancia().usuarioLogado.getNomeDeUsuario();

        int novaVersao = requisito.getVersao() + 1;
        requisito.setVersao(novaVersao);
        requisito.setAutorUltimaAlteracao(autor);
        requisito.setDataUltimaAlteracao(dtSql);

        try (Connection c = ConexaoBD.getConnection()) {
            PreparedStatement ps = c.prepareStatement(sql);

            ps.setString(1, requisito.getNome());
            ps.setInt(2, requisito.getModulo());
            ps.setString(3, requisito.getFuncionalidades());
            ps.setDate(4, dtSql);
            ps.setInt(5, requisito.getVersao());
            ps.setString(6, requisito.getPrioridade().toString());
            ps.setString(7, requisito.getComplexidade().toString());
            ps.setInt(8, requisito.getEsforcoHoras());
            ps.setString(9, requisito.getEstado().toString());
            ps.setString(10, requisito.getFase().toString());
            ps.setString(11, requisito.getDescricao());
            ps.setString(12, autor);
            ps.setInt(13, requisito.getId());

            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return requisito;
    }

    public static void apagar(int idRequisito) {
        String sql = "DELETE FROM Requisito WHERE idRequisito = ?";

        try (Connection c = ConexaoBD.getConnection()) {
            PreparedStatement ps = c.prepareStatement(sql);

            ps.setInt(1, idRequisito);

            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
