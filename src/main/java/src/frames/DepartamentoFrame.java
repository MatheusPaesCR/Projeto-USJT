package src.frames;

import src.frames.projeto.ProjetoMainFrame;
import src.frames.projeto.TabelaProjetos;
import src.frames.projeto.TabelaRequisitos;
import src.main.Principal;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DepartamentoFrame extends JFrame {
    //Criando construtor
    public DepartamentoFrame() {       //aqui o titulo da janela "gerenciador do departamento"
        super("GERENCIADOR DO DEPARTAMENTO");
        criarMenu();//Chamando metodo criar menu pelo construtor
    }

    private void criarMenu()//private para só ser acessado por essa classe
    {
        GerenciarUsuariosAction gerenciarUsuariosAction = new GerenciarUsuariosAction(); //criando um objeto para executar as ações dos JComboBox's
        GerenciarProjetosAction gerenciarProjetosAction = new GerenciarProjetosAction();
        GerenciarRequisitosAction gerenciarRequisitosAction = new GerenciarRequisitosAction();
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
        JMenuItem comboBoxAlterar = new JMenuItem("Requisitos");
        comboBoxAlterar.addActionListener(gerenciarRequisitosAction);///Ação
        comboBox.add(comboBoxAlterar);


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

    //Editar
    private class GerenciarUsuariosAction implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            Principal principal = new Principal();
            principal.adicionarUsuario(0);

        }
    }

    private class GerenciarProjetosAction implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            ProjetoMainFrame.abrir();
        }
    }

    private class GerenciarRequisitosAction implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            JOptionPane.showMessageDialog(null, "Alterar");
        }
    }

    //Exibir
    private class ExibirUsuaiosAction implements ActionListener {
        public void actionPerformed(ActionEvent event) {

            new TabelaComFiltro();
        }
    }

    private class ExibirProjetosAction implements ActionListener {
        public void actionPerformed(ActionEvent event) {

            new TabelaProjetos();
        }
    }

    private class ExibirRequisitosAction implements ActionListener {
        public void actionPerformed(ActionEvent event) {

            new TabelaRequisitos();

        }
    }
}