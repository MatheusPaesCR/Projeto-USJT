package src.db.helper;

import src.db.ConexaoBD;
import src.models.Projeto;
import src.models.Requisito;
import src.models.Usuario;

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
                int idAutor = rs.getInt("idAutor");
                int idAutorUltimaModificacao = rs.getInt("idAutorUltimaModificacao");

                requisitos.add(new Requisito(idRequisito, nome, modulo, funcionalidades, data_de_criacao, data_da_ultima_alteracao, versao, prioridade, complexidade, esforco_horas, estado, fase, descricao, idProjeto, idAutor, idAutorUltimaModificacao));
            }
            return requisitos;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
