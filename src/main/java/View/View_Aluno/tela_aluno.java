package View.View_Aluno;

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
import javax.swing.JLabel;
import javax.swing.JPanel;

import Controller.actions_performed.fechar_listener;
import Model.sessao;
import View.BackgroundImagePanel;
import View.View_Login.tela_cadastro;
import View.botao_logout;

public class tela_aluno extends JFrame{

    private final JButton botaoFechar;
    private final JButton botaoMinimizar;
    private final painel_Aluno painel_aluno;
    private final JButton botao_sair;
    private BackgroundImagePanel backgroundPanel;
    private JLabel imagem_gato_1;
    private JLabel imagem_gato_2;
    private JLabel imagem_gato_3;

    public tela_aluno(sessao sessao) throws IOException{

        botaoFechar = new JButton();
        botaoMinimizar = new JButton();
        painel_aluno = new painel_Aluno(sessao);
        botao_sair = new botao_logout(this, sessao);
        imagem_gato_1 = new JLabel();
        imagem_gato_2 = new JLabel();
        imagem_gato_3 = new JLabel();

        botaoFechar.addActionListener(new fechar_listener());
        botaoMinimizar.addActionListener(new minimizar_listener());

        configTela();
        ImageIcon icone_background = new ImageIcon(tela_cadastro.class.getResource("/flat-mountains.png"));
        backgroundPanel = new BackgroundImagePanel(icone_background);
        adicionaComponentes(painel_aluno);
        setVisible(true);
    }

    private void configTela() throws IOException{
        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        setUndecorated(true);
        setTitle("Portal do Aluno");
        setSize((int)screensize.getWidth(), (int)screensize.getHeight());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(new Color(61, 54, 92));
    }

    private void adicionaComponentes(JPanel painel_aluno){
        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();

        painel_aluno.setBounds(
            75,
            100,
            (int)screensize.getWidth() - (int)screensize.getWidth()/3,
            (int)screensize.getHeight() - (int)screensize.getHeight()/3);

        // Gato lutando
        ImageIcon icone_gato_1 = new ImageIcon(tela_cadastro.class.getResource("/gato_lutando.png"));
        Image gato_1 = icone_gato_1.getImage();
        Image imagemEmEscala_gato_1 = gato_1.getScaledInstance(150,150,  java.awt.Image.SCALE_SMOOTH);

        // Gato Assutado
        ImageIcon icone_gato_2 = new ImageIcon(tela_cadastro.class.getResource("/gato_assustado.png"));
        Image gato_2 = icone_gato_2.getImage();
        Image imagemEmEscala_gato_2 = gato_2.getScaledInstance(150,100,  java.awt.Image.SCALE_SMOOTH);    
        
        // Gato Esfinge
        ImageIcon icone_gato_3 = new ImageIcon(tela_cadastro.class.getResource("/gato_secreto.png"));
        Image gato_3 = icone_gato_3.getImage();
        //Image imagemEmEscala_gato_3 = gato_3.getScaledInstance(150,100,  java.awt.Image.SCALE_SMOOTH);

        imagem_gato_1.setIcon(new ImageIcon(imagemEmEscala_gato_1));
        imagem_gato_1.setBounds(1100, 22, 150, 150);

        imagem_gato_2.setIcon(new ImageIcon(imagemEmEscala_gato_2));
        imagem_gato_2.setBounds(200, 27, 150, 100);

        botao_sair.setBounds((int)screensize.getWidth() - 156,27,40,40);
        add(botao_sair);

        add(imagem_gato_1);
        add(imagem_gato_2);

        add(painel_aluno);
        configurarBotoesControle(screensize);
    }

    private void configurarBotoesControle(Dimension screensize){
        
        ImageIcon icone_fechar = new ImageIcon(tela_aluno.class.getResource("/cross.png"));
        Image imagem_fechar = icone_fechar.getImage();
        Image imagemEmEscala_fechar = imagem_fechar.getScaledInstance(15,15,  java.awt.Image.SCALE_SMOOTH);
        
        // importando a imagem
        ImageIcon icone_minimizar = new ImageIcon(tela_aluno.class.getResource("/window-minimize.png"));
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

        backgroundPanel.setLayout(null); 
        backgroundPanel.setOpaque(false);
        backgroundPanel.setBounds(0,0,getWidth(),getHeight());
        
        add(botaoFechar);
        add(botaoMinimizar);
        add(backgroundPanel);
    }

    public class minimizar_listener implements ActionListener{    
        @Override
        public void actionPerformed(ActionEvent e) {
            //DO SOMETHING
            setState(JFrame.ICONIFIED);
        }
    }   
}
