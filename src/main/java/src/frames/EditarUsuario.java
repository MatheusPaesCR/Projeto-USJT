package src.frames;

import src.db.helper.UsuarioHelper;
import src.main.Principal;
import src.models.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.PatternSyntaxException;

public class EditarUsuario extends JFrame {     //Painel do usuario
    private JTextField nomeDeUsuarioField;
    private JTextField nomeCompletoField;
    private JTextField emailField;
    private JTextField senhaField;
    private JTextField confirmarSenhaField;
    private JTextField telefoneField;
    //// painel da tabela
    private JTable table;
    private JTable nomes;
    private TableModel model;
    private DefaultTableModel nomesModel;
    //painel atualizar usuario 
    private JTextField atualizarNomeCompletoField;
    private JTextField atualizarNomeDeUsuarioField;
    private JTextField atualizarEmailField;
    private JTextField atualizarSenhaField;
    private JTextField atualizarConfirmarSenhaField;
    private JTextField atualizarTelefoneField;
    //excluir usuario
    private JTextField nomeCompletoAExcluir;


    public EditarUsuario() { //aqui o titulo da janela para acessar o sistema
        super("Editar Usuario");
        acessarUsuario();
    }

    private void acessarUsuario() {

        //////Adicionar usuario///----------------------------
        setLayout(new BorderLayout());
        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new GridLayout(6, 1));//linha/colunas

        JPanel painelAdicionarUsuario = new JPanel();
        JLabel adicionarUsuario = new JLabel("Adicionar usuário");
        adicionarUsuario.setFont(new Font("Verdana", Font.PLAIN, 30));
        painelAdicionarUsuario.add(adicionarUsuario);

        JLabel nomeCompletoLabel = new JLabel("Nome completo:");
        nomeCompletoLabel.setFont(new Font("Verdana", Font.PLAIN, 20));
        nomeCompletoField = new JTextField(20);
        JLabel nomeDeUsuarioLabel = new JLabel("Nome de usuário (LOGIN):");
        nomeDeUsuarioLabel.setFont(new Font("Verdana", Font.PLAIN, 20));
        nomeDeUsuarioField = new JTextField(20);
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Verdana", Font.PLAIN, 20));
        emailField = new JTextField(20);
        JLabel senhaLabel = new JLabel("Senha:");
        senhaLabel.setFont(new Font("Verdana", Font.PLAIN, 20));
        senhaField = new JTextField(20);
        JLabel confirmarSenhaLabel = new JLabel("Confirmar senha:");
        confirmarSenhaLabel.setFont(new Font("Verdana", Font.PLAIN, 20));
        confirmarSenhaField = new JTextField(20);
        JLabel telefoneLabel = new JLabel("Telefone:");
        telefoneLabel.setFont(new Font("Verdana", Font.PLAIN, 20));
        telefoneField = new JTextField(20);

        painelPrincipal.add(nomeCompletoLabel);
        painelPrincipal.add(nomeCompletoField);
        painelPrincipal.add(nomeDeUsuarioLabel);
        painelPrincipal.add(nomeDeUsuarioField);
        painelPrincipal.add(emailLabel);
        painelPrincipal.add(emailField);
        painelPrincipal.add(senhaLabel);
        painelPrincipal.add(senhaField);
        painelPrincipal.add(confirmarSenhaLabel);
        painelPrincipal.add(confirmarSenhaField);
        painelPrincipal.add(telefoneLabel);
        painelPrincipal.add(telefoneField);

        //---------BOTOES---------
        JPanel painelBotoes = new JPanel();  //criando o painel para os botoes
        painelBotoes.setLayout(new FlowLayout());


        BotaoSalvarAction botaoSalvarAction = new BotaoSalvarAction();
        BotaoApagarAction botaoApagarAction = new BotaoApagarAction();
        BotaoFecharAction botaoFecharAction = new BotaoFecharAction();

        JButton botaoSalvar = new JButton("Salvar");
        botaoSalvar.addActionListener(botaoSalvarAction);//ação
        JButton botaoApagar = new JButton("Limpar");
        botaoApagar.addActionListener(botaoApagarAction);//ação


        painelBotoes.add(botaoSalvar);
        painelBotoes.add(botaoApagar);

