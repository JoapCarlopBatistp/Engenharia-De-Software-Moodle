package View.View_Aluno;

import javax.swing.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;


public class tela_aluno extends JFrame{
    
    public tela_aluno() throws IOException{
        configTela();
        setVisible(true);
    }

    private void configTela() throws IOException{
        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        setTitle("Moodle 2");
        setSize((int)screensize.getWidth(), (int)screensize.getHeight());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(new Color(70,80,70));
    }

}
