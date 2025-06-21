package View.View_Login;

import Controller.actions_performed.*;
import View.View_Administrador.tela_administrador;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.*;


public class tela_cadastro extends JFrame{
    
    private JButton botaoFechar;
    private JButton botaoMinimizar;
    private painel_login painel;

    public tela_cadastro() throws IOException{
        botaoFechar = new JButton();
        botaoMinimizar = new JButton();
        painel = new painel_login();
        JLabel imagem_cadastro = new JLabel();
        JLabel nome_programa = new JLabel();
        
        botaoFechar.addActionListener(new fechar_listener());
        botaoMinimizar.addActionListener(new minimizar_listener());
        painel.botao_login.addActionListener(e -> {
            try {
                painel.login(this);
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });

        configTela();
        adicionaComponentes(botaoFechar, botaoMinimizar, painel, imagem_cadastro, nome_programa);
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

    private void adicionaComponentes(JButton botaoFechar, JButton botaoMinimizar, painel_login painel, JLabel imagem_cadastro, JLabel nome_programa){
        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        ImageIcon icone_fechar = new ImageIcon(tela_cadastro.class.getResource("/cross.png"));
        Image imagem_fechar = icone_fechar.getImage();
        Image imagemEmEscala_fechar = imagem_fechar.getScaledInstance(15,15,  java.awt.Image.SCALE_SMOOTH);
        ImageIcon icone_minimizar = new ImageIcon(tela_cadastro.class.getResource("/window-minimize.png"));
        Image imagem_minimizar = icone_minimizar.getImage();
        Image imagemEmEscala_minimizar = imagem_minimizar.getScaledInstance(15,15,  java.awt.Image.SCALE_SMOOTH);
        ImageIcon icone_gato = new ImageIcon(tela_cadastro.class.getResource("/gatinho_login.png"));
        Image gato_login = icone_gato.getImage();
        Image imagemEmEscala_gatinho = gato_login.getScaledInstance(430,640,  java.awt.Image.SCALE_SMOOTH);

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

        painel.setBounds((int)screensize.getWidth()/2 - (int)screensize.getWidth()/8, 0, (int)screensize.getWidth()/4, (int)screensize.getHeight());
        painel.setOpaque(true);
        imagem_cadastro.setIcon(new ImageIcon(imagemEmEscala_gatinho));
        imagem_cadastro.setBounds(270, 300, 430, 640);

        nome_programa.setBounds(110,32,200,50);
        nome_programa.setForeground(new Color(248, 181, 95));
        nome_programa.setFont(new Font("Britannic Bold", Font.BOLD, 15));

        add(nome_programa);
        add(painel);
        add(imagem_cadastro, BorderLayout.CENTER);
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
