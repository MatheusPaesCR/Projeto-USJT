package src.main;

import src.db.ConexaoBD;
import src.frames.AcessoFrame;
import src.frames.DepartamentoFrame;
import src.frames.EditarUsuario;
import src.frames.projeto.ProjetoMainFrame;
import src.models.Usuario;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Principal {
    //Atributos
    private JTable table;
    private TableModel model;
    public static AcessoFrame acessoFrame = null;
    public static EditarUsuario editarUsuario; //da tabela com filtro:

    public static void main(String[] args) {
        //Inerface do acesso usuario e senha
        criarAcesso(0);
        //interface da tela gerenciador do departamento é criada depois de fazer login e o login ser validado
//        criarDepartamento();
    }

    //Inerface do acesso usuario e senha
    public static void criarAcesso(int x) {    //Para abrir e 0 para fechar
        int status = 3;
        status = x;

        if (status == 0) {

            acessoFrame = new AcessoFrame();
            acessoFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            acessoFrame.setSize(500, 300);
            acessoFrame.setVisible(true);
            acessoFrame.setLocationRelativeTo(null);
        }
        if (status == 1) {
            acessoFrame.setVisible(false);
        }


    }

    //Gerenciar editarUsuario
    public static void fecharUsuario() {
        adicionarUsuario(1);
    }

    public static void adicionarUsuario(int x) {    //1 para abrir e 0 para fechar
        int status = x;


        if (status == 0) {
            editarUsuario = new EditarUsuario();
            editarUsuario.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            editarUsuario.setSize(1280, 850);
            editarUsuario.setVisible(true);
            editarUsuario.setLocationRelativeTo(null);
        }
        if (status == 1) {
            editarUsuario.setVisible(false);
        }
    }

    //interface da tela gerenciador do departamento
    public static void criarDepartamento(String user, String senha) {

        int busca = 0;
        int cont = 0;

        Usuario usuario = new Usuario();
        ArrayList<Usuario> usuarios = new ArrayList<>();
        //try with resources
        try (
                PreparedStatement ps = ConexaoBD.getConnection().prepareStatement("SELECT nome_de_usuario, senha FROM Usuario;");
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {


                String nomeUser = rs.getString("nome_de_usuario");
                String senhaUser = rs.getString("senha");


                if (nomeUser.equalsIgnoreCase(user) && senhaUser.equalsIgnoreCase(senha)) {
                    //Encontrou
                    cont = 1;
                } else {
                    //Não encontrou no banco de dados

                }


            }

        } catch (Exception e) {
            e.printStackTrace();
            //return null;
        }


        if (cont == 1) {
            busca = 1;
        } else {
            busca = -1;
        }


        if (busca == 1) {
            //criando o objeto departamento frame
            DepartamentoFrame departamentoFrame = new DepartamentoFrame();
            //definindo o que acontece ao apertar x da janela
            departamentoFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            ///DEFININDO O TAMANHO DA JANELA:
            departamentoFrame.setSize(1280, 720);
            departamentoFrame.setVisible(true);//define se esta visivel ou nao
            departamentoFrame.setLocationRelativeTo(null);
            //src.frames.AcessoFrame acessoFrame;
            criarAcesso(1);

        } else {
            JOptionPane.showMessageDialog(null, "Usuário ou senha incorreto(s)!", "OPS!", JOptionPane.PLAIN_MESSAGE);
        }
    }
}