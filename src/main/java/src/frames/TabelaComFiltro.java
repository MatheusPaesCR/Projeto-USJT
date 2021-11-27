package src.frames;

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


public class TabelaComFiltro extends JFrame {
    private JTable table;
    private JTable nomes;
    private TableModel model;
    private DefaultTableModel nomesModel;

    public TabelaComFiltro() {
        setTitle("Dados dos usuários");
        Object columns[] = {"Nome Completo", "Login", "Senha", "Email" ,"Número do celular"};  ///titulo das colunas
        nomesModel = new DefaultTableModel(columns, 6);
        nomes = new JTable(nomesModel);


        String dados = "";
        String dados2 = "";
        Usuario usuario = new Usuario();
        Usuario auxiliar = new Usuario();
        ArrayList<Usuario> lista = Usuario.listar();


        for (int i = 0; i < lista.size(); i++) {
            usuario = lista.get(i);

            Object[] row1 = {usuario.getNomeCompleto(),  usuario.getNomeDeUsuario(), usuario.getSenha(),usuario.getEmail(), usuario.getTelefone()};
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
                Usuario usuario = new Usuario();
                ArrayList<Usuario> lista = Usuario.listar();


                for (int i = 0; i < lista.size(); i++) {
                    usuario = lista.get(i);

                    Object[] row1 = {usuario.getNomeCompleto(),  usuario.getNomeDeUsuario(), usuario.getSenha(),usuario.getEmail(), usuario.getTelefone()};
                    nomesModel.insertRow(0, row1);
                }
            }
        });

        painelBotoes.add(buttonFiltrar);
        painelBotoes.add(buttonAtualizar);
        painelBotoes.add(buttonVoltar);
        add(painelBotoes, BorderLayout.SOUTH);

        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}