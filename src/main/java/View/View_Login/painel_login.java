package View.View_Login;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import Controller.sessaoController;
import Model.roleEnum;
import Model.sessao;
import View.botao_redondo;
import View.View_Administrador.tela_administrador;
import View.View_Aluno.tela_aluno;
import View.View_Professor.tela_professor;

public class painel_login extends JPanel {
    
    public JTextField usuario;
    public JPasswordField senha;
    public botao_redondo botao_login;

    public painel_login() throws IOException{
        JLabel titulo_login = new JLabel("Login");
        JLabel titulo_usuario = new JLabel("Usuário:");
        JLabel titulo_senha = new JLabel("Senha:");
        botao_login = new botao_redondo("Login");
        usuario = new JTextField();
        senha = new JPasswordField();

        configPainel();
        adicionaComponentes(usuario, senha, titulo_login, titulo_usuario, titulo_senha, botao_login);
        setVisible(true);
    }

    private void configPainel() throws IOException{
        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        setBackground(new Color(55,44,78));
        setSize((int)screensize.getWidth()/4, (int)screensize.getHeight()/2);
        setLayout(new FlowLayout(FlowLayout.CENTER));
        
    }

    private void adicionaComponentes(JTextField usuario, JPasswordField senha, JLabel titulo_login, JLabel titulo_usuario, JLabel titulo_senha, botao_redondo botao_login){
        
        setLayout(null);
        titulo_login.setFont(new Font("Britannic Bold", Font.BOLD, 30));
        titulo_login.setForeground(new Color(194,48,160));
        titulo_login.setBounds(145,240,150,100);

        titulo_usuario.setFont(new Font("Verdana", Font.BOLD, 18));
        titulo_usuario.setForeground(new Color(194,48,160));
        titulo_usuario.setBounds(40,290,150,100);

        usuario.setBounds(40, 360, 300, 40);
        usuario.setBackground(new Color(61,54,92));
        usuario.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        usuario.setForeground(new Color(194,48,160));
        usuario.setCaretColor(new Color(194,48,160));

        Border paddingBorder = new EmptyBorder(0, 10, 0, 10);
        Border originalBorderUsuario = usuario.getBorder();

        usuario.setBorder(new CompoundBorder(originalBorderUsuario, paddingBorder));
  
        titulo_senha.setFont(new Font("Verdana", Font.BOLD, 18));
        titulo_senha.setForeground(new Color(194,48,160));
        titulo_senha.setBounds(40,370,150,100);



        senha.setBounds(40, 440, 300, 40);
        senha.setBackground(new Color(61,54,92));
        senha.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        senha.setForeground(new Color(194,48,160));
        senha.setCaretColor(new Color(194,48,160));

        Border originalBorderSenha = senha.getBorder();

        senha.setBorder(new CompoundBorder(originalBorderSenha, paddingBorder));

        botao_login.setBounds(70, 530, 230, 50);
        botao_login.setBackground(new Color(61,54,92));
        botao_login.setFocusable(false);
        botao_login.setForeground(new Color(194,48,160));
        botao_login.setFont(new Font("Verdana", Font.BOLD, 18));


        add(titulo_login);
        add(titulo_usuario);
        add(usuario);
        add(titulo_senha);
        add(senha);
        add(botao_login);
    }

    public void login(final JFrame frameParaFechar) {
        this.loginLogic(frameParaFechar);
    }

    private void loginLogic(final JFrame frameParaFechar) {
        String stringSenha = new String(senha.getPassword()).trim();
        try {
             if((usuario.getText().trim().equals("")) && stringSenha.equals("")){
					throw new Exception ("Verifique se algum campo está vazio!");
            } else {
                sessaoController sessaoContrl = new sessaoController();
                sessao sessao = sessaoContrl.login(usuario.getText().trim(), stringSenha);
                switch(roleEnum.values()[sessao.getPapel()]) {
                    case PROFESSOR:
                        new tela_professor(sessao);
                        break;
                    case ALUNO:
                        new tela_aluno(sessao);
                        break;
                    default:
                        new tela_administrador(sessao);
                }
                frameParaFechar.dispose();
                sessao.disconnect();
            }   
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }	
    }
}
