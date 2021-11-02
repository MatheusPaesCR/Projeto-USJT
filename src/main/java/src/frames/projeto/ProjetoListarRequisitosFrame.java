package src.frames.projeto;

import src.db.helper.RequisitoHelper;
import src.models.Requisito;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.ArrayList;

public class ProjetoListarRequisitosFrame extends JFrame {
    private JTable tabelaRequisitos;
    private JButton btnAdicionar;
    private JPanel root;
    private int idProjeto;
    private final GridBagConstraints c = new GridBagConstraints();

    public ProjetoListarRequisitosFrame() {
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
        c.insets = new Insets(20,20,0,20);
        c.ipady = 40;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.NORTH;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 1;
        root.add(btnAdicionar, c);
    }

    private void adicionarAcaoBotoes() {

    }

    private void montarTabela() {
        DefaultTableModel modeloDeTabela = criarTituloDasColunasDaTabela();
        criarTabela(modeloDeTabela);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(10,20,0,20);
        c.ipady = 40;
        c.weightx = 0.0;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 0;
        JPanel painelDaTabela = new JPanel(new BorderLayout());
        painelDaTabela.add(new JScrollPane(tabelaRequisitos), BorderLayout.CENTER);

        root.add(painelDaTabela, c);
    }


    private DefaultTableModel criarTituloDasColunasDaTabela() {
        Object[] columns = {"ID", "Título", "Módulo", "Funcionalidades", "Data de Criação", "Data da Última Alteração", "Versão", "Prioridade", "Complexidade", "Esforço em Horas", "Estado", "Fase", "Descrição", "ID Projeto", "Autor", "Autor da Última Modificação"};
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
        tabelaRequisitos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    }

    private void popularTabela() {
        DefaultTableModel modeloTabela = ((DefaultTableModel) tabelaRequisitos.getModel());
        modeloTabela.getDataVector().removeAllElements();
        modeloTabela.fireTableDataChanged();
        ArrayList<Requisito> requisitos = RequisitoHelper.listar(idProjeto);

        if (requisitos != null) {
            for (Requisito requisito : requisitos) {
                Object[] row1 = {requisito.getId(), requisito.getNome(), requisito.getModulo(), requisito.getFuncionalidades(), requisito.getDataCriacao(), requisito.getDataUltimaAlteracao(), requisito.getVersao(), requisito.getPrioridade(), requisito.getComplexidade(), requisito.getEsforcoHoras(), requisito.getEstado(), requisito.getFase(), requisito.getDescricao(), requisito.getIdProjeto(), requisito.getIdAutor(), requisito.getIdAutorUltimaAlteracao()};
                modeloTabela.insertRow(0, row1);
            }
        }
    }

    /**
     * Métodos para abrir a tela
     */
    private static ProjetoListarRequisitosFrame projetoListarRequisitosFrame;

    private static ProjetoListarRequisitosFrame getInstance() {
        if (projetoListarRequisitosFrame == null) {
            projetoListarRequisitosFrame = new ProjetoListarRequisitosFrame();
        }
        return projetoListarRequisitosFrame;
    }

    public static void fechar() {
        getInstance().setVisible(false);
        projetoListarRequisitosFrame = null;
    }

    public static void abrir(int idProjeto) {
        getInstance().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getInstance().setSize(1080, 720);
        getInstance().setVisible(true);
        getInstance().setLocationRelativeTo(null);

        getInstance().idProjeto = idProjeto;
        getInstance().popularTabela();
    }
}