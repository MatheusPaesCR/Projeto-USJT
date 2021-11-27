package src.frames.requisito;

import src.db.helper.RequisitoHelper;
import src.models.Requisito;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.ArrayList;

public class ListarRequisitosFrame extends JFrame {
    private JTable tabelaRequisitos;
    private JButton btnAdicionar;
    private JButton btnEditar;
    private JButton btnExcluir;
    private JButton btnDetalhes;
    private JPanel root;
    private int idProjeto;
    private final GridBagConstraints c = new GridBagConstraints();

    public ListarRequisitosFrame() {
        super();
        setLayout(new BorderLayout());

        montarRoot();
        montarBotoes();
        adicionarAcaoBotoes();
        montarTabela();

        add(root);
    }

    private void montarRoot() {
        setTitle("Lista de Requisitos");

        root = new JPanel(new GridBagLayout());
    }

    private void montarBotoes() {
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

        btnDetalhes = new JButton("Mostrar Detalhes");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.PAGE_END;
        c.weightx = 0.5;
        c.gridx = 3;
        c.gridy = 0;
        root.add(btnDetalhes, c);
    }

    private void adicionarAcaoBotoes() {
        btnAdicionar.addActionListener(actionEvent -> AdicionarRequisitoFrame.abrir(this::atualizarTabela, idProjeto));
        btnDetalhes.addActionListener(actionEvent -> mostrarDetalhesRequisito());
        btnExcluir.addActionListener(actionEvent -> excluirRequisito());
        btnEditar.addActionListener(actionEvent -> editarRequisito());
    }

    private void montarTabela() {
        DefaultTableModel modeloDeTabela = criarTituloDasColunasDaTabela();
        criarTabela(modeloDeTabela);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(10,20,0,20);
        c.ipady = 40;
        c.weightx = 0.0;
        c.gridwidth = 4;
        c.gridx = 0;
        c.gridy = 1;
        JPanel painelDaTabela = new JPanel(new BorderLayout());
        painelDaTabela.add(new JScrollPane(tabelaRequisitos), BorderLayout.CENTER);

        root.add(painelDaTabela, c);
    }


    private DefaultTableModel criarTituloDasColunasDaTabela() {
        Object[] columns = {"ID", "Título", "Autor", "Data de Criação", "Funcionalidades"};
        return new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
    }

    private void criarTabela(DefaultTableModel modeloDeTabela) {
        tabelaRequisitos = new JTable(modeloDeTabela);
        tabelaRequisitos.getTableHeader().setFont(new Font("Verdana", Font.BOLD, 13));
        tabelaRequisitos.setRowSorter(new TableRowSorter<TableModel>(modeloDeTabela));
        tabelaRequisitos.setRowHeight(20);
        tabelaRequisitos.setFont(new Font("Verdana", Font.PLAIN, 12));
        tabelaRequisitos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    private void popularTabela() {
        DefaultTableModel modeloTabela = ((DefaultTableModel) tabelaRequisitos.getModel());
        modeloTabela.getDataVector().removeAllElements();
        modeloTabela.fireTableDataChanged();
        ArrayList<Requisito> requisitos = RequisitoHelper.listar(idProjeto);

        if (requisitos != null) {
            for (Requisito requisito : requisitos) {
                Object[] row1 = {requisito.getId(), requisito.getNome(), requisito.getAutor(), requisito.getDataCriacao(), requisito.getFuncionalidades()};
                modeloTabela.insertRow(0, row1);
            }
        }
    }

    private void excluirRequisito() {
        int idRequisito = pegarIdRequisito();
        if (idRequisito == -1) {
            JOptionPane.showMessageDialog(null, "Selecione um requisito primeiro");
            return;
        }

        int confirmado = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir esse requisito?", "Excluir", JOptionPane.YES_NO_OPTION);

        if (confirmado == 0){
            RequisitoHelper.apagar(idRequisito);
            atualizarTabela();
            JOptionPane.showMessageDialog(null, "Requisito excluído!!!");
        }
    }

    private void mostrarDetalhesRequisito(){
        int idRequisito = pegarIdRequisito();
        if (idRequisito == -1) {
            JOptionPane.showMessageDialog(null, "Selecione um requisito primeiro");
            return;
        }

        DetalhesRequisitoFrame.abrir(idRequisito);
    }

    private void editarRequisito() {
        int idRequisito = pegarIdRequisito();
        if (idRequisito == -1) {
            JOptionPane.showMessageDialog(null, "Selecione um requisito primeiro");
            return;
        }

        AdicionarRequisitoFrame.abrirParaEdicao(this::atualizarTabela, idProjeto, idRequisito);
    }

    private void atualizarTabela() {
        DefaultTableModel modeloTabela = ((DefaultTableModel) tabelaRequisitos.getModel());
        modeloTabela.getDataVector().removeAllElements();
        modeloTabela.fireTableDataChanged();

        ArrayList<Requisito> requisitos = RequisitoHelper.listar(idProjeto);

        if (requisitos != null) {
            for (Requisito requisito : requisitos) {
                Object[] row1 = {requisito.getId(), requisito.getNome(), requisito.getAutor(), requisito.getDataCriacao(), requisito.getFuncionalidades()};
                modeloTabela.insertRow(0, row1);
            }
        }
    }

    private int pegarIdRequisito(){
        int column = 0;
        int row = tabelaRequisitos.getSelectedRow();
        if (row == -1){
            return -1;
        } else {
            // Evita pegar a linha errada quando o usuário ordena a coluna
            int realRow = tabelaRequisitos.convertRowIndexToModel(row);
            return Integer.parseInt(tabelaRequisitos.getModel().getValueAt(realRow, column).toString());
        }
    }

    /**
     * Métodos para abrir a tela
     */
    private static ListarRequisitosFrame listarRequisitosFrame;

    private static ListarRequisitosFrame getInstance() {
        if (listarRequisitosFrame == null) {
            listarRequisitosFrame = new ListarRequisitosFrame();
        }
        return listarRequisitosFrame;
    }

    private static void fechar() {
        if (listarRequisitosFrame != null) getInstance().setVisible(false);
        listarRequisitosFrame = null;
    }

    public static void abrir(int idProjeto) {
        getInstance().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getInstance().setSize(1080, 720);
        getInstance().setVisible(true);
        getInstance().setLocationRelativeTo(null);

        getInstance().idProjeto = idProjeto;
        getInstance().popularTabela();

        getInstance().addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                fechar();
            }
        });
    }
}