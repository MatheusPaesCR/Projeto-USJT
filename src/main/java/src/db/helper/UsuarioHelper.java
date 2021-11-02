package src.db.helper;

import src.db.ConexaoBD;
import src.models.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class UsuarioHelper {

    //Mostrar tudo---Pegando do banco de dados
    public static ArrayList<Usuario> listar() {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        //try with resources
        try (
                PreparedStatement ps = ConexaoBD.getConnection().prepareStatement("SELECT * FROM USUARIO;");
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {

                int id = rs.getInt("idUsuario");
                String nome = rs.getString("nome_completo");
                String nomeUser = rs.getString("nome_de_usuario");
                String email = rs.getString("email");
                String senha = rs.getString("senha");
                String telefone = rs.getString("telefone");


                Usuario u = new Usuario(id, nome, nomeUser, email, senha, telefone);
                usuarios.add(u);
            }
            return usuarios;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
    //Excluir

    public static void apagar(String nomeCompleto) {
        //1: Definir o comando SQL
        String sql = "DELETE FROM USUARIO WHERE nome_completo= ?";
        //2: Abrir uma conexão

        try (Connection c = ConexaoBD.getConnection()) {
            //3: Pré compila o comando
            PreparedStatement ps = c.prepareStatement(sql);
            //4: Preenche os dados faltantes
            ps.setString(1, nomeCompleto);
            //5: Executa o comando
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //Atualizar
    public static void atualizar(Usuario usuario) {
        //1: Definir o comando SQL
        String sql = "UPDATE USUARIO SET nome_completo = ?, email = ?, senha = ?, telefone = ? WHERE nome_de_usuario = ?";
        try (Connection c = ConexaoBD.getConnection()) {
            //3: Pré compila o comando
            PreparedStatement ps = c.prepareStatement(sql);
            //4: Preenche os dados faltantes
            ps.setString(1, usuario.getNomeCompleto());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getSenha());
            ps.setString(4, usuario.getTelefone());
            ps.setString(5, usuario.getNomeDeUsuario());
            //5: Executa o comando
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    ///Adicionar usuario ao banco de dados:
    public static void adicionarUsuario(Usuario usuario) {
        //1: Definir o comando SQL
        String sql = "INSERT INTO USUARIO(nome_completo, nome_de_usuario, email, senha, telefone) VALUES (?, ?, ?, ?, ?)";
        try (Connection c = ConexaoBD.getConnection()) {
            //3: Pré compila o comando
            PreparedStatement ps = c.prepareStatement(sql);
            //4: Preenche os dados faltantes
            ps.setString(1, usuario.getNomeCompleto());
            ps.setString(2, usuario.getNomeDeUsuario());
            ps.setString(3, usuario.getEmail());
            ps.setString(4, usuario.getSenha());
            ps.setString(5, usuario.getTelefone());
            //5: Executa o comando
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Usuario pegar(String nomeUsuario) {
        String sql = "SELECT * FROM USUARIO WHERE nome_de_usuario = ? ;";

        try (Connection c = ConexaoBD.getConnection()) {
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, nomeUsuario);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                int id = rs.getInt("idUsuario");
                String nome = rs.getString("nome_completo");
                String nomeUser = rs.getString("nome_de_usuario");
                String email = rs.getString("email");
                String senha = rs.getString("senha");
                String telefone = rs.getString("telefone");

                return new Usuario(id, nome, nomeUser, email, senha, telefone);
            } else return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
