package src.frames.projeto;

import src.db.helper.ProjetoHelper;
import src.models.Projeto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.ArrayList;

public class ProjetoMainFrame extends JFrame {
    DefaultTableModel modeloTabela;
    JButton btnAdicionar;
    JButton btnEditar;
    JButton btnExcluir;
    JPanel root;

    public ProjetoMainFrame() {
        super("Editar Projetos");
        setLayout(new BorderLayout());

        montarRoot();
        montarTabela();

        add(root);
    }


    private void montarRoot() {
        setTitle("Dados dos projetos");

        root = new JPanel(new GridBagLayout());
    }

    private void montarTabela() {
        criarTituloDasColunasDaTabela();
        JTable tabela = criarTabela();

        GridBagConstraints c = new GridBagConstraints();

        montarBotoes(c);
        adicionarAcaoBotoes();

        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(10,20,0,20);
        c.ipady = 40;
        c.weightx = 0.0;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 1;
        JPanel painelDaTabela = new JPanel(new BorderLayout());
        final JTextField filterText = new JTextField("Digite aqui para pesquisar...");
        painelDaTabela.add(filterText, BorderLayout.NORTH);
        painelDaTabela.add(new JScrollPane(tabela), BorderLayout.CENTER);

        root.add(painelDaTabela, c);
    }

    private void montarBotoes(GridBagConstraints c) {
        btnAdicionar = new JButton("Adicionar");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.NORTH;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 0;
        root.add(btnAdicionar, c);

        btnEditar = new JButton("Editar");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.NORTH;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 0;
        root.add(btnEditar, c);

        btnExcluir = new JButton("Excluir");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.PAGE_END;
        c.weightx = 0.5;
        c.gridx = 2;
        c.gridy = 0;
        root.add(btnExcluir, c);
    }

    private void adicionarAcaoBotoes() {
        btnAdicionar.addActionListener(actionEvent -> {
            AdicionarProjetoFrame.abrir();
        });
    }

    private void criarTituloDasColunasDaTabela() {
        Object[] columns = {"Registro do Projeto", "Nome do Projeto", "Descrição", "Nome do Proprietário", "Email do Proprietário"};
        modeloTabela = new DefaultTableModel(columns, 5);
    }

    private JTable criarTabela() {
        ArrayList<Projeto> projetos = ProjetoHelper.listar();

        if (projetos != null) {
            for (Projeto projeto : projetos) {

                Object[] row1 = {projeto.getRegistro(), projeto.getNome(), projeto.getDescricao(), projeto.getProprietario().getNomeDeUsuario(), projeto.getProprietario().getEmail()};
                modeloTabela.insertRow(0, row1);
            }
        }

        JTable tabela = new JTable(modeloTabela);
        tabela.setRowSorter(new TableRowSorter<TableModel>(modeloTabela));
        return tabela;
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