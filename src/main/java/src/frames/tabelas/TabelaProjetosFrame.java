package src.frames.tabelas;

import src.db.helper.ProjetoHelper;
import src.models.Projeto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.PatternSyntaxException;

public class TabelaProjetosFrame extends JFrame {
    private JTable table;
    private JTable nomes;
    private TableModel model;
    private DefaultTableModel nomesModel;

    public TabelaProjetosFrame() {
        setTitle("Dados dos Projetos");
        Object[] columns = {"Registro do projeto", "Nome do projeto", "Descrição", "Usuário proprietário"};  ///titulo das colunas
        nomesModel = new DefaultTableModel(columns, 0);
        nomes = new JTable(nomesModel);

        ArrayList<Projeto> projetos = ProjetoHelper.listar();

        if (projetos == null) return;

        for (Projeto projeto : projetos) {
            Object[] row1 = {projeto.getId(), projeto.getNome(), projeto.getDescricao(), projeto.getProprietario().getNomeDeUsuario()};
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

        buttonVoltar.addActionListener(e -> setVisible(false));

        buttonAtualizar.addActionListener(e -> {

            ///Limpando a tabela
            DefaultTableModel dm = (DefaultTableModel) table.getModel();
            dm.getDataVector().removeAllElements();
            dm.fireTableDataChanged();

            ArrayList<Projeto> novosProjetos = ProjetoHelper.listar();

            if (novosProjetos == null) return;

            for (Projeto projeto : novosProjetos) {
                Object[] row1 = {projeto.getId(), projeto.getNome(), projeto.getDescricao(), projeto.getProprietario().getNomeDeUsuario()};
                nomesModel.insertRow(0, row1);
            }
        });

        painelBotoes.add(buttonFiltrar);
        painelBotoes.add(buttonAtualizar);
        painelBotoes.add(buttonVoltar);
        add(painelBotoes, BorderLayout.SOUTH);

        setSize(800, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
