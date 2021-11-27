package src.frames.requisito;

import src.db.helper.ProjetoHelper;
import src.db.helper.RequisitoHelper;
import src.models.Projeto;
import src.models.Requisito;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class DetalhesRequisitoFrame extends JFrame {
    private JPanel root;
    private int idRequisito;
    private final GridBagConstraints c = new GridBagConstraints();

    public DetalhesRequisitoFrame() {
        super("");
    }

    public void iniciar(){
        setLayout(new BorderLayout());

        montarRoot();
        montarTela();

        add(new JScrollPane(root));
    }

    private void montarRoot() {
        setTitle("Detalhes do Requisito");

        root = new JPanel(new GridBagLayout());
        root.setBorder(new EmptyBorder(20, 20, 20, 20));
    }

    private void montarTela() {
        Requisito req = RequisitoHelper.pegar(idRequisito);
        if (req == null) return;
        Projeto projeto = ProjetoHelper.pegar(req.getIdProjeto());
        if (projeto == null) return;

        c.ipadx = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.NORTH;
        c.weightx = 0.0;
        c.ipady = 0;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;

        JPanel p1 = new JPanel(new BorderLayout());
        JLabel nomeLabel = new JLabel("Título:");
        nomeLabel.setFont(new Font("Verdana", Font.PLAIN, 16));
        JTextField tituloField = new JTextField(1);
        tituloField.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), new EmptyBorder(6, 6, 6, 6)));
        tituloField.setEditable(false);
        JPanel in1 = new JPanel(new BorderLayout());
        in1.add(tituloField);
        in1.setBorder(new EmptyBorder(25, 10, 25, 0));
        p1.add(nomeLabel, BorderLayout.LINE_START);
        p1.add(in1);
        tituloField.setText(req.getNome());

        c.ipady = 8;
        root.add(p1, c);

        JPanel jps1 = new JPanel(new BorderLayout());
        JLabel moduloLabel = new JLabel("Módulo:");
        moduloLabel.setFont(new Font("Verdana", Font.PLAIN, 16));
        JTextField moduloField = new JTextField(40);
        moduloField.setEditable(false);
        moduloField.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), new EmptyBorder(6, 6, 6, 6)));
        JPanel jp1 = new JPanel(new BorderLayout());
        jp1.add(moduloField);
        jp1.setBorder(new EmptyBorder(10, 10, 10, 0));
        jps1.add(moduloLabel, BorderLayout.LINE_START);
        jps1.add(jp1);
        moduloField.setText(String.valueOf(req.getModulo()));

        c.ipady = 8;
        c.gridy = 1;
        root.add(jps1, c);

        JPanel jps2 = new JPanel(new BorderLayout());
        JLabel esforcoHorasLabel = new JLabel("Esforço/Horas:");
        esforcoHorasLabel.setFont(new Font("Verdana", Font.PLAIN, 16));
        JTextField esforcoHorasField = new JTextField(40);
        esforcoHorasField.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), new EmptyBorder(6, 6, 6, 6)));
        esforcoHorasField.setEditable(false);
        JPanel jp2 = new JPanel(new BorderLayout());
        jp2.add(esforcoHorasField);
        jp2.setBorder(new EmptyBorder(10, 10, 10, 0));
        jps2.add(esforcoHorasLabel, BorderLayout.LINE_START);
        jps2.add(jp2);
        esforcoHorasField.setText(String.valueOf(req.getEsforcoHoras()));

        c.ipady = 8;
        c.gridy = 2;
        root.add(jps2, c);

        JPanel p3 = new JPanel(new BorderLayout());
        JLabel funcionalidadesLabel = new JLabel("Funcionalidades:");
        funcionalidadesLabel.setFont(new Font("Verdana", Font.PLAIN, 16));
        JTextArea funcionalidadesField = new JTextArea(1, 1);
        funcionalidadesField.setWrapStyleWord(true);
        funcionalidadesField.setLineWrap(true);
        funcionalidadesField.setBorder(new EmptyBorder(10, 10, 10, 10));
        funcionalidadesField.setEditable(false);
        JPanel in2 = new JPanel(new BorderLayout());
        in2.add(new JScrollPane(funcionalidadesField));
        in2.setBorder(new EmptyBorder(5, 10, 5, 0));
        p3.add(funcionalidadesLabel, BorderLayout.LINE_START);
        p3.add(in2);
        funcionalidadesField.setText(req.getFuncionalidades());

        c.ipady = 40;
        c.gridy = 3;
        root.add(p3, c);

        JPanel p4 = new JPanel(new BorderLayout());
        JLabel descricaoLabel = new JLabel("Descrição:");
        descricaoLabel.setFont(new Font("Verdana", Font.PLAIN, 16));
        JTextArea descricaoField = new JTextArea(1, 1);
        descricaoField.setWrapStyleWord(true);
        descricaoField.setLineWrap(true);
        descricaoField.setBorder(new EmptyBorder(10, 10, 10, 10));
        descricaoField.setEditable(false);
        JPanel in3 = new JPanel(new BorderLayout());
        in3.add(new JScrollPane(descricaoField));
        in3.setBorder(new EmptyBorder(5, 10, 5, 0));
        p4.add(descricaoLabel, BorderLayout.LINE_START);
        p4.add(in3);
        descricaoField.setText(req.getDescricao());

        c.ipady = 40;
        c.gridy = 4;
        root.add(p4, c);

        JPanel p5 = new JPanel(new FlowLayout());

        JLabel prioridadeLabel = new JLabel("Prioridade:");
        prioridadeLabel.setFont(new Font("Verdana", Font.PLAIN, 16));
        p5.add(prioridadeLabel);
        JPanel pi1 = new JPanel();
        JComboBox<String> comboBoxPrioridade = new JComboBox<>();
        comboBoxPrioridade.addItem(Requisito.getPrioridadeText(req.getPrioridade()));
        comboBoxPrioridade.setEditable(false);
        pi1.add(comboBoxPrioridade);
        pi1.setBorder(new EmptyBorder(10, 0, 0, 0));
        p5.add(pi1);

        JLabel complexidadeLabel = new JLabel("Complexidade:");
        complexidadeLabel.setFont(new Font("Verdana", Font.PLAIN, 16));
        p5.add(complexidadeLabel);
        JPanel pi2 = new JPanel();
        JComboBox<String> comboBoxComplexidade = new JComboBox<>();
        comboBoxComplexidade.addItem(Requisito.getComplexidadeText(req.getComplexidade()));
        comboBoxComplexidade.setEditable(false);
        pi2.add(comboBoxComplexidade);
        pi2.setBorder(new EmptyBorder(10, 0, 0, 0));
        p5.add(pi2);

        JLabel estadoLabel = new JLabel("Estado:");
        estadoLabel.setFont(new Font("Verdana", Font.PLAIN, 16));
        p5.add(estadoLabel);
        JPanel pi3 = new JPanel();
        JComboBox<String> comboBoxEstado = new JComboBox<>();
        comboBoxEstado.addItem(Requisito.getEstadoText(req.getEstado()));
        comboBoxEstado.setEditable(false);
        pi3.add(comboBoxEstado);
        pi3.setBorder(new EmptyBorder(10, 0, 0, 0));
        p5.add(pi3);

        JLabel faseLabel = new JLabel("Fase:");
        faseLabel.setFont(new Font("Verdana", Font.PLAIN, 16));
        p5.add(faseLabel);
        JPanel pi4 = new JPanel();
        JComboBox<String> comboBoxFase = new JComboBox<>();
        comboBoxFase.addItem(Requisito.getFaseText(req.getFase()));
        comboBoxFase.setEditable(false);
        pi4.add(comboBoxFase);
        pi4.setBorder(new EmptyBorder(10, 0, 0, 0));
        p5.add(pi4);

        c.ipady = 0;
        c.gridy = 5;
        root.add(p5, c);

        JPanel p6 = new JPanel(new BorderLayout());
        JLabel dataCriacaoLabel = new JLabel("Data de criação:");
        dataCriacaoLabel.setFont(new Font("Verdana", Font.PLAIN, 16));
        JTextField dataCriacaoField = new JTextField(1);
        dataCriacaoField.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), new EmptyBorder(6, 6, 6, 6)));
        dataCriacaoField.setEditable(false);
        JPanel in6 = new JPanel(new BorderLayout());
        in6.add(dataCriacaoField);
        in6.setBorder(new EmptyBorder(25, 10, 25, 0));
        p6.add(dataCriacaoLabel, BorderLayout.LINE_START);
        p6.add(in6);
        dataCriacaoField.setText(req.getDataCriacao().toString());

        c.ipady = 8;
        c.gridy = 6;
        root.add(p6, c);

        JPanel p7 = new JPanel(new BorderLayout());
        JLabel dataUltimaAlteracaoLabel = new JLabel("Data última alteração:");
        dataUltimaAlteracaoLabel.setFont(new Font("Verdana", Font.PLAIN, 16));
        JTextField dataUltimaAlteracaoField = new JTextField(1);
        dataUltimaAlteracaoField.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), new EmptyBorder(6, 6, 6, 6)));
        dataUltimaAlteracaoField.setEditable(false);
        JPanel in7 = new JPanel(new BorderLayout());
        in7.add(dataUltimaAlteracaoField);
        in7.setBorder(new EmptyBorder(25, 10, 25, 0));
        p7.add(dataUltimaAlteracaoLabel, BorderLayout.LINE_START);
        p7.add(in7);
        dataUltimaAlteracaoField.setText(req.getDataUltimaAlteracao().toString());

        c.ipady = 8;
        c.gridy = 7;
        root.add(p7, c);

        JPanel p8 = new JPanel(new BorderLayout());
        JLabel versaoLabel = new JLabel("Versão:");
        versaoLabel.setFont(new Font("Verdana", Font.PLAIN, 16));
        JTextField versaoField = new JTextField(1);
        versaoField.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), new EmptyBorder(6, 6, 6, 6)));
        versaoField.setEditable(false);
        JPanel in8 = new JPanel(new BorderLayout());
        in8.add(versaoField);
        in8.setBorder(new EmptyBorder(25, 10, 25, 0));
        p8.add(versaoLabel, BorderLayout.LINE_START);
        p8.add(in8);
        versaoField.setText(String.valueOf(req.getVersao()));

        c.ipady = 8;
        c.gridy = 8;
        root.add(p8, c);

        JPanel p9 = new JPanel(new BorderLayout());
        JLabel nomeProjetoLabel = new JLabel("Nome do Projeto:");
        nomeProjetoLabel.setFont(new Font("Verdana", Font.PLAIN, 16));
        JTextField nomeProjetolField = new JTextField(1);
        nomeProjetolField.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), new EmptyBorder(6, 6, 6, 6)));
        nomeProjetolField.setEditable(false);
        JPanel in9 = new JPanel(new BorderLayout());
        in9.add(nomeProjetolField);
        in9.setBorder(new EmptyBorder(25, 10, 25, 0));
        p9.add(nomeProjetoLabel, BorderLayout.LINE_START);
        p9.add(in9);
        nomeProjetolField.setText(projeto.getNome());

        c.ipady = 8;
        c.gridy = 9;
        root.add(p9, c);

        JPanel p10 = new JPanel(new BorderLayout());
        JLabel autorLabel = new JLabel("Autor:");
        autorLabel.setFont(new Font("Verdana", Font.PLAIN, 16));
        JTextField autorField = new JTextField(1);
        autorField.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), new EmptyBorder(6, 6, 6, 6)));
        autorField.setEditable(false);
        JPanel in10 = new JPanel(new BorderLayout());
        in10.add(autorField);
        in10.setBorder(new EmptyBorder(25, 10, 25, 0));
        p10.add(autorLabel, BorderLayout.LINE_START);
        p10.add(in10);
        autorField.setText(req.getAutor());

        c.ipady = 8;
        c.gridy = 10;
        root.add(p10, c);

        JPanel p11 = new JPanel(new BorderLayout());
        JLabel autorUltimaAlteracaoLabel = new JLabel("Autor da última alteração:");
        autorUltimaAlteracaoLabel.setFont(new Font("Verdana", Font.PLAIN, 16));
        JTextField autorUltimaAlteracaoField = new JTextField(1);
        autorUltimaAlteracaoField.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), new EmptyBorder(6, 6, 6, 6)));
        autorUltimaAlteracaoField.setEditable(false);
        JPanel in11 = new JPanel(new BorderLayout());
        in11.add(autorUltimaAlteracaoField);
        in11.setBorder(new EmptyBorder(25, 10, 25, 0));
        p11.add(autorUltimaAlteracaoLabel, BorderLayout.LINE_START);
        p11.add(in11);
        autorUltimaAlteracaoField.setText(req.getAutorUltimaAlteracao());

        c.ipady = 8;
        c.gridy = 11;
        root.add(p11, c);
    }

    /**
     * Métodos para abrir a tela
     */
    private static DetalhesRequisitoFrame adicionarRequisitoFrame;

    private static DetalhesRequisitoFrame getInstance() {
        if (adicionarRequisitoFrame == null) {
            adicionarRequisitoFrame = new DetalhesRequisitoFrame();
        }
        return adicionarRequisitoFrame;
    }

    private static void fechar() {
        if (adicionarRequisitoFrame != null) getInstance().setVisible(false);
        adicionarRequisitoFrame = null;
    }

    public static void abrir(int idRequisito) {
        getInstance().idRequisito = idRequisito;
        getInstance().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getInstance().setSize(1080, 720);
        getInstance().setVisible(true);
        getInstance().setLocationRelativeTo(null);
        getInstance().iniciar();
        getInstance().addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                fechar();
            }
        });
    }
}
