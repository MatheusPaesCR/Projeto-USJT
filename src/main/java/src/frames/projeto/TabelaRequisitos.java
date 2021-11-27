package src.frames.projeto;
import src.models.Projeto;
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

public class TabelaRequisitos extends  JFrame{

    private JTable table;
    private JTable nomes;
    private TableModel model;
    private DefaultTableModel nomesModel;

    public TabelaRequisitos()
    {
        setTitle("Dados dos Requisitos");
        Object columns[] = {"Nome", "Modulo", "Funcionalidades", "Data de criação","Autor","Ultima alteração","Autor da alteração","Versão","prioridade","complexidade","Esforço em horas","Estado","Fase","Descrição"};  ///titulo das colunas
        nomesModel = new DefaultTableModel(columns, 15);
        nomes = new JTable(nomesModel);


        String dados = "";
        String dados2 = "";


        Requisito requisito = new Requisito();
        Requisito auxiliar = new Requisito();
        ArrayList<Requisito> lista = requisito.listar();


        for (int i = 0; i < lista.size(); i++) {
            requisito = lista.get(i);

            Object[] row1 = {requisito.getNome(),requisito.getModulo(),requisito.getFuncionalidades(),requisito.getDataCriacao(),requisito.getAutor(),requisito.getDataUltimaAlteracao(),requisito.getAutorUltimaAlteracao(),requisito.getVersao(),requisito.getPrioridade(),requisito.getComplexidade(),requisito.getEsforcoHoras(),requisito.getEstado(),requisito.getFase(),requisito.getDescricao()};
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

        buttonVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        buttonAtualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                ///Limpando a tabela
                DefaultTableModel dm = (DefaultTableModel) table.getModel();
                dm.getDataVector().removeAllElements();
                dm.fireTableDataChanged();

                //Aqui parei de limpar


                String dados = "";
                String dados2 = "";

                Requisito requisito = new Requisito();
                Requisito auxiliar = new Requisito();
                ArrayList<Requisito> lista = requisito.listar();


                for (int i = 0; i < lista.size(); i++) {
                    requisito = lista.get(i);

                    Object[] row1 = {requisito.getNome(),requisito.getModulo(),requisito.getFuncionalidades(),requisito.getDataCriacao(),requisito.getAutor(),requisito.getDataUltimaAlteracao(),requisito.getAutorUltimaAlteracao(),requisito.getVersao(),requisito.getPrioridade(),requisito.getComplexidade(),requisito.getEsforcoHoras(),requisito.getEstado(),requisito.getFase(),requisito.getDescricao()};
                    nomesModel.insertRow(0, row1);

                }
            }
        });


        painelBotoes.add(buttonFiltrar);
        painelBotoes.add(buttonAtualizar);
        painelBotoes.add(buttonVoltar);
        add(painelBotoes, BorderLayout.SOUTH);

        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);


    }
}