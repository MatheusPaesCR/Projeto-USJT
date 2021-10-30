package src.db.helper;

import src.db.ConexaoBD;
import src.models.Projeto;
import src.models.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ProjetoHelper {
    //Mostrar tudo---Pegando do banco de dados
    public static ArrayList<Projeto> listar() {
        ArrayList<Projeto> projetos = new ArrayList<>();
        //try with resources
        try (
                PreparedStatement ps = ConexaoBD.getConnection().prepareStatement("SELECT * FROM Projeto, Usuario WHERE Usuario.nome_de_usuario = usuario_proprietario;");
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {

                int registro = rs.getInt("registro_do_projeto");
                String nomeProjeto = rs.getString("nome_do_projeto");
                String descricao = rs.getString("descricao");
                String nomeProprietario = rs.getString("usuario_proprietario");
                String nomeCompleto = rs.getString("nome_completo");
                String email = rs.getString("email");
                String senha = rs.getString("senha");
                String telefone = rs.getString("telefone");
                Usuario usuario = new Usuario(nomeCompleto, nomeProprietario, email, senha, telefone);

                Projeto projeto = new Projeto(registro, nomeProjeto, descricao, usuario);
                projetos.add(projeto);
            }
            return projetos;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
    //Excluir

    public static void apagar(int registro) {
        //1: Definir o comando SQL
        String sql = "DELETE FROM Projeto WHERE registro_do_projeto= ?";
        //2: Abrir uma conexão

        try (Connection c = ConexaoBD.getConnection()) {
            //3: Pré compila o comando
            PreparedStatement ps = c.prepareStatement(sql);
            //4: Preenche os dados faltantes
            ps.setInt(1, registro);
            //5: Executa o comando
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //Atualizar
    public static void atualizar(Projeto projeto) {
        //1: Definir o comando SQL
        String sql = "UPDATE Projeto SET registro_do_projeto = ?, nome_do_projeto = ?, descricao = ?, usuario_proprietario = ?";
        try (Connection c = ConexaoBD.getConnection()) {
            //3: Pré compila o comando
            PreparedStatement ps = c.prepareStatement(sql);
            //4: Preenche os dados faltantes
            ps.setInt(1, projeto.getRegistro());
            ps.setString(2, projeto.getNome());
            ps.setString(3, projeto.getDescricao());
            ps.setObject(4, projeto.getProprietario());
            //5: Executa o comando
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    ///Adicionar usuario ao banco de dados:
    public static void adicionar(Projeto projeto) {
        //1: Definir o comando SQL
        String sql = "INSERT INTO Projeto(registro_do_projeto, nome_do_projeto, descricao, usuario_proprietario) VALUES (?, ?, ?, ?)";
        try (Connection c = ConexaoBD.getConnection()) {
            //3: Pré compila o comando
            PreparedStatement ps = c.prepareStatement(sql);
            //4: Preenche os dados faltantes
            ps.setInt(1, projeto.getRegistro());
            ps.setString(2, projeto.getNome());
            ps.setString(3, projeto.getDescricao());
            ps.setObject(4, projeto.getProprietario());
            //5: Executa o comando
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
