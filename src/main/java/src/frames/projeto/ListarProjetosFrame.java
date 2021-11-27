package src.frames.projeto;

import src.db.helper.ProjetoHelper;
import src.frames.requisito.ListarRequisitosFrame;
import src.models.Projeto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.ArrayList;

public class ListarProjetosFrame extends JFrame {
    private JTable tabelaProjetos;
    private JButton btnAdicionar;
    private JButton btnEditar;
    private JButton btnExcluir;
    private JButton btnMostrarRequisitos;
    private JPanel root;
    private final GridBagConstraints c = new GridBagConstraints();

    public ListarProjetosFrame() {
        super("Dados dos Projetos");
        setLayout(new BorderLayout());

        montarRoot();
        montarBotoes();
        adicionarAcaoBotoes();
        montarTabela();

        add(root);
    }


    private void montarRoot() {
        setTitle("Dados dos projetos");

        root = new JPanel(new GridBagLayout());
    }

    private void montarTabela() {
        DefaultTableModel modeloDeTabela = criarTituloDasColunasDaTabela();
        criarTabela(modeloDeTabela);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(10, 20, 0, 20);
        c.ipady = 40;
        c.weightx = 0.0;
        c.gridwidth = 4;
        c.gridx = 0;
        c.gridy = 1;
        JPanel painelDaTabela = new JPanel(new BorderLayout());
        final JTextField filterText = new JTextField("Digite aqui para pesquisar...");
        painelDaTabela.add(filterText, BorderLayout.NORTH);
        painelDaTabela.add(new JScrollPane(tabelaProjetos), BorderLayout.CENTER);

        root.add(painelDaTabela, c);
    }


    private DefaultTableModel criarTituloDasColunasDaTabela() {
        Object[] columns = {"ID", "Título", "Descrição", "Usuário Proprietário", "Email do Proprietário"};
        return new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };
    }

    private void criarTabela(DefaultTableModel modeloDeTabela) {
        ArrayList<Projeto> projetos = ProjetoHelper.listar();

        if (projetos != null) {
            for (Projeto projeto : projetos) {
                Object[] row1 = {projeto.getId(), projeto.getNome(), projeto.getDescricao(), projeto.getProprietario().getNomeDeUsuario(), projeto.getProprietario().getEmail()};
                modeloDeTabela.insertRow(0, row1);
            }
        }

        tabelaProjetos = new JTable(modeloDeTabela);
        tabelaProjetos.getTableHeader().setFont(new Font("Verdana", Font.BOLD, 13));
        tabelaProjetos.setRowSorter(new TableRowSorter<TableModel>(modeloDeTabela));
        tabelaProjetos.setRowHeight(20);
        tabelaProjetos.setFont(new Font("Verdana", Font.PLAIN, 12));
        tabelaProjetos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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

        btnMostrarRequisitos = new JButton("Mostrar Requisitos");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.PAGE_END;
        c.weightx = 0.5;
        c.gridx = 3;
        c.gridy = 0;
        root.add(btnMostrarRequisitos, c);
    }

    private void adicionarAcaoBotoes() {
        btnAdicionar.addActionListener(actionEvent -> AdicionarProjetoFrame.abrir(this::atualizarTabela));
        btnEditar.addActionListener(actionEvent -> editarProjeto());
        btnExcluir.addActionListener(actionEvent -> excluirProjeto());
        btnMostrarRequisitos.addActionListener(actionEvent -> mostrarRequisitos());
    }

    private void atualizarTabela() {
        DefaultTableModel modeloTabela = ((DefaultTableModel) tabelaProjetos.getModel());
        modeloTabela.getDataVector().removeAllElements();
        modeloTabela.fireTableDataChanged();

        ArrayList<Projeto> projetos = ProjetoHelper.listar();

        if (projetos != null) {
            for (Projeto projeto : projetos) {
                Object[] row1 = { projeto.getId(), projeto.getNome(), projeto.getDescricao(), projeto.getProprietario().getNomeDeUsuario(), projeto.getProprietario().getEmail()};
                modeloTabela.insertRow(0, row1);
            }
        }
    }

    private void excluirProjeto() {
        int idProjeto = pegarIdProjeto();
        if (idProjeto == -1) {
            JOptionPane.showMessageDialog(null, "Selecione um projeto primeiro");
            return;
        }

        int confirmado = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir esse projeto?", "Excluir", JOptionPane.YES_NO_OPTION);

        if (confirmado == 0) {
            ProjetoHelper.apagar(idProjeto);
            atualizarTabela();
            JOptionPane.showMessageDialog(null, "Projeto excluído!!!");
        }
    }

    private void mostrarRequisitos() {
        int idProjeto = pegarIdProjeto();
        if (idProjeto == -1) {
            JOptionPane.showMessageDialog(null, "Selecione um projeto primeiro");
            return;
        }

        ListarRequisitosFrame.abrir(idProjeto);
    }

    private void editarProjeto() {
        int idProjeto = pegarIdProjeto();
        if (idProjeto == -1) {
            JOptionPane.showMessageDialog(null, "Selecione um projeto primeiro");
            return;
        }

        AdicionarProjetoFrame.abrirParaEdicao(idProjeto, this::atualizarTabela);
    }

    private int pegarIdProjeto() {
        int column = 0;
        int row = tabelaProjetos.getSelectedRow();
        if (row == -1) {
            return -1;
        } else {
            // Evita pegar a linha errada quando o usuário ordena a coluna
            int realRow = tabelaProjetos.convertRowIndexToModel(row);
            return Integer.parseInt(tabelaProjetos.getModel().getValueAt(realRow, column).toString());
        }
    }

    /**
     * Métodos para abrir a tela
     */
    private static ListarProjetosFrame listarProjetosFrame;

    private static ListarProjetosFrame getInstance() {
        if (listarProjetosFrame == null) {
            listarProjetosFrame = new ListarProjetosFrame();
        }
        return listarProjetosFrame;
    }

    public static void fechar() {
        if (listarProjetosFrame != null) listarProjetosFrame.setVisible(false);
        listarProjetosFrame = null;
    }

    public static void abrir() {
        getInstance().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getInstance().setSize(1080, 720);
        getInstance().setVisible(true);
        getInstance().setLocationRelativeTo(null);
        getInstance().addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                fechar();
            }
        });
    }
}