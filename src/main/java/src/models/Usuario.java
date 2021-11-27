package src.models;

import src.db.ConexaoBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

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

    //Mostrar tudo---Pegando do banco de dados
    public static ArrayList<Usuario> listar() {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        //try with resources
        try (
                PreparedStatement ps = ConexaoBD.getConnection().prepareStatement("SELECT * FROM USUARIO;");
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {

                String nome = rs.getString("nome_completo");
                String nomeUser = rs.getString("nome_de_usuario");
                String email = rs.getString("email");
                String senha = rs.getString("senha");
                String telefone = rs.getString("telefone");

                Usuario u = new Usuario(nome, nomeUser, email, senha, telefone);
                usuarios.add(u);
            }
            return usuarios;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

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

                String nome = rs.getString("nome_completo");
                String nomeUser = rs.getString("nome_de_usuario");
                String email = rs.getString("email");
                String senha = rs.getString("senha");
                String telefone = rs.getString("telefone");

                return new Usuario(nome, nomeUser, email, senha, telefone);
            } else return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Ver se o user já existe no banco
    public static String verificar(String nomeUsuario) {
        String sql = "SELECT * FROM USUARIO WHERE nome_de_usuario = ? ;";

        try (Connection c = ConexaoBD.getConnection()) {
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, nomeUsuario);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                String nomeUser = rs.getString("nome_de_usuario");

                return nomeUser;
            } else return "não";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
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