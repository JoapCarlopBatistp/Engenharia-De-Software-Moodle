package View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.sound.sampled.*;
import java.io.IOException;
import java.awt.Image;

public class botao_imagem extends JButton {

    private Clip soundClip;

    public botao_imagem(Image image) {
        super(new ImageIcon(image));
        initButtonStyling();
    }

    public botao_imagem(Icon icon) {
        super(icon);
        initButtonStyling();
    }

    public botao_imagem(URL imageUrl) throws Exception {
        this(new ImageIcon(imageUrl));
    }

    public botao_imagem(String imagePath) {
        this(new ImageIcon(imagePath));
    }

    public botao_imagem(Icon icon, URL soundUrl) {
        this(icon);
        setSound(soundUrl);
    }

    public botao_imagem(Image image, URL soundUrl) {
        this(image);
        setSound(soundUrl);
    }

    private void initButtonStyling() {
        setBorderPainted(false);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setOpaque(false);
        setText("");
    }

    public void setSound(URL soundUrl) {
        if (soundClip != null && soundClip.isOpen()) {
            soundClip.close();
        }
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundUrl);
            soundClip = AudioSystem.getClip();
            soundClip.open(audioStream);
        } catch (UnsupportedAudioFileException e) {
            System.err.println("Unsupported audio file format: " + soundUrl);
            e.printStackTrace();
            soundClip = null;
        } catch (IOException e) {
            System.err.println("I/O error reading audio file: " + soundUrl);
            e.printStackTrace();
            soundClip = null;
        } catch (LineUnavailableException e) {
            System.err.println("Audio line unavailable: " + e.getMessage());
            e.printStackTrace();
            soundClip = null;
        }
    }

    @Override
    protected void fireActionPerformed(ActionEvent event) {
        if (soundClip != null) {
            soundClip.setFramePosition(0);
            soundClip.start();
        }
        super.fireActionPerformed(event);
    }
}
