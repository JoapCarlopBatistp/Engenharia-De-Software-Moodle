package View.View_Professor;

import Controller.actions_performed.fechar_listener;
import Model.sessao;
import View.BackgroundImagePanel;
import View.botao_logout;
import View.View_Login.tela_cadastro;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.*;

public class tela_professor extends JFrame{

    private final JButton botaoFechar;
    private final JButton botaoMinimizar;
    private final painel_Presenca painel_presenca;
    private final painel_Professor painel_professor;
    private final JButton botao_sair;
    private BackgroundImagePanel backgroundPanel;
    private JLabel imagem_gato;
    private JLabel imagem_professor;

    public tela_professor(sessao sessao) throws IOException{

        botaoFechar = new JButton();
        botaoMinimizar = new JButton();
        painel_professor = new painel_Professor(sessao);
        painel_presenca = new painel_Presenca();
        botao_sair = new botao_logout(this, sessao);
        imagem_gato = new JLabel();
        imagem_professor = new JLabel();
        

        botaoFechar.addActionListener(new fechar_listener());
        botaoMinimizar.addActionListener(new minimizar_listener());

        configTela();
        ImageIcon icone_background = new ImageIcon(tela_cadastro.class.getResource("/flat-mountains.png"));
        backgroundPanel = new BackgroundImagePanel(icone_background);
        adicionaComponentes(painel_professor, painel_presenca);
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

    private void adicionaComponentes(JPanel painel_professor, JPanel painel_presenca){
        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();

        painel_presenca.setBounds(
            75, 100,
            (int)screensize.getWidth() - (int)(screensize.getWidth()/1.3),
            (int)screensize.getHeight() - (int)(screensize.getHeight()/3.5));

        painel_professor.setBounds(
            525, 100,
            (int)screensize.getWidth() - (int)(screensize.getWidth()/1.3),
            (int)screensize.getHeight() - (int)(screensize.getHeight()/3.5) );
        
        botao_sair.setBounds((int)screensize.getWidth() - 156,27,40,40);
        add(botao_sair);

        add(painel_professor);
        add(painel_presenca);
        configurarBotoesControle(screensize);
    }

    private void configurarBotoesControle(Dimension screensize){
        
        ImageIcon icone_fechar = new ImageIcon(tela_professor.class.getResource("/cross.png"));
        Image imagem_fechar = icone_fechar.getImage();
        Image imagemEmEscala_fechar = imagem_fechar.getScaledInstance(15,15,  java.awt.Image.SCALE_SMOOTH);
        
        // importando a imagem
        ImageIcon icone_minimizar = new ImageIcon(tela_professor.class.getResource("/window-minimize.png"));
        // transformando na classe Image em vez de ImageIcon
        Image imagem_minimizar = icone_minimizar.getImage();
        // Definindo o tamanho(escala)
        Image imagemEmEscala_minimizar = imagem_minimizar.getScaledInstance(15,15,  java.awt.Image.SCALE_SMOOTH);

        ImageIcon icone_gato = new ImageIcon(tela_cadastro.class.getResource("/gato_tela_professor.png"));
        Image gato_login = icone_gato.getImage();
        Image imagemEmEscala_gatinho = gato_login.getScaledInstance(150,100,  java.awt.Image.SCALE_SMOOTH);

        ImageIcon icone_professor = new ImageIcon(tela_cadastro.class.getResource("/felino_para_telas.png"));
        Image professor = icone_professor.getImage();
        Image imagemEmEscala_professor = professor.getScaledInstance(400,400,  java.awt.Image.SCALE_SMOOTH);
        
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

        imagem_gato.setIcon(new ImageIcon(imagemEmEscala_gatinho));
        imagem_gato.setBounds(500, 10, 150, 100);

        imagem_professor.setIcon(new ImageIcon(imagemEmEscala_professor));
        imagem_professor.setBounds(1000, 100, 500, 500);

        add(imagem_gato);
        add(imagem_professor);
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
