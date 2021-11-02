package src.frames.projeto;

import src.db.helper.ProjetoHelper;
import src.db.helper.UsuarioHelper;
import src.models.Projeto;
import src.models.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

public class AdicionarProjetoFrame extends JFrame {
    private JPanel root;
    private JTextField descricaoProjetoField;
    private JTextField nomeProjetoField;
    private JTable tabelaDeUsuarios;
    private JButton btnCriar;
    private CallbackAdicionarProjeto callback;
    private final GridBagConstraints c = new GridBagConstraints();
    private boolean isEditMode = false;
    private int idProjeto;

    public AdicionarProjetoFrame() {
        super("");
        setLayout(new BorderLayout());

        montarRoot();
        montarFormulario();
        montarTabela();
        montarBotoes();
        adicionarAcaoBotoes();

        add(root);
    }

    private void colocarEmModoDeEdicao() {
        isEditMode = true;
        setTitle("Editar Projeto");
        popularFormulario();
    }

    private void montarRoot() {
        setTitle("Adicionar Projeto");

        root = new JPanel(new GridBagLayout());
    }

    private void montarFormulario() {
        JLabel nomeProjetoLabel = new JLabel("Nome do Projeto:");
        nomeProjetoLabel.setFont(new Font("Verdana", Font.PLAIN, 18));
        nomeProjetoField = new JTextField(20);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(10, 20, 0, 20);
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 0;
        c.weighty = 3;
        root.add(nomeProjetoLabel, c);
        c.gridx = 1;
        c.weightx = 2.5;
        root.add(nomeProjetoField, c);

        JLabel descricaoProjetoLabel = new JLabel("Descrição do Projeto:");
        descricaoProjetoLabel.setFont(new Font("Verdana", Font.PLAIN, 18));
        descricaoProjetoField = new JTextField(40);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(10, 20, 0, 20);
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 1;
        c.weighty = 3;
        root.add(descricaoProjetoLabel, c);
        c.gridx = 1;
        c.weightx = 2.5;
        root.add(descricaoProjetoField, c);
    }

    private void montarTabela() {
        DefaultTableModel modeloDeTabela = criarTituloDasColunasDaTabela();
        criarTabela(modeloDeTabela);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(10, 20, 0, 20);
        c.ipady = 40;
        c.weightx = 0.0;
        c.weighty = 40;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 2;
        JPanel painelDaTabela = new JPanel(new BorderLayout());
        painelDaTabela.add(new JScrollPane(tabelaDeUsuarios), BorderLayout.CENTER);

        root.add(painelDaTabela, c);
    }

