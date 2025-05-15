package View.View_Aluno;

import View.botao_redondo;
import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class painel_Aluno extends JPanel{

    public painel_Aluno() throws IOException{
        JLabel titulo = new JLabel("Opções");
        botao_redondo botao_turmas_disponiveis = new botao_redondo("Visualizar turmas disponíveis");
        botao_redondo botao_turmas_atuais = new botao_redondo("Visualizar minhas turmas");
        botao_redondo botao_presenca = new botao_redondo("Visualizar presença");
        botao_redondo botao_matricula = new botao_redondo("Fazer nova matrícula");
        botao_redondo botao_historico = new botao_redondo("Gerar histórico de matrícula");
    
        botao_redondo[] botoes = {botao_turmas_atuais, botao_turmas_disponiveis, botao_presenca, botao_matricula, botao_historico};

        configPainel();
        adicionaComponentes(titulo, botoes );
        setVisible(true);
    }

    private void configPainel() throws IOException{
        // Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        setBackground(new Color(61, 46, 85));
        //setSize((int)screensize.getWidth()/4, (int)screensize.getHeight()/2);
        setLayout(null);
        //setLayout(new FlowLayout(FlowLayout.CENTER));
    }

    private void adicionaComponentes(JLabel  titulo, botao_redondo[] botoes){

        // Config do título 
        titulo.setFont(new Font("Britannic Bold", Font.BOLD, 30));
        titulo.setForeground(new Color(248, 181, 95));
        titulo.setBounds(50,20,150,100);
        add(titulo);
        
                // Configuração dos botões
        int x = 70;  // Centraliza horizontalmente
        int yBase = 150;                     // Posição Y inicial
        int espacamento_x = 250;                // Espaço entre botões
        int espacamento_y = 150;                // Espaço entre botões
        int width_botao = 230;

        for (int i = 0; i < botoes.length; i++) {
            botao_redondo btn = botoes[i];
            if(i < 3){
                btn.setBounds(x  +(i*espacamento_x), yBase , width_botao, 70);    
            }else{
                btn.setBounds(x   + ( (i-3)*espacamento_x), yBase + espacamento_y, width_botao, 70);    
            }
            btn.setBackground(Color.WHITE);
            btn.setFocusable(false);
            add(btn);
        }


    }
}
