package View.View_Administrador;

import static View.View_Administrador.tela_administrador.*;

import Controller.adminController;
import Model.pessoa;
import Model.sala;
import Model.turma;
import Model.roleEnum;
import View.botao_redondo;
import java.awt.Color;
import java.awt.Font;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class painel_Cadastros extends JPanel{

        // Constantes baseadas na resolução 1920x1080
        //private final int LARGURA_PAINEL = 480;  // 1920/4
        //private final int ALTURA_PAINEL = 830;   // 1080 - 250 (como definido na tela principal)

        public painel_Cadastros() throws IOException{
        JLabel titulo = new JLabel("Cadastro");
        botao_redondo botao_sala  = new botao_redondo("Cadastrar nova sala");
        botao_redondo botao_turma = new botao_redondo("Cadastrar nova turma");
        botao_redondo botao_aluno = new botao_redondo("Cadastrar novo aluno");
        botao_redondo botao_profe = new botao_redondo("Cadastrar novo professor");
        
        // Array para o loop
        botao_redondo[] botoes = {botao_sala, botao_turma, botao_aluno, botao_profe};

        configPainel();
        adicionaComponentes(titulo, botoes );
        adminController admin = new adminController();
        botao_sala.addActionListener(e -> this.cadastrarSala());
        botao_turma.addActionListener(e -> this.cadastrarTurma());
        botao_aluno.addActionListener(e -> this.cadastrarAluno());
        botao_profe.addActionListener(e -> this.cadastrarProfessor());
        setVisible(true);
    }
 
    private void configPainel() throws IOException{
        setBackground(new Color(61, 46, 85));
        setSize(LARGURA_PAINEL, ALTURA_PAINEL);
        setLayout(null);  // Usando layout absoluto
    }

     private void adicionaComponentes(JLabel titulo, botao_redondo[] botoes){
        
       // Config do título 
       titulo.setFont(new Font("Britannic Bold", Font.BOLD, 30));
        titulo.setForeground(new Color(248, 181, 95));
        titulo.setBounds(
            (LARGURA_PAINEL - 300) / 2,  // Centralizado horizontalmente
            50,                          // Margem superior
            300,                         // Largura
            50                           // Altura
        );
        add(titulo);
        
        // Configuração dos botões
        int x = (LARGURA_PAINEL - 260) / 2;  // Centraliza horizontalmente
        int yBase = 150;                     // Posição Y inicial
        int espacamento = 80;                // Espaço entre botões

        for (int i = 0; i < botoes.length; i++) {
            botao_redondo btn = botoes[i];
            btn.setBounds(x, yBase + (i * espacamento), 230, 40);
            btn.setBackground(Color.WHITE);
            btn.setFocusable(false);
            add(btn);
        }

    }

    private sala cadastrarSala() {        
        JTextField CapacidadeField = new JTextField(2);

        JPanel myPanel = new JPanel();       
        myPanel.add(new JLabel("Capacidade da sala:"));
        myPanel.add(CapacidadeField);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer

        int result = JOptionPane.showConfirmDialog(null, myPanel, 
                "Por favor cadastre os dados: ", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
           sala sala = new sala();            
            sala.setCapacidade_Sala(Integer.parseInt(CapacidadeField.getText()));
            return sala;
        }
        return null;

    }

    private turma cadastrarTurma() {        
        JTextField SemestreField = new JTextField(2);
        JTextField Vagas_DisponibilizadasField = new JTextField(2);
        JTextField Vagas_OcupadasField = new JTextField(2);
        JTextField DiasField = new JTextField(2);
        JTextField HorarioField = new JTextField(2);

        JPanel myPanel = new JPanel();       
        myPanel.add(new JLabel("Semestre:"));
        myPanel.add(SemestreField);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("Vagas Disponibilizadas:"));
        myPanel.add(Vagas_DisponibilizadasField);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("Vagas Ocupadas:"));
        myPanel.add(Vagas_OcupadasField);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("Dias:"));
        myPanel.add(DiasField);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("Horario:"));
        myPanel.add(HorarioField);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer

        int result = JOptionPane.showConfirmDialog(null, myPanel, 
                "Por favor cadastre os dados: ", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
           turma turma = new turma();            
            turma.setSemestre(SemestreField.getText());
            turma.setVagas_disponibilidadas(Integer.parseInt(Vagas_DisponibilizadasField.getText()));
            turma.setVagas_ocupadas(Integer.parseInt(Vagas_OcupadasField.getText()));
            turma.setDias(DiasField.getText());
            turma.setHorario(HorarioField.getText());
            return turma;
        }
        return null;

    }

    private pessoa cadastrarPessoa(final int papel) {

        JTextField nomeField = new JTextField(20);
        JTextField dddField = new JTextField(2);
        JTextField telefoneField = new JTextField(9);
        JTextField usernameField = new JTextField(20);
        JTextField emailField = new JTextField(20);
        JPasswordField passwordField = new JPasswordField(28);

        JPanel myPanel = new JPanel();
        myPanel.add(new JLabel("Nome :"));
        myPanel.add(nomeField);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("DDD:"));
        myPanel.add(dddField);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("Telefone:"));
        myPanel.add(telefoneField);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("Username:"));
        myPanel.add(usernameField);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("Senha:"));
        myPanel.add(passwordField);
        myPanel.add(new JLabel("Email:"));
        myPanel.add(emailField);

        int result = JOptionPane.showConfirmDialog(null, myPanel, 
                "Por favor cadastre os dados: ", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
           pessoa pessoa = new pessoa();
            pessoa.setNome(nomeField.getText());
            pessoa.setDdd(Integer.parseInt(dddField.getText()));
            pessoa.setTelefone(Integer.parseInt(telefoneField.getText()));
            pessoa.setUsername(usernameField.getText());
            pessoa.setPassword(passwordField.getPassword());
            pessoa.setEmail(emailField.getText());
            pessoa.setPapel(papel);
            return pessoa;
        }
        return null;
    }

    private void cadastrarAluno() {
        adminController admController = new adminController();
        admController.cadastrarAluno(this.cadastrarPessoa(roleEnum.ALUNO.ordinal()));
    }

    private void cadastrarProfessor() {
        adminController admController = new adminController();
        admController.cadastrarProfessor(this.cadastrarPessoa(roleEnum.PROFESSOR.ordinal()));
    }

}


