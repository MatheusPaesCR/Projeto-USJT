package src.main;

import src.db.helper.UsuarioHelper;
import src.frames.AcessoFrame;
import src.frames.DepartamentoFrame;
import src.frames.EditarUsuario;
import src.models.Usuario;

import javax.swing.*;

public class Principal {
    public static AcessoFrame acessoFrame = null;
    public static EditarUsuario editarUsuario; //da tabela com filtro:

    public static void main(String[] args) {
        criarAcesso(0);
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
        boolean usuarioValido = false;

        Usuario usuarioEncontrado = UsuarioHelper.pegar(user);
        if (usuarioEncontrado != null) {
            if (usuarioEncontrado.getNomeDeUsuario().equalsIgnoreCase(user) && usuarioEncontrado.getSenha().equalsIgnoreCase(senha)) {
                LoginInfo.getInstancia().usuarioLogado = usuarioEncontrado;
                usuarioValido = true;
            }
        }

        if (usuarioValido) {
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