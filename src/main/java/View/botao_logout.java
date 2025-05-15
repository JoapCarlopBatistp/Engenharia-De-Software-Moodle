package View;

import View.View_Login.tela_cadastro;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class botao_logout extends JButton{
        
    private final JFrame tela_atual;

    public botao_logout(JFrame tela_atual) {
        //super("Logout");
        this.tela_atual = tela_atual;

        // importando a imagem tela_administrador.class.getResource("/cross.png")
        ImageIcon icone_logout = new ImageIcon(botao_logout.class.getResource("/logout_branco.png"));
        // transformando na classe Image em vez de ImageIcon
        Image imagem_logout = icone_logout.getImage();
        // Definindo o tamanho(escala)
        Image imagemEmEscala_logout = imagem_logout.getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH);    
        
        setLayout(null);
        setBackground(new Color(61, 54, 92));
        setBorder(null);
         //setOpaque(true);
        setIcon(new ImageIcon(imagemEmEscala_logout));
        setFocusPainted(false);
        // setContentAreaFilled(false); 

        addActionListener(this::handleLogout);
    }

    private void criar_tela_login() {
       try {
            JFrame loginFrame = new tela_cadastro(); // Fresh instance
            loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            loginFrame.setLocationRelativeTo(null); // Center screen
            loginFrame.setVisible(true);
            
        } catch (Exception e) {
            System.err.println("Houve algum problema ao criar a tela de login" + e.getMessage());
            System.exit(1);
        }
    }

    private void handleLogout(ActionEvent e) {
        
        // Color bgColor = new Color(61, 54, 92);
        // UIManager.put("OptionPane.background", bgColor);
        // UIManager.put("Panel.background", bgColor);
        
        int confirm = JOptionPane.showConfirmDialog(
            tela_atual,
            "Tem certeza que quer sair?",
            "Confirmar logout",
            JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
             tela_atual.dispose();  
             criar_tela_login();

        }
    }

}