        JPanel painelUsuario = new JPanel();
        painelUsuario.setLayout(new GridLayout(3, 1));
        painelUsuario.add(painelAdicionarUsuario);
        painelUsuario.add(painelPrincipal);
        painelUsuario.add(painelBotoes, BorderLayout.SOUTH);


        ///------Atualizar usuario
        ///------Atualizar usuario
        ///------Atualizar usuario
        ///------Atualizar usuario
        JPanel painelPrincipalAtualizarUsuario = new JPanel();
        painelPrincipalAtualizarUsuario.setLayout(new GridLayout(6, 1));//linha/colunas

        JPanel painelAtualizarUsuario = new JPanel();

        JLabel atualizarUsuario = new JLabel("Atualizar usuário");
        atualizarUsuario.setFont(new Font("Verdana", Font.PLAIN, 30));
        painelAtualizarUsuario.add(atualizarUsuario);

        JLabel atualizarNomeCompletoLabel = new JLabel("Nome completo:");
        atualizarNomeCompletoLabel.setFont(new Font("Verdana", Font.PLAIN, 20));
        atualizarNomeCompletoField = new JTextField(20);
        JLabel atualizarNomeDeUsuarioLabel = new JLabel("Nome de usuário (LOGIN):");
        atualizarNomeDeUsuarioLabel.setFont(new Font("Verdana", Font.PLAIN, 20));
        atualizarNomeDeUsuarioField = new JTextField(20);
        JLabel atualizarEmailLabel = new JLabel("Email:");
        atualizarEmailLabel.setFont(new Font("Verdana", Font.PLAIN, 20));
        atualizarEmailField = new JTextField(20);
        JLabel atualizarSenhaLabel = new JLabel("Senha:");
        atualizarSenhaLabel.setFont(new Font("Verdana", Font.PLAIN, 20));
        atualizarSenhaField = new JTextField(20);
        JLabel atualizarConfirmarSenhaLabel = new JLabel("Confirmar senha:");
        atualizarConfirmarSenhaLabel.setFont(new Font("Verdana", Font.PLAIN, 20));
        atualizarConfirmarSenhaField = new JTextField(20);
        JLabel atualizarTelefoneLabel = new JLabel("Telefone:");
        atualizarTelefoneLabel.setFont(new Font("Verdana", Font.PLAIN, 20));
        atualizarTelefoneField = new JTextField(20);

        painelPrincipalAtualizarUsuario.add(atualizarNomeCompletoLabel);
        painelPrincipalAtualizarUsuario.add(atualizarNomeCompletoField);
        painelPrincipalAtualizarUsuario.add(atualizarNomeDeUsuarioLabel);
        painelPrincipalAtualizarUsuario.add(atualizarNomeDeUsuarioField);
        painelPrincipalAtualizarUsuario.add(atualizarEmailLabel);
        painelPrincipalAtualizarUsuario.add(atualizarEmailField);
        painelPrincipalAtualizarUsuario.add(atualizarSenhaLabel);
        painelPrincipalAtualizarUsuario.add(atualizarSenhaField);
        painelPrincipalAtualizarUsuario.add(atualizarConfirmarSenhaLabel);
        painelPrincipalAtualizarUsuario.add(atualizarConfirmarSenhaField);
        painelPrincipalAtualizarUsuario.add(atualizarTelefoneLabel);
        painelPrincipalAtualizarUsuario.add(atualizarTelefoneField);

        //---------BOTOES---------
        JPanel painelBotaoAtualizar = new JPanel();  //criando o painel para os botoes
        painelBotaoAtualizar.setLayout(new FlowLayout());

        BotaoAtualizarAction botaoAtualizarAction = new BotaoAtualizarAction();

        JButton botaoAtualizarUsuario = new JButton("Atualizar");
        botaoAtualizarUsuario.addActionListener(botaoAtualizarAction);//ação

        painelBotaoAtualizar.add(botaoAtualizarUsuario);

        JPanel atualizandolUsuario = new JPanel();
        atualizandolUsuario.setLayout(new GridLayout(3, 1));
        atualizandolUsuario.add(painelAtualizarUsuario);
        atualizandolUsuario.add(painelPrincipalAtualizarUsuario);
        atualizandolUsuario.add(painelBotaoAtualizar, BorderLayout.SOUTH);


