package View.View_Login;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Database.databaseconn;
import View.botao_redondo;
import View.View_Administrador.tela_administrador;
import View.View_Aluno.tela_aluno;
import View.View_Professor.tela_professor;

public class painel_login extends JPanel{
    
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
        setBackground(new Color(61, 46, 85));
        setSize((int)screensize.getWidth()/4, (int)screensize.getHeight()/2);
        setLayout(new FlowLayout(FlowLayout.CENTER));
        
    }

    private void adicionaComponentes(JTextField usuario, JPasswordField senha, JLabel titulo_login, JLabel titulo_usuario, JLabel titulo_senha, botao_redondo botao_login){
        
        setLayout(null);
        titulo_login.setFont(new Font("Britannic Bold", Font.BOLD, 30));
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
        botao_login.setBackground(Color.WHITE);
        botao_login.setFocusable(false);


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
        databaseconn bd = new databaseconn();
        PreparedStatement statement = null;
        ResultSet resultSet;
        String stringSenha = new String(senha.getPassword()).trim();
        if((usuario.getText().trim().equals("")) && stringSenha.equals("")){
					JOptionPane.showMessageDialog(null, "Verifique se algum campo está vazio!");
        } else {
            try {
                if(!bd.getConnection()){
                    JOptionPane.showMessageDialog(null, "Falha na conexão, o sistem será fechado!");
                    System.exit(0);
                }
                String url = "SELECT * FROM pessoa WHERE nome_de_usuario=? AND senha=?";
                statement = bd.connection.prepareStatement(url);
                statement.setString(1, usuario.getText());
                statement.setString(2, stringSenha);
                resultSet = statement.executeQuery();
                if(resultSet.next()){
                    JOptionPane.showMessageDialog(null, "Usuário logado com sucesso");
                    if(resultSet.getLong("Papel") == 1) {
                        new tela_aluno();
                    } else if(resultSet.getLong("Papel") == 2) {
                        new tela_professor();
                    } else {
                        new tela_administrador();
                    }
                    
                    frameParaFechar.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Nenhum usuário com esse username e senha foi encontrado!");
                }
                resultSet.close();
                statement.close();
                bd.close();
            } catch(Exception erro) {
                JOptionPane.showMessageDialog(null, "Algo de errado aconteceu:\n " + erro.toString());
            }
            
        }
			
    }
}
