package src.frames.tabelas;

import src.db.helper.RequisitoHelper;
import src.models.Requisito;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.PatternSyntaxException;

public class TabelaRequisitosFrame extends JFrame {

    private JTable table;
    private JTable nomes;
    private TableModel model;
    private DefaultTableModel nomesModel;

    public TabelaRequisitosFrame() {
        setTitle("Dados dos Requisitos");
        Object[] columns = {"Nome", "Modulo", "Funcionalidades", "Data de criação", "Autor", "Ultima alteração", "Autor da alteração", "Versão", "prioridade", "complexidade", "Esforço em horas", "Estado", "Fase", "Descrição"};  ///titulo das colunas
        nomesModel = new DefaultTableModel(columns, 0);
        nomes = new JTable(nomesModel);

        ArrayList<Requisito> requisitos = RequisitoHelper.listar();
        if (requisitos == null) return;

        for (Requisito requisito : requisitos) {
            Object[] row1 = {requisito.getNome(), requisito.getModulo(), requisito.getFuncionalidades(), requisito.getDataCriacao(), requisito.getAutor(), requisito.getDataUltimaAlteracao(), requisito.getAutorUltimaAlteracao(), requisito.getVersao(), Requisito.getPrioridadeText(requisito.getPrioridade()), Requisito.getComplexidadeText(requisito.getComplexidade()), requisito.getEsforcoHoras(), Requisito.getEstadoText(requisito.getEstado()), Requisito.getFaseText(requisito.getFase()), requisito.getDescricao()};
            nomesModel.insertRow(0, row1);
        }

        table = new JTable(nomesModel); //JTable
        final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(nomesModel);
        table.setRowSorter(sorter);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("Filtro");
        panel.add(label, BorderLayout.WEST);

        final JTextField filterText = new JTextField("");

        panel.add(filterText, BorderLayout.CENTER);
        add(panel, BorderLayout.NORTH);
        ///---------------------Botões
        JPanel painelBotoes = new JPanel();  //criando o painel para os botoes
        painelBotoes.setLayout(new FlowLayout());

        JButton buttonVoltar = new JButton("Voltar");
        JButton buttonFiltrar = new JButton("Filtrar");
        JButton buttonAtualizar = new JButton("Atualizar");

        //Adicionando ação para o botão filtrar
        buttonFiltrar.addActionListener(e -> {
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
        });

        buttonVoltar.addActionListener(e -> setVisible(false));

        buttonAtualizar.addActionListener(e -> {

            DefaultTableModel dm = (DefaultTableModel) table.getModel();
            dm.getDataVector().removeAllElements();
            dm.fireTableDataChanged();

            ArrayList<Requisito> novosRequisitos = RequisitoHelper.listar();
            if (novosRequisitos == null) return;

            for (Requisito requisito : novosRequisitos) {
                Object[] row1 = {requisito.getNome(), requisito.getModulo(), requisito.getFuncionalidades(), requisito.getDataCriacao(), requisito.getAutor(), requisito.getDataUltimaAlteracao(), requisito.getAutorUltimaAlteracao(), requisito.getVersao(), Requisito.getPrioridadeText(requisito.getPrioridade()), Requisito.getComplexidadeText(requisito.getComplexidade()), requisito.getEsforcoHoras(), Requisito.getEstadoText(requisito.getEstado()), Requisito.getFaseText(requisito.getFase()), requisito.getDescricao()};
                nomesModel.insertRow(0, row1);
            }
        });

        painelBotoes.add(buttonFiltrar);
        painelBotoes.add(buttonAtualizar);
        painelBotoes.add(buttonVoltar);
        add(painelBotoes, BorderLayout.SOUTH);

        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

}