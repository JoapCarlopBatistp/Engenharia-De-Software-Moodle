package View.View_Administrador;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JPanel;

import Controller.reports.report_test;
import static View.View_Administrador.tela_administrador.ALTURA_PAINEL;
import static View.View_Administrador.tela_administrador.LARGURA_PAINEL;
import View.botao_redondo;

public class painel_Relatorios extends JPanel{

        // Constantes baseadas na resolução 1920x1080
        // private final int LARGURA_PAINEL = 480;  // 1920/4
        // private final int ALTURA_PAINEL = 830;   // 1080 - 250 (como definido na tela principal)

        public painel_Relatorios() throws IOException{
        JLabel titulo = new JLabel("Relatórios");
        botao_redondo botao_sala = new botao_redondo("Listar salas");
        botao_redondo botao_turma = new botao_redondo("Listar turmas");        
        botao_redondo botao_profe = new botao_redondo("Listar professores");
        botao_redondo botao_matricula = new botao_redondo("Gerar histórico de matrícula");
        
        // Adicionando o report ao botão
        botao_sala.addActionListener(new report_test());

        // Array para o loop
        botao_redondo[] botoes = {botao_sala, botao_turma, botao_matricula, botao_profe};

        configPainel();
        adicionaComponentes(titulo, botoes );
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

}
