
package View.View_Professor;

import View.botao_redondo;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.IOException;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class painel_Presenca extends JPanel{

    public painel_Presenca() throws IOException{

        JLabel titulo = new JLabel("Presença");
        botao_redondo botao_relatorio_presenca = new botao_redondo("Gerar Relatório de Presença");
        botao_redondo botao_verificar_presenca = new botao_redondo("Fazer chamada");
    
        botao_redondo[] botoes = {botao_relatorio_presenca, botao_verificar_presenca};

        configPainel();
        adicionaComponentes(titulo, botoes );
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
        titulo.setBounds(115,20,150,100);
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

}

