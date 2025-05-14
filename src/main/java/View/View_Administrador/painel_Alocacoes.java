package main.java.View.View_Administrador;

import static main.java.View.View_Administrador.tela_administrador.*;
import main.java.View.botao_redondo;
import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
        int x = (LARGURA_PAINEL - 230) / 2;  // Centraliza horizontalmente
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
