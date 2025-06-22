package View;

import javax.swing.*;

import View.View_Login.tela_cadastro;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class BackgroundImagePanel extends JPanel {

    private Image backgroundImage;

    public BackgroundImagePanel(ImageIcon icone) {
        backgroundImage = icone.getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); 

        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}