
package View.View_Professor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.IOException;
import java.lang.reflect.Array;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Controller.adminController;
import Controller.professorController;
import Controller.reports.report_minhas_turmas;
import Controller.reports.report_turma_ensinada;
import Model.cadeira;
import Model.notificacao;
import Model.sala;
import Model.sessao;
import Model.turma;
import View.botao_redondo;

public class painel_Professor extends JPanel{
    private sessao sessao;
    public painel_Professor(sessao sessao) throws IOException{
        JLabel titulo = new JLabel("Opções");
        this.sessao = sessao;
        botao_redondo botao_turmas_atuais = new botao_redondo("Visualizar minhas turmas");
        botao_redondo botao_autorizar_matricula = new botao_redondo("Autorizar matrícula");
        botao_redondo botao_chamada = new botao_redondo("Gerar histórico de matrícula");
        
        botao_redondo[] botoes = {botao_turmas_atuais, botao_autorizar_matricula, botao_chamada};

        botao_turmas_atuais.addActionListener(new report_turma_ensinada(sessao));

        configPainel();
        adicionaComponentes(titulo, botoes );

        // botao_autorizar_matricula.addActionListener(e -> {
        //     try {
        //         this.painelAutorizar();
        //     } catch (Exception e1) {
        //         // TODO Auto-generated catch block
        //         e1.printStackTrace();
        //     }
        // });

        // botao_chamada.addActionListener(e->this.fazerChamada());
        
        setVisible(true);
    }

    private void configPainel() throws IOException{
        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        setBackground(new Color(61, 46, 85));
        setSize((int)screensize.getWidth()/3, (int)screensize.getHeight()/3);
        setLayout(null);
        //setLayout(new FlowLayout(FlowLayout.CENTER));
    }

    private void adicionaComponentes(JLabel  titulo, botao_redondo[] botoes){

        // Config do título 
        titulo.setFont(new Font("Britannic Bold", Font.BOLD, 30));
        titulo.setForeground(new Color(194,48,160));
        titulo.setBounds(120,20,150,100);
        add(titulo);
        
                // Configuração dos botões
        int x = 70;  // Centraliza horizontalmente
        int yBase = 150;                     // Posição Y inicial
        int espacamento_y = 125;                // Espaço entre botões
        int width_botao = 230;
        
        for (int i = 0; i < botoes.length; i++) {
            botao_redondo btn = botoes[i];         
            btn.setBounds(x , yBase + (espacamento_y*i), width_botao, 70);                
            btn.setBackground(Color.WHITE);
            btn.setFocusable(false);
            add(btn);
        }

    }
    // private int painelAutorizar() throws Exception{        
    //     JTextField CapacidadeField = new JTextField(2);

    //     JPanel myPanel = new JPanel();   
    //     Object[] opcoes = {"Autorizar", "Negar", "Cancelar"};
    //     notificacao[] verificacao = professorController.buscarNotificacoesParaProcessar(sessao).toArray(new notificacao[0]);
    //     JComboBox<notificacao> comboBoxNotificacoes = new JComboBox<>(professorController.buscarNotificacoesParaProcessar(sessao).toArray(new notificacao[0]));

    //     if(verificacao.length == 0){
    //         myPanel.add(new JLabel("Nenhuma notificação pendente"));
    //         int resultado = JOptionPane.showConfirmDialog(null, myPanel, 
    //             "Selecionar matrícula para autorizar: ", JOptionPane.OK_CANCEL_OPTION);
    //         if (resultado == JOptionPane.OK_CANCEL_OPTION){
    //             return -1;
    //         }
    //         return -1;
    //     }
    //     myPanel.add(comboBoxNotificacoes);



    //     int result = JOptionPane.showConfirmDialog(null, myPanel, 
    //             "Selecionar matrícula para autorizar: ", JOptionPane.OK_CANCEL_OPTION);
    //     if (result == JOptionPane.OK_OPTION) {

    //         int resposta = JOptionPane.showOptionDialog(
    //         null,
    //         "Deseja autorizar ou negar o aluno?",
    //         "Aviso",
    //         JOptionPane.DEFAULT_OPTION,
    //         JOptionPane.WARNING_MESSAGE,
    //         null,
    //         opcoes,
    //         opcoes[0] // botão padrão
    //         );

    //         switch (resposta) {
    //             case 0:
    //                 System.out.println("Usuário escolheu Autorizar");
    //                 break;
    //             case 1:
    //                 System.out.println("Usuário escolheu Negar");
    //                 break;
    //             case 2:
    //                 System.out.println("Usuário escolheu Cancelar");
    //                 break;
    //             case JOptionPane.CLOSED_OPTION:
    //                 System.out.println("Usuário fechou o diálogo (X)");
    //                 break;
    //         }
    //         return Integer.parseInt(CapacidadeField.getText());
    //     }
    //     return -1;

    // }

    // private void fazerChamada() {
    //     JPanel myPanel = new JPanel();
    //     JComboBox<turma> comboBoxTurma = new JComboBox<>(this.sessao.getTurmasEnsinadas().toArray(new sala[0]));

    //     myPanel.add(new JLabel("Escolha uma"));
    //     myPanel.add(comboBoxTurma);

    //     JOptionPane.showConfirmDialog(null, myPanel, 
    //             "Por favor cadastre os dados: ", JOptionPane.OK_CANCEL_OPTION);
    // }

}

