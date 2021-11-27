package src.frames;

import com.sun.tools.javac.Main;
import src.frames.projeto.ListarProjetosFrame;
import src.frames.requisito.ListarRequisitosFrame;
import src.frames.tabelas.TabelaProjetosFrame;
import src.frames.tabelas.TabelaRequisitosFrame;
import src.frames.tabelas.TabelaUsuariosFrame;
import src.frames.usuario.ListarUsuarioFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    public MainFrame() {
        super("GERENCIADOR DO DEPARTAMENTO");
        criarMenu();
    }

    private void criarMenu() {
        GerenciarUsuariosAction gerenciarUsuariosAction = new GerenciarUsuariosAction(); //criando um objeto para executar as ações dos JComboBox's
        GerenciarProjetosAction gerenciarProjetosAction = new GerenciarProjetosAction();
        ExibirUsuaiosAction exibirUsuaiosAction = new ExibirUsuaiosAction();
        ExibirProjetosAction exibirProjetosAction = new ExibirProjetosAction();
        ExibirRequisitosAction exibirRequisitosAction = new ExibirRequisitosAction();

        //criando JcomboBox
        JMenu comboBox = new JMenu("Editar");//aqui eu crio um menu que tera itens no JComboBox

        JMenuItem comboBoxAdicionar = new JMenuItem("Usuários");//Aqui estou criando uma opção no JComboBox
        comboBoxAdicionar.addActionListener(gerenciarUsuariosAction); //Ação    --Agora estou tornando acessivel a ação pós item selecionado
        comboBox.add(comboBoxAdicionar);//Aqui estou adicionando uma opção no combobox
        JMenuItem comboBoxExcluir = new JMenuItem("Projetos");
        comboBoxExcluir.addActionListener(gerenciarProjetosAction);//Ação
        comboBox.add(comboBoxExcluir);

        JMenu exibir = new JMenu("Exibir");//Criando outra opção e itens para a barra de menu

        JMenuItem exibirUsuarios = new JMenuItem("Usuários");
        exibirUsuarios.addActionListener(exibirUsuaiosAction);//Ação
        exibir.add(exibirUsuarios);
        JMenuItem exibirProjetos = new JMenuItem("Projetos");
        exibirProjetos.addActionListener(exibirProjetosAction);//Ação
        exibir.add(exibirProjetos);
        JMenuItem exibirRequisitos = new JMenuItem("Requisitos");
        exibirRequisitos.addActionListener(exibirRequisitosAction);//Ação
        exibir.add(exibirRequisitos);
        //Criando barra que abriga todos os itens acima:
        JMenuBar barra = new JMenuBar();
        setJMenuBar(barra);
        barra.add(comboBox);
        barra.add(exibir);
    }

    //Classes que criam Objetos que executam as ações do itensMenus

    private static class GerenciarUsuariosAction implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            ListarUsuarioFrame.abrir();
        }
    }

    private static class GerenciarProjetosAction implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            ListarProjetosFrame.abrir();
        }
    }

    private static class ExibirUsuaiosAction implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            new TabelaUsuariosFrame();
        }
    }

    private static class ExibirProjetosAction implements ActionListener {
        public void actionPerformed(ActionEvent event) {

            new TabelaProjetosFrame();
        }
    }

    private static class ExibirRequisitosAction implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            new TabelaRequisitosFrame();
        }
    }

    /**
     * Métodos para abrir a tela
     */
    private static MainFrame mainFrame;

    private static MainFrame getInstance() {
        if (mainFrame == null) {
            mainFrame = new MainFrame();
        }
        return mainFrame;
    }

    public static void abrir() {
        getInstance().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getInstance().setSize(1280, 720);
        getInstance().setVisible(true);
        getInstance().setLocationRelativeTo(null);
    }
}