package src.db.helper;

import src.db.ConexaoBD;
import src.models.Projeto;
import src.models.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ProjetoHelper {

    public static ArrayList<Projeto> listar() {
        ArrayList<Projeto> projetos = new ArrayList<>();

        try (
                PreparedStatement ps = ConexaoBD.getConnection().prepareStatement("SELECT * FROM Projeto, Usuario WHERE Usuario.idUsuario = Projeto.idUsuario;");
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int idUsuario = rs.getInt("idUsuario");
                int idProjeto = rs.getInt("idProjeto");
                String nomeProjeto = rs.getString("nome_do_projeto");
                String descricao = rs.getString("descricao");
                String nomeDeUsuario = rs.getString("nome_de_usuario");
                String nomeCompleto = rs.getString("nome_completo");
                String email = rs.getString("email");
                String senha = rs.getString("senha");
                String telefone = rs.getString("telefone");

                Usuario usuario = new Usuario(idUsuario, nomeCompleto, nomeDeUsuario, email, senha, telefone);

                Projeto projeto = new Projeto(idProjeto, nomeProjeto, descricao, usuario);
                projetos.add(projeto);
            }
            return projetos;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public static void apagar(int idProjeto) {
        String sql = "DELETE FROM Projeto WHERE idProjeto = ?";

        try (Connection c = ConexaoBD.getConnection()) {
            PreparedStatement ps = c.prepareStatement(sql);

            ps.setInt(1, idProjeto);

            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void atualizar(Projeto projeto) {
        String sql = "UPDATE Projeto SET nome_do_projeto = ?, descricao = ?, idUsuario = ?";

        try (Connection c = ConexaoBD.getConnection()) {
            PreparedStatement ps = c.prepareStatement(sql);

            ps.setString(1, projeto.getNome());
            ps.setString(2, projeto.getDescricao());
            ps.setInt(3, projeto.getProprietario().getId());

            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void adicionar(String nomeDoProjeto, String descricaoDoProjeto, int idUsuario) {
        String sql = "INSERT INTO Projeto(nome_do_projeto, descricao, idUsuario) VALUES (?, ?, ?)";

        try (Connection c = ConexaoBD.getConnection()) {
            PreparedStatement ps = c.prepareStatement(sql);

            ps.setString(1, nomeDoProjeto);
            ps.setString(2, descricaoDoProjeto);
            ps.setInt(3, idUsuario);

            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