        //Tabela daqui pra baixo

        setTitle("Dados dos usuários");
        Object columns[] = {"Nome Completo", "Email", "Login", "Senha", "Número do celular"};  ///titulo das colunas
        nomesModel = new DefaultTableModel(columns, 6);
        nomes = new JTable(nomesModel);

        String dados = "";
        String dados2 = "";
        Usuario usuario = new Usuario();
        ArrayList<Usuario> lista = UsuarioHelper.listar();

        for (int i = 0; i < lista.size(); i++) {
            usuario = lista.get(i);

            Object[] row1 = {usuario.getNomeCompleto(), usuario.getEmail(), usuario.getNomeDeUsuario(), usuario.getSenha(), usuario.getTelefone()};
            nomesModel.insertRow(0, row1);
        }


        table = new JTable(nomesModel); //JTable
        final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(nomesModel);
        table.setRowSorter(sorter);
        //
        JPanel panel = new JPanel(new BorderLayout());

        JLabel label = new JLabel("Filtro");
        final JTextField filterText = new JTextField("Digite aqui para pesquisar...");


        panel.add(filterText, BorderLayout.NORTH);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);


        JPanel painelBotoesTabela = new JPanel();  //criando o painel para os botoes
        painelBotoesTabela.setLayout(new FlowLayout());

        JButton buttonVoltar = new JButton("Voltar ao gerenciador");
        JButton buttonFiltrar = new JButton("Filtrar");
        JButton buttonAtualizar = new JButton("Atualizar");

        painelBotoesTabela.add(buttonFiltrar);
        painelBotoesTabela.add(buttonAtualizar);
        painelBotoesTabela.add(buttonVoltar);

        //Adicionando ação para o botão filtrar
        buttonFiltrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String text = filterText.getText();
                if (text.length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    try {
                        sorter.setRowFilter(RowFilter.regexFilter(text));
                    } catch (PatternSyntaxException pse) {
                        System.out.println("Bad regex pattern");
                    }
                }
            }
        });
        //botao voltar da tabela

        buttonVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        //botao atualizar tabela
        buttonAtualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                ///Limpando a tabela
                DefaultTableModel dm = (DefaultTableModel) table.getModel();
                dm.getDataVector().removeAllElements();
                dm.fireTableDataChanged();

                //Aqui parei de limpar
                String dados = "";
                String dados2 = "";
                Usuario usuario = new Usuario();
                Usuario auxiliar = new Usuario();
                ArrayList<Usuario> lista = UsuarioHelper.listar();


                for (int i = 0; i < lista.size(); i++) {
                    usuario = lista.get(i);

                    Object[] row1 = {usuario.getNomeCompleto(), usuario.getEmail(), usuario.getNomeDeUsuario(), usuario.getSenha(), usuario.getTelefone()};
                    nomesModel.insertRow(0, row1);

                }
            }
        });

        JPanel painelTabela = new JPanel();
        painelTabela.setLayout(new GridLayout(2, 1));

        painelTabela.add(panel);
        painelTabela.add(painelBotoesTabela);

        JPanel painelExcluir = new JPanel();
        painelExcluir.setLayout(new GridLayout(3, 1));//linha/colunas

        //jlbel's
        JLabel excluirUsuario = new JLabel("Excluir usuário");
        excluirUsuario.setFont(new Font("Verdana", Font.PLAIN, 30));
        JLabel excluirUser = new JLabel("Nome completo do usuário:");

        //JTextfils
        nomeCompletoAExcluir = new JTextField(30);

        BotaoExluirUsuarioAction botaoExluirUsuarioAction = new BotaoExluirUsuarioAction();

        JButton botaoExluirUsuario = new JButton("Excluir Usuário");
        botaoExluirUsuario.addActionListener(botaoExluirUsuarioAction);

        JPanel painelDoExcluirTitulo = new JPanel();
        JPanel painelDoExcluirTextos = new JPanel();
        JPanel painelDoExcluirBotao = new JPanel();

        painelDoExcluirTitulo.add(excluirUsuario);
        painelDoExcluirTextos.add(excluirUser);
        painelDoExcluirTextos.add(nomeCompletoAExcluir);
        painelDoExcluirBotao.add(botaoExluirUsuario);

        painelExcluir.add(painelDoExcluirTitulo);
        painelExcluir.add(painelDoExcluirTextos);
        painelExcluir.add(painelDoExcluirBotao);

        JPanel painelFinal = new JPanel();
        painelFinal.setLayout(new GridLayout(2, 2));

        painelFinal.add(painelTabela);
        painelFinal.add(painelExcluir);
        painelFinal.add(painelUsuario);
        painelFinal.add(atualizandolUsuario);

        add(painelFinal);//Painel a ser exibido
    }

    ///botao salvar para adicionar usuarios
    private class BotaoSalvarAction implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String nomeDeUsuario = nomeDeUsuarioField.getText();
            String nomeCompleto = nomeCompletoField.getText();
            String email = emailField.getText();
            String senha = senhaField.getText();
            String confirmarSenha = confirmarSenhaField.getText();
            String telefone = telefoneField.getText();

            if (senha.equalsIgnoreCase(confirmarSenha)) {
                //adicionar no banco
                Usuario adicionarUser = new Usuario();

                adicionarUser.setNomeCompleto(nomeCompleto);
                adicionarUser.setNomeDeUsuario(nomeDeUsuario);
                adicionarUser.setEmail(email);
                adicionarUser.setSenha(senha);
                adicionarUser.setTelefone(telefone);

                UsuarioHelper.adicionarUsuario(adicionarUser);
            } else {
                JOptionPane.showMessageDialog(null, "As senhas não correspondem!", "Erro", JOptionPane.PLAIN_MESSAGE);
            }
        }
    }

    //botao de limpar digitação do adicionar usuario
    private class BotaoApagarAction implements ActionListener {
        public void actionPerformed(ActionEvent event) {

            //limpando a digitação
            nomeDeUsuarioField.setText("");
            nomeCompletoField.setText("");
            emailField.setText("");
            senhaField.setText("");
            confirmarSenhaField.setText("");
            telefoneField.setText("");
        }
    }

    //botao pra fechar a janela de editar usuario
    private class BotaoFecharAction implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            Principal p = new Principal();
            p.fecharUsuario();
        }
    }

    //botao excluir usuario
    private class BotaoExluirUsuarioAction implements ActionListener {
        public void actionPerformed(ActionEvent event) {

            Usuario usuario = new Usuario();
            String oNomeDoExcluido = nomeCompletoAExcluir.getText();
            JOptionPane.showMessageDialog(null, "Usuario excluido:" + oNomeDoExcluido);
            nomeCompletoAExcluir.setText("");


            usuario.setNomeCompleto(oNomeDoExcluido);
            UsuarioHelper.apagar(usuario.getNomeCompleto());

            //Botão para excluir user
        }
    }

    //botao para atualizar suario
    private class BotaoAtualizarAction implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String atualizarNome = atualizarNomeCompletoField.getText();
            String atualizarNomeUser = atualizarNomeDeUsuarioField.getText();
            String atualizarEmail = atualizarEmailField.getText();
            String atualizarSenha = atualizarSenhaField.getText();
            String atualizarConfirma = atualizarConfirmarSenhaField.getText();
            String atualizarTelefone = atualizarTelefoneField.getText();

            if (atualizarSenha.equalsIgnoreCase(atualizarConfirma)) {
                Usuario us = new Usuario();

                us.setNomeCompleto(atualizarNome);
                us.setNomeDeUsuario(atualizarNomeUser);
                us.setEmail(atualizarEmail);
                us.setSenha(atualizarSenha);
                us.setTelefone(atualizarTelefone);

                atualizarNomeCompletoField.setText("");
                atualizarNomeDeUsuarioField.setText("");
                atualizarEmailField.setText("");
                atualizarSenhaField.setText("");
                atualizarConfirmarSenhaField.setText("");
                atualizarTelefoneField.setText("");

                UsuarioHelper.atualizar(us);

                //adicionar no banco
            } else {
                JOptionPane.showMessageDialog(null, "As senhas não correspondem!", "Erro", JOptionPane.PLAIN_MESSAGE);
            }
        }
    }

}