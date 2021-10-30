package src.frames.projeto;

import javax.swing.*;
import java.awt.*;

public class AdicionarProjetoFrame extends JFrame {
    private JPanel root;
    private JTextField descricaoProjetoField;
    private JTextField nomeProjetoField;
    private JTextField emailField;
    private JTextField senhaField;
    private JTextField confirmarSenhaField;
    private JTextField telefoneField;

    public AdicionarProjetoFrame() {
        super("Adicionar Projeto");
        setLayout(new BorderLayout());

        montarRoot();
        montarFormulario();
        montarBotoes();

        add(root);
    }

    private void montarRoot() {
        root = new JPanel(new GridBagLayout());
    }

    private void montarFormulario() {
        GridBagConstraints c = new GridBagConstraints();

        JLabel nomeProjetoLabel = new JLabel("Nome do Projeto:");
        nomeProjetoLabel.setFont(new Font("Verdana", Font.PLAIN, 18));
        nomeProjetoField = new JTextField(20);

        JLabel descricaoProjetoLabel = new JLabel("Descrição do Projeto:");
        descricaoProjetoLabel.setFont(new Font("Verdana", Font.PLAIN, 18));
        descricaoProjetoField = new JTextField(40);

        root.add(nomeProjetoLabel);
        root.add(nomeProjetoField);
        root.add(descricaoProjetoLabel);
        root.add(descricaoProjetoField);

        //---------BOTOES---------
        JPanel painelBotoes = new JPanel();  //criando o painel para os botoes
        painelBotoes.setLayout(new FlowLayout());


//        EditarUsuario.BotaoSalvarAction botaoSalvarAction = new EditarUsuario.BotaoSalvarAction();
//        EditarUsuario.BotaoApagarAction botaoApagarAction = new EditarUsuario.BotaoApagarAction();
//        EditarUsuario.BotaoFecharAction botaoFecharAction = new EditarUsuario.BotaoFecharAction();
//
//        JButton botaoSalvar = new JButton("Salvar");
//        botaoSalvar.addActionListener(botaoSalvarAction);//ação
//        JButton botaoApagar = new JButton("Limpar");
//        botaoApagar.addActionListener(botaoApagarAction);//ação
//
//
//        painelBotoes.add(botaoSalvar);
//        painelBotoes.add(botaoApagar);

//        JPanel painelUsuario = new JPanel();
//            painelUsuario.setLayout(new GridLayout(3, 1));
//        painelUsuario.add(painelAdicionarUsuario);
//        painelUsuario.add(painelPrincipal);
//        painelUsuario.add(painelBotoes, BorderLayout.SOUTH);
    }

    private void montarBotoes() {

    }

    /**
     * Métodos para abrir a tela
     */
    private static ProjetoMainFrame projetoMainFrame;

    private static ProjetoMainFrame getInstance() {
        if (projetoMainFrame == null) {
            projetoMainFrame = new ProjetoMainFrame();
        }
        return projetoMainFrame;
    }

    public static void fechar() {
        getInstance().setVisible(false);
    }

    public static void abrir() {
        getInstance().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getInstance().setSize(1080, 720);
        getInstance().setVisible(true);
        getInstance().setLocationRelativeTo(null);
    }
}
