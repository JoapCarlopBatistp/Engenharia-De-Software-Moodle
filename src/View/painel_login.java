package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class painel_login extends JPanel{
    
    private JTextField usuario;
    private JTextField senha;

    public painel_login() throws IOException{
        JLabel titulo_login = new JLabel("Login");
        JLabel titulo_usuario = new JLabel("Usuário:");
        JLabel titulo_senha = new JLabel("Senha:");
        JButton botao_login = new JButton("Login");
        usuario = new JTextField();
        senha = new JTextField();

        configPainel();
        adicionaComponentes(usuario, senha, titulo_login, titulo_usuario, titulo_senha, botao_login);
        setVisible(true);
    }

    private void configPainel() throws IOException{
        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        setBackground(new Color(61, 46, 85));
        setSize((int)screensize.getWidth()/4, (int)screensize.getHeight()/2);
        setLayout(new FlowLayout(FlowLayout.CENTER));
        
    }

    private void adicionaComponentes(JTextField usuario, JTextField senha, JLabel titulo_login, JLabel titulo_usuario, JLabel titulo_senha, JButton botao_login){
        
        setLayout(null);
        titulo_login.setFont(new Font("Verdana", Font.PLAIN, 30));
        titulo_login.setForeground(new Color(248, 181, 95));
        titulo_login.setBounds(145,240,150,100);

        titulo_usuario.setFont(new Font("Verdana", Font.PLAIN, 18));
        titulo_usuario.setForeground(new Color(248, 181, 95));
        titulo_usuario.setBounds(40,290,150,100);

        usuario.setBounds(40, 370, 300, 20);

        titulo_senha.setFont(new Font("Verdana", Font.PLAIN, 18));
        titulo_senha.setForeground(new Color(248, 181, 95));
        titulo_senha.setBounds(40,370,150,100);

        senha.setBounds(40, 450, 300, 20);

        botao_login.setBounds(70, 530, 230, 40);


        add(titulo_login);
        add(titulo_usuario);
        add(usuario);
        add(titulo_senha);
        add(senha);
        add(botao_login);
    }
}
