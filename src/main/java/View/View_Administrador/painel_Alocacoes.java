package View.View_Administrador;

import static View.View_Administrador.tela_administrador.*;
import View.botao_redondo;
import java.awt.Color;
import java.awt.Font;
import java.io.IOException;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Controller.adminController;
import Controller.turmaController;
import Model.professor;
import Model.sala;
import Model.turma;

public class painel_Alocacoes extends JPanel{

        // Constantes baseadas na resolução 1920x1080
       // private final int LARGURA_PAINEL = 480;  // 1920/4
        //private final int ALTURA_PAINEL = 830;   // 1080 - 250 (como definido na tela principal)

        public painel_Alocacoes() throws IOException{
        JLabel titulo = new JLabel("Alocações");
        botao_redondo botao_sala = new botao_redondo("Alocar sala");
        botao_redondo botao_profe = new botao_redondo("Alocar professor");
        
        // Array para o loop
        botao_redondo[] botoes = {botao_sala, botao_profe};

        configPainel();
        adicionaComponentes(titulo, botoes );
        botao_sala.addActionListener(e -> this.alocarSala());
        botao_profe.addActionListener(e -> this.alocarProfessor());
        setVisible(true);
    }
 
    private void configPainel() throws IOException{
        setBackground(new Color(61, 46, 85));
        setSize(LARGURA_PAINEL, ALTURA_PAINEL);
        setLayout(null);  // Usando layout absoluto
    }

     private void adicionaComponentes(JLabel titulo, botao_redondo[] botoes){
        
       // Config do título 
       titulo.setFont(new Font("Britannic Bold", Font.BOLD, 30));
        titulo.setForeground(new Color(248, 181, 95));
        titulo.setBounds(
            (LARGURA_PAINEL - 300) / 2,  // Centralizado horizontalmente
            50,                          // Margem superior
            300,                         // Largura
            50                           // Altura
        );
        add(titulo);
        
        // Configuração dos botões
        int x = (LARGURA_PAINEL - 260) / 2;  // Centraliza horizontalmente
        int yBase = 150;                     // Posição Y inicial
        int espacamento = 80;                // Espaço entre botões

        for (int i = 0; i < botoes.length; i++) {
            botao_redondo btn = botoes[i];
            btn.setBounds(x, yBase + (i * espacamento), 230, 40);
            btn.setBackground(Color.WHITE);
            btn.setFocusable(false);
            add(btn);
        }

    }

    private void alocarSala() {
        adminController admController = new adminController();
        turmaController turmaController = new turmaController();
        JPanel myPanel = new JPanel();  
        JComboBox<turma> comboBoxTurma = new JComboBox<>(turmaController.listarTurmas().toArray(new turma[0]));
        JComboBox<sala> comboBoxSala = new JComboBox<>(admController.listarSalas().toArray(new sala[0]));     
        myPanel.add(new JLabel("Escolha uma turma: "));
        myPanel.add(comboBoxTurma);
        myPanel.add(new JLabel("Escolha uma sala: "));
        myPanel.add(comboBoxSala);

        int result = JOptionPane.showConfirmDialog(null, myPanel, 
                "Por favor cadastre os dados: ", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            sala salaSelecionado = (sala) comboBoxSala.getSelectedItem();
            turma turmaSelecionada = (turma) comboBoxTurma.getSelectedItem();
            try {
                admController.alocarSalaTurma(salaSelecionado, turmaSelecionada);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());

            }
        }
    }

    private void alocarProfessor() {               
        adminController admController = new adminController();
        turmaController turmaController = new turmaController();
        JPanel myPanel = new JPanel();  
        JComboBox<turma> comboBoxTurma = new JComboBox<>(turmaController.listarTurmas().toArray(new turma[0]));
        JComboBox<professor> comboBoxProfessor = new JComboBox<>(admController.listarProfessores().toArray(new professor[0]));     
        myPanel.add(new JLabel("Escolha uma turma: "));
        myPanel.add(comboBoxTurma);
        myPanel.add(new JLabel("Escolha um professor: "));
        myPanel.add(comboBoxProfessor);

        int result = JOptionPane.showConfirmDialog(null, myPanel, 
                "Por favor cadastre os dados: ", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            professor professorSelecionado = (professor) comboBoxProfessor.getSelectedItem();
            turma turmaSelecionada = (turma) comboBoxTurma.getSelectedItem();
            try {
                admController.alocarProfessorTurma(professorSelecionado, turmaSelecionada);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());

            }
        }

    }
      

}
