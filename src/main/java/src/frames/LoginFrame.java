package src.frames;

import src.db.helper.UsuarioHelper;
import src.main.LoginInfo;
import src.models.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {
    private JTextField userField;
    private JPasswordField senhaField;

    /**
     * Aqui o titulo da janela para acessar o sistema
     **/
    public LoginFrame() {
        super("Acesso ao sistema");

        acessarCadastro();
    }

    private void acessarCadastro() {
        setLayout(new BorderLayout());
        JPanel painelTitulo = new JPanel();
        painelTitulo.setLayout(new FlowLayout());

        //criando jLabel
        JLabel titulo = new JLabel("Login");
        titulo.setFont(new Font("Verdana", Font.PLAIN, 16));//definindo fone do jlabel
        painelTitulo.add(titulo);//Adicionando lJLabel no painel titulo

        JPanel painelLogin = new JPanel();
        painelLogin.setLayout(new FlowLayout());//Mudando o modo de se posicionar

        JLabel userLabel = new JLabel("Usuários:");
        userField = new JTextField(40);//40 é o tamanho da digitação

        JLabel senhaLabel = new JLabel("Senha:");
        senhaField = new JPasswordField(40);
        //Adicionando ao painel login os labels e textFilds
        painelLogin.add(userLabel);
        painelLogin.add(userField);
        painelLogin.add(senhaLabel);
        painelLogin.add(senhaField);
        //---------BOTOES---------
        JPanel painelBotoes = new JPanel();  //criando o painel para os botoes
        painelBotoes.setLayout(new FlowLayout());

        BotaoEntrarAction botaoEntrarAction = new BotaoEntrarAction();//Criando objeto para ação
        BotaoLimparAction botaoLimparAction = new BotaoLimparAction();

        JButton botaoEntrar = new JButton("Entrar");
        botaoEntrar.addActionListener(botaoEntrarAction);//ação
        JButton botaoLimpar = new JButton("Limpar");
        botaoLimpar.addActionListener(botaoLimparAction);//ação
        painelBotoes.add(botaoEntrar);
        painelBotoes.add(botaoLimpar);

        //adicionar os paineis ao frame
        add(painelTitulo, BorderLayout.NORTH); //ADD NO NORTE DA TELA
        add(painelLogin, BorderLayout.CENTER); //add no centro
        add(painelBotoes, BorderLayout.SOUTH);//Add painel no sul da tela

    }

    private class BotaoEntrarAction implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String usuario = userField.getText();
            String senha = new String(senhaField.getPassword());

            boolean usuarioValido = verificarUsuario(usuario, senha);

            if (usuarioValido) {
                MainFrame.abrir();
                fechar();
            }
            else JOptionPane.showMessageDialog(null, "Usuário ou senha incorreto(s)!", "OPS!", JOptionPane.PLAIN_MESSAGE);
        }
    }

    private boolean verificarUsuario(String usuario, String senha) {
        Usuario usuarioEncontrado = UsuarioHelper.pegar(usuario);
        if (usuarioEncontrado != null) {
            if (usuarioEncontrado.getNomeDeUsuario().equalsIgnoreCase(usuario) && usuarioEncontrado.getSenha().equalsIgnoreCase(senha)) {
                LoginInfo.getInstancia().usuarioLogado = usuarioEncontrado;
                return true;
            }
        }
        return false;
    }

    private class BotaoLimparAction implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            //limpando a digitação
            userField.setText("");
            senhaField.setText("");
        }
    }

    /**
     * Métodos para abrir a tela
     */
    private static LoginFrame loginFrame;

    private static LoginFrame getInstance() {
        if (loginFrame == null) {
            loginFrame = new LoginFrame();
        }
        return loginFrame;
    }

    public static void fechar() {
        if (loginFrame != null) loginFrame.setVisible(false);
        loginFrame = null;
    }
    public static void abrir() {
        getInstance().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getInstance().setSize(500, 300);
        getInstance().setVisible(true);
        getInstance().setLocationRelativeTo(null);
    }
}