    private DefaultTableModel criarTituloDasColunasDaTabela() {
        Object[] columns = {"Usuário", "Nome Completo", "Email"};
        return new DefaultTableModel(columns, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
    }

    private void criarTabela(DefaultTableModel modeloDeTabela) {
        ArrayList<Usuario> usuarios = UsuarioHelper.listar();

        if (usuarios != null) {
            for (Usuario usuario : usuarios) {
                Object[] row1 = {usuario.getNomeDeUsuario(), usuario.getNomeCompleto(), usuario.getEmail()};
                modeloDeTabela.insertRow(0, row1);
            }
        }

        tabelaDeUsuarios = new JTable(modeloDeTabela);
        tabelaDeUsuarios.getTableHeader().setFont(new Font("Verdana", Font.BOLD, 13));
        tabelaDeUsuarios.setRowSorter(new TableRowSorter<TableModel>(modeloDeTabela));
        tabelaDeUsuarios.setRowHeight(20);
        tabelaDeUsuarios.setFont(new Font("Verdana", Font.PLAIN, 12));
        tabelaDeUsuarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    private void montarBotoes() {
        btnCriar = new JButton("Criar");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.NORTH;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 3;
        root.add(btnCriar, c);
    }

    private void adicionarAcaoBotoes() {
        btnCriar.addActionListener(actionEvent -> {
            if (isEditMode){
                if (atualizarProjeto()){
                    JOptionPane.showMessageDialog(null, "Projeto atualizado com sucesso!!!");
                    fechar();
                }
            } else {
                if (criarProjeto()) {
                    JOptionPane.showMessageDialog(null, "Projeto criado com sucesso!!!");
                    fechar();
                }
            }
        });
    }

    private boolean criarProjeto(){
        String nomeUsuario = pegarNomeDeUsuarioProprietarioProjeto();
        String nomeDoProjeto = nomeProjetoField.getText();
        String descricaoDoProjeto = descricaoProjetoField.getText();

        if (!validarCampos(nomeDoProjeto, descricaoDoProjeto, nomeUsuario)) return false;

        Usuario usuario = UsuarioHelper.pegar(nomeUsuario);
        if (usuario != null) { ProjetoHelper.adicionar(nomeDoProjeto, descricaoDoProjeto, usuario.getId()); }
        return true;
    }

    private boolean atualizarProjeto(){
        String nomeUsuario = pegarNomeDeUsuarioProprietarioProjeto();
        String nomeDoProjeto = nomeProjetoField.getText();
        String descricaoDoProjeto = descricaoProjetoField.getText();

        if (!validarCampos(nomeDoProjeto, descricaoDoProjeto, nomeUsuario)) return false;

        Usuario usuario = UsuarioHelper.pegar(nomeUsuario);

        Projeto projeto = ProjetoHelper.pegar(idProjeto);
        projeto.setNome(nomeDoProjeto);
        projeto.setDescricao(descricaoDoProjeto);
        projeto.setProprietario(usuario);

        if (usuario != null) { ProjetoHelper.atualizar(projeto); }
        return true;
    }

    private boolean validarCampos(String nomeDoProjeto, String descricaoDoProjeto, String nomeUsuario) {
        if (nomeDoProjeto.equals("")) {
            JOptionPane.showMessageDialog(null, "Digite um nome para o projeto");
            return false;
        }

        if (descricaoDoProjeto.equals("")) {
            JOptionPane.showMessageDialog(null, "Digite uma descrição para o projeto");
            return false;
        }

        if (nomeUsuario.equals("")) {
            JOptionPane.showMessageDialog(null, "Selecione um proprietário para o projeto");
            return false;
        }

        return true;
    }

    private String pegarNomeDeUsuarioProprietarioProjeto(){
        int column = 0;
        int row = tabelaDeUsuarios.getSelectedRow();
        if (row == -1){
            return "";
        } else {
            // Evita pegar a linha errada quando o usuário ordena a coluna
            int realRow = tabelaDeUsuarios.convertRowIndexToModel(row);
            return tabelaDeUsuarios.getModel().getValueAt(realRow, column).toString();
        }
    }

    private void popularFormulario() {
        Projeto projeto = ProjetoHelper.pegar(idProjeto);
        if (projeto != null) {
            nomeProjetoField.setText(projeto.getNome());
            descricaoProjetoField.setText(projeto.getDescricao());
            int rowDoProprietarioDoProjeto = pegarRowComIdDoUsuario(projeto.getProprietario().getEmail());
            tabelaDeUsuarios.setRowSelectionInterval(rowDoProprietarioDoProjeto, rowDoProprietarioDoProjeto);

            btnCriar.setText("Atualizar");
        }
    }

    private int pegarRowComIdDoUsuario(String emailUsuario){
        Vector<Vector> rows = ((DefaultTableModel) tabelaDeUsuarios.getModel()).getDataVector();
        for (int index = 0; index < rows.size(); index++) {
            if (rows.get(index).contains(emailUsuario)){
                return tabelaDeUsuarios.convertRowIndexToModel(index);
            }
        }
        return -1;
    }

    /**
     * Métodos para abrir a tela
     */
    private static AdicionarProjetoFrame adicionarProjetoFrame;

    private static AdicionarProjetoFrame getInstance() {
        if (adicionarProjetoFrame == null) {
            adicionarProjetoFrame = new AdicionarProjetoFrame();
        }
        return adicionarProjetoFrame;
    }

    public static void fechar() {
        getInstance().setVisible(false);
        getInstance().callback.invoke();
        adicionarProjetoFrame = null;
    }

    public static void abrir(CallbackAdicionarProjeto callback) {
        getInstance().callback = callback;
        getInstance().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getInstance().setSize(1080, 720);
        getInstance().setVisible(true);
        getInstance().setLocationRelativeTo(null);
    }

    public static void abrirParaEdicao(int idProjeto, CallbackAdicionarProjeto callback) {
        getInstance().idProjeto = idProjeto;
        abrir(callback);
        getInstance().colocarEmModoDeEdicao();
    }
}
