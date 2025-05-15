package View.View_Administrador;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import Controller.actions_performed.fechar_listener;
import View.botao_logout;

public class tela_administrador extends JFrame{
    
    
    private final JButton botaoFechar;
    private final JButton botaoMinimizar;
    private final painel_Cadastros painel_cadastros;
    private final painel_Relatorios painel_relatorios;
    private final painel_Alocacoes painel_alocacoes;
    private final botao_logout botao_sair;

    // Constantes em tamanho real dos pixels
    public static final int LARGURA_TELA = 1920;
    public static final int ALTURA_TELA = 1080;
    public static final int ALTURA_PAINEL = ALTURA_TELA - 550;
    public static final int LARGURA_PAINEL = LARGURA_TELA/5;

    public tela_administrador() throws IOException{
        botaoFechar = new JButton();
        botaoMinimizar = new JButton();
        painel_cadastros = new painel_Cadastros();
        painel_relatorios = new painel_Relatorios();
        painel_alocacoes = new painel_Alocacoes();
        botao_sair = new botao_logout(this);
        
        // Transforma as medidas em pixels, parece mais razoável
        // System.setProperty("sun.java2d.uiScale", "1");
        
        botaoFechar.addActionListener(new fechar_listener());
        botaoMinimizar.addActionListener(new minimizar_listener());

        configTela();
        adicionaComponentes();
        setVisible(true);
    }

    // Padrão para toda tela a principio
    private void configTela() throws IOException{
        // Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        setUndecorated(true);
        setTitle("Moodle 2");
        setSize(LARGURA_TELA, ALTURA_TELA);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(new Color(61, 54, 92));
    }

    // Isso que vai mudar de tela p tela principalmente
    private void adicionaComponentes(){
                        
        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();

        painel_cadastros.setBounds(
            75, 100,
            (int)screensize.getWidth() - (int)(screensize.getWidth()/1.3),
            (int)screensize.getHeight() - (int)(screensize.getHeight()/3.5));
        
        painel_relatorios.setBounds(
            525, 100,
            (int)screensize.getWidth() - (int)(screensize.getWidth()/1.3),
            (int)screensize.getHeight() - (int)(screensize.getHeight()/3.5) );    

        // Posicionamento dos painéis com espaçamento uniforme
        int espacamento = 50;
        // int larguraPainel = larguraDisponivel/3;

        // painel_cadastros.setBounds(espacamento, 150, LARGURA_PAINEL, ALTURA_PAINEL);
        painel_relatorios.setBounds(2*espacamento + LARGURA_PAINEL, 150, LARGURA_PAINEL, ALTURA_PAINEL);
        painel_alocacoes.setBounds(3*espacamento + 2*LARGURA_PAINEL, 150, LARGURA_PAINEL, ALTURA_PAINEL);
        botao_sair.setBounds((int)screensize.getWidth() - 150,27,40,40);
        add(botao_sair);
        

        add(painel_cadastros);
        add(painel_relatorios);
        add(painel_alocacoes);
        //add(botaoFechar);
        //add(botaoMinimizar);
        configurarBotoesControle(screensize);
    }

    // Função para definir os botões de fechar e minimizar
    private void configurarBotoesControle(Dimension screensize) {
        
        ImageIcon icone_fechar = new ImageIcon(tela_administrador.class.getResource("/cross.png"));
        Image imagem_fechar = icone_fechar.getImage();
        Image imagemEmEscala_fechar = imagem_fechar.getScaledInstance(15,15,  java.awt.Image.SCALE_SMOOTH);
        
        // importando a imagem
        ImageIcon icone_minimizar = new ImageIcon(tela_administrador.class.getResource("/window-minimize.png"));
        // transformando na classe Image em vez de ImageIcon
        Image imagem_minimizar = icone_minimizar.getImage();
        // Definindo o tamanho(escala)
        Image imagemEmEscala_minimizar = imagem_minimizar.getScaledInstance(15,15,  java.awt.Image.SCALE_SMOOTH);
        
                botaoFechar.setBounds((int)screensize.getWidth() - 64,32,32,32);
        botaoFechar.setLayout(null);
        botaoFechar.setBackground(new Color(61, 54, 92));
        botaoFechar.setBorder(null);
        botaoFechar.setIcon(new ImageIcon(imagemEmEscala_fechar));

        botaoMinimizar.setBounds((int)screensize.getWidth() - 110,32,32,32);
        botaoMinimizar.setLayout(null);
        botaoMinimizar.setBackground(new Color(61, 54, 92));
        botaoMinimizar.setBorder(null);
        botaoMinimizar.setIcon(new ImageIcon(imagemEmEscala_minimizar));
        botaoMinimizar.setFocusPainted(false); // muda o ícone
        

        add(botaoFechar);
        add(botaoMinimizar);
    }

    public class minimizar_listener implements ActionListener{    
        @Override
        public void actionPerformed(ActionEvent e) {
            //DO SOMETHING
            setState(JFrame.ICONIFIED);
        }
    }   
}
