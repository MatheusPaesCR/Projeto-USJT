package src.frames.requisito;

import src.db.helper.RequisitoHelper;
import src.frames.CallbackAdicionar;
import src.models.Requisito;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class AdicionarRequisitoFrame extends JFrame {
    private JPanel root;
    private JTextField tituloField;
    private JTextField moduloField;
    private JTextField esforcoHorasField;
    private JTextArea funcionalidadesField;
    private JTextArea descricaoField;
    private JComboBox<String> comboBoxPrioridade;
    private JComboBox<String> comboBoxComplexidade;
    private JComboBox<String> comboBoxEstado;
    private JComboBox<String> comboBoxFase;
    private JButton btnCriar;
    private CallbackAdicionar callback;
    private boolean isEditMode = false;
    private int idRequisito;
    private int idProjeto;
    private final GridBagConstraints c = new GridBagConstraints();

    public AdicionarRequisitoFrame() {
        super("");
        setLayout(new BorderLayout());

        montarRoot();
        montarFormulario();
        montarBotoes();
        adicionarAcaoBotoes();

        add(root);
    }

    private void colocarEmModoDeEdicao() {
        isEditMode = true;
        setTitle("Editar Requisito");
        popularFormulario();
    }

    private void montarRoot() {
        setTitle("Adicionar Requisito");

        root = new JPanel(new GridBagLayout());
        root.setBorder(new EmptyBorder(20, 20, 20, 20));
    }

    private void montarFormulario() {
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
        tituloField = new JTextField(1);
        tituloField.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), new EmptyBorder(6, 6, 6, 6)));
        JPanel in1 = new JPanel(new BorderLayout());
        in1.add(tituloField);
        in1.setBorder(new EmptyBorder(25, 10, 25, 0));
        p1.add(nomeLabel, BorderLayout.LINE_START);
        p1.add(in1);

        c.ipady = 20;
        root.add(p1, c);

        JPanel jps1 = new JPanel(new BorderLayout());
        JLabel moduloLabel = new JLabel("Módulo:");
        moduloLabel.setFont(new Font("Verdana", Font.PLAIN, 16));
        moduloField = new JTextField(40);
        moduloField.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), new EmptyBorder(6, 6, 6, 6)));
        JPanel jp1 = new JPanel(new BorderLayout());
        jp1.add(moduloField);
        jp1.setBorder(new EmptyBorder(10, 10, 10, 0));
        jps1.add(moduloLabel, BorderLayout.LINE_START);
        jps1.add(jp1);

        c.ipady = 8;
        c.gridy = 1;
        root.add(jps1, c);

        JPanel jps2 = new JPanel(new BorderLayout());
        JLabel esforcoHorasLabel = new JLabel("Esforço/Horas:");
        esforcoHorasLabel.setFont(new Font("Verdana", Font.PLAIN, 16));
        esforcoHorasField = new JTextField(40);
        esforcoHorasField.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), new EmptyBorder(6, 6, 6, 6)));
        JPanel jp2 = new JPanel(new BorderLayout());
        jp2.add(esforcoHorasField);
        jp2.setBorder(new EmptyBorder(10, 10, 10, 0));
        jps2.add(esforcoHorasLabel, BorderLayout.LINE_START);
        jps2.add(jp2);

        c.ipady = 8;
        c.gridy = 2;
        root.add(jps2, c);

        JPanel p3 = new JPanel(new BorderLayout());
        JLabel funcionalidadesLabel = new JLabel("Funcionalidades:");
        funcionalidadesLabel.setFont(new Font("Verdana", Font.PLAIN, 16));
        funcionalidadesField = new JTextArea(1, 1);
        funcionalidadesField.setWrapStyleWord(true);
        funcionalidadesField.setLineWrap(true);
        funcionalidadesField.setBorder(new EmptyBorder(10, 10, 10, 10));
        JPanel in2 = new JPanel(new BorderLayout());
        in2.add(new JScrollPane(funcionalidadesField));
        in2.setBorder(new EmptyBorder(5, 10, 5, 0));
        p3.add(funcionalidadesLabel, BorderLayout.LINE_START);
        p3.add(in2);

        c.ipady = 40;
        c.gridy = 3;
        root.add(p3, c);

        JPanel p4 = new JPanel(new BorderLayout());
        JLabel descricaoLabel = new JLabel("Descrição:");
        descricaoLabel.setFont(new Font("Verdana", Font.PLAIN, 16));
        descricaoField = new JTextArea(1, 1);
        descricaoField.setWrapStyleWord(true);
        descricaoField.setLineWrap(true);
        descricaoField.setBorder(new EmptyBorder(10, 10, 10, 10));
        JPanel in3 = new JPanel(new BorderLayout());
        in3.add(new JScrollPane(descricaoField));
        in3.setBorder(new EmptyBorder(5, 10, 5, 0));
        p4.add(descricaoLabel, BorderLayout.LINE_START);
        p4.add(in3);

        c.ipady = 40;
        c.gridy = 4;
        root.add(p4, c);

        JPanel p5 = new JPanel(new FlowLayout());

        JLabel prioridadeLabel = new JLabel("Prioridade:");
        prioridadeLabel.setFont(new Font("Verdana", Font.PLAIN, 16));
        p5.add(prioridadeLabel);
        JPanel pi1 = new JPanel();
        comboBoxPrioridade = new JComboBox<>();
        comboBoxPrioridade.addItem(Requisito.getPrioridadeTextByIndex(0));
        comboBoxPrioridade.addItem(Requisito.getPrioridadeTextByIndex(1));
        comboBoxPrioridade.addItem(Requisito.getPrioridadeTextByIndex(2));
        comboBoxPrioridade.addItem(Requisito.getPrioridadeTextByIndex(3));
        comboBoxPrioridade.addItem(Requisito.getPrioridadeTextByIndex(4));
        pi1.add(comboBoxPrioridade);
        pi1.setBorder(new EmptyBorder(10, 0, 0, 0));
        p5.add(pi1);

        JLabel complexidadeLabel = new JLabel("Complexidade:");
        complexidadeLabel.setFont(new Font("Verdana", Font.PLAIN, 16));
        p5.add(complexidadeLabel);
        JPanel pi2 = new JPanel();
        comboBoxComplexidade = new JComboBox<>();
        comboBoxComplexidade.addItem(Requisito.getComplexidadeTextByIndex(0));
        comboBoxComplexidade.addItem(Requisito.getComplexidadeTextByIndex(1));
        comboBoxComplexidade.addItem(Requisito.getComplexidadeTextByIndex(2));
        comboBoxComplexidade.addItem(Requisito.getComplexidadeTextByIndex(3));
        comboBoxComplexidade.addItem(Requisito.getComplexidadeTextByIndex(4));
        pi2.add(comboBoxComplexidade);
        pi2.setBorder(new EmptyBorder(10, 0, 0, 0));
        p5.add(pi2);

        JLabel estadoLabel = new JLabel("Estado:");
        estadoLabel.setFont(new Font("Verdana", Font.PLAIN, 16));
        p5.add(estadoLabel);
        JPanel pi3 = new JPanel();
        comboBoxEstado = new JComboBox<>();
        comboBoxEstado.addItem(Requisito.getEstadoTextByIndex(0));
        comboBoxEstado.addItem(Requisito.getEstadoTextByIndex(1));
        comboBoxEstado.addItem(Requisito.getEstadoTextByIndex(2));
        pi3.add(comboBoxEstado);
        pi3.setBorder(new EmptyBorder(10, 0, 0, 0));
        p5.add(pi3);

        JLabel faseLabel = new JLabel("Fase:");
        faseLabel.setFont(new Font("Verdana", Font.PLAIN, 16));
        p5.add(faseLabel);
        JPanel pi4 = new JPanel();
        comboBoxFase = new JComboBox<>();
        comboBoxFase.addItem(Requisito.getFaseTextByIndex(0));
        comboBoxFase.addItem(Requisito.getFaseTextByIndex(1));
        comboBoxFase.addItem(Requisito.getFaseTextByIndex(2));
        comboBoxFase.addItem(Requisito.getFaseTextByIndex(3));
        pi4.add(comboBoxFase);
        pi4.setBorder(new EmptyBorder(10, 0, 0, 0));
        p5.add(pi4);

        c.ipady = 0;
        c.gridy = 5;
        root.add(p5, c);
    }

    private void montarBotoes() {
        btnCriar = new JButton("Criar");
        c.insets = new Insets(20, 0, 20, 0);
        c.ipady = 30;
        c.gridy = 6;
        root.add(btnCriar, c);
    }

    private void adicionarAcaoBotoes() {
        btnCriar.addActionListener(actionEvent -> {
            if (isEditMode) {
                if (atualizarRequisito()) {
                    JOptionPane.showMessageDialog(null, "Requisito atualizado com sucesso!!!");
                    fechar();
                }
            } else {
                if (criarRequisito()) {
                    JOptionPane.showMessageDialog(null, "Requisito criado com sucesso!!!");
                    fechar();
                }
            }
        });
    }

    private boolean criarRequisito() {
        String titulo = tituloField.getText();
        String modulo = moduloField.getText();
        String esforcoHoras = esforcoHorasField.getText();
        String funcionalidades = funcionalidadesField.getText();
        String descricao = descricaoField.getText();
        int complexidadePos = comboBoxComplexidade.getSelectedIndex();
        Requisito.Complexidade complexidade = Requisito.getComplexidadeByIndex(complexidadePos);
        int prioridadePos = comboBoxPrioridade.getSelectedIndex();
        Requisito.Prioridade prioridade = Requisito.getPrioridadeByIndex(prioridadePos);
        int estadoPos = comboBoxEstado.getSelectedIndex();
        Requisito.Estado estado = Requisito.getEstadoByIndex(estadoPos);
        int fasePos = comboBoxFase.getSelectedIndex();
        Requisito.Fase fase = Requisito.getFaseByIndex(fasePos);

        if (!validarCampos(titulo, modulo, esforcoHoras, funcionalidades, descricao)) return false;
        RequisitoHelper.adicionar(titulo, Integer.parseInt(modulo), funcionalidades, prioridade, complexidade, Integer.parseInt(esforcoHoras), estado, fase, descricao, idProjeto);
        return true;
    }

    private boolean atualizarRequisito() {
        String titulo = tituloField.getText();
        String modulo = moduloField.getText();
        String esforcoHoras = esforcoHorasField.getText();
        String funcionalidades = funcionalidadesField.getText();
        String descricao = descricaoField.getText();
        int complexidadePos = comboBoxComplexidade.getSelectedIndex();
        Requisito.Complexidade complexidade = Requisito.getComplexidadeByIndex(complexidadePos);
        int prioridadePos = comboBoxPrioridade.getSelectedIndex();
        Requisito.Prioridade prioridade = Requisito.getPrioridadeByIndex(prioridadePos);
        int estadoPos = comboBoxEstado.getSelectedIndex();
        Requisito.Estado estado = Requisito.getEstadoByIndex(estadoPos);
        int fasePos = comboBoxFase.getSelectedIndex();
        Requisito.Fase fase = Requisito.getFaseByIndex(fasePos);

        if (!validarCampos(titulo, modulo, esforcoHoras, funcionalidades, descricao)) return false;

        Requisito requisito = RequisitoHelper.pegar(idRequisito);
        if (requisito == null) return false;
        requisito.setNome(titulo);
        requisito.setModulo(Integer.parseInt(modulo));
        requisito.setEsforcoHoras(Integer.parseInt(esforcoHoras));
        requisito.setFuncionalidades(funcionalidades);
        requisito.setDescricao(descricao);
        requisito.setComplexidade(complexidade);
        requisito.setPrioridade(prioridade);
        requisito.setEstado(estado);
        requisito.setFase(fase);

        RequisitoHelper.atualizar(requisito);
        return true;
    }

    private boolean validarCampos(String titulo, String modulo, String esforcoHoras, String funcionalidades, String descricao) {
        if (titulo.equals("")) {
            JOptionPane.showMessageDialog(null, "Digite um nome para o Requisito");
            return false;
        }

        if (modulo.equals("")) {
            JOptionPane.showMessageDialog(null, "Digite um módulo para o Requisito");
            return false;
        }

        if (!isInteiro(modulo)) {
            JOptionPane.showMessageDialog(null, "Digite um número inteiro para o campo módulo!");
            return false;
        }

        if (esforcoHoras.equals("")) {
            JOptionPane.showMessageDialog(null, "Digite uma quantidade de esforço em horas para o Requisito");
            return false;
        }

        if (!isInteiro(esforcoHoras)) {
            JOptionPane.showMessageDialog(null, "Digite um número inteiro para o campo esforço em horas!");
            return false;
        }

        if (funcionalidades.equals("")) {
            JOptionPane.showMessageDialog(null, "Digite uma funcionalidade para o Requisito");
            return false;
        }

        if (descricao.equals("")) {
            JOptionPane.showMessageDialog(null, "Digite uma descrição para o Requisito");
            return false;
        }

        return true;
    }

    private void popularFormulario() {
        Requisito requisito = RequisitoHelper.pegar(idRequisito);
        if (requisito != null) {
            tituloField.setText(requisito.getNome());
            moduloField.setText(String.valueOf(requisito.getModulo()));
            esforcoHorasField.setText(String.valueOf(requisito.getEsforcoHoras()));
            funcionalidadesField.setText(requisito.getFuncionalidades());
            descricaoField.setText(requisito.getDescricao());
            comboBoxComplexidade.setSelectedIndex(Requisito.getComplexidadeIndex(requisito.getComplexidade()));
            comboBoxPrioridade.setSelectedIndex(Requisito.getPrioridadeIndex(requisito.getPrioridade()));
            comboBoxEstado.setSelectedIndex(Requisito.getEstadoIndex(requisito.getEstado()));
            comboBoxFase.setSelectedIndex(Requisito.getFaseIndex(requisito.getFase()));

            btnCriar.setText("Atualizar");
        }
    }

    /**
     * Métodos para abrir a tela
     */
    private static AdicionarRequisitoFrame adicionarRequisitoFrame;

    private static AdicionarRequisitoFrame getInstance() {
        if (adicionarRequisitoFrame == null) {
            adicionarRequisitoFrame = new AdicionarRequisitoFrame();
        }
        return adicionarRequisitoFrame;
    }

    public static void fechar() {
        if (adicionarRequisitoFrame != null) {
            getInstance().setVisible(false);
            getInstance().callback.invoke();
        }
        adicionarRequisitoFrame = null;
    }

    private static void fecharSemCallback() {
        if (adicionarRequisitoFrame != null) {
            getInstance().setVisible(false);
        }
        adicionarRequisitoFrame = null;
    }

    public static void abrir(CallbackAdicionar callback, int idProjeto) {
        getInstance().callback = callback;
        getInstance().idProjeto = idProjeto;
        getInstance().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getInstance().setSize(1080, 720);
        getInstance().setVisible(true);
        getInstance().setLocationRelativeTo(null);
        getInstance().addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                fecharSemCallback();
            }
        });
    }

    public static void abrirParaEdicao(CallbackAdicionar callback, int idProjeto, int idRequisito) {
        getInstance().idRequisito = idRequisito;
        abrir(callback, idProjeto);
        getInstance().colocarEmModoDeEdicao();
    }

    public static boolean isInteiro(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
}
