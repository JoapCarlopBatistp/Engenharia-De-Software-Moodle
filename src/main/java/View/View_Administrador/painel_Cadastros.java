package View.View_Administrador;

import static View.View_Administrador.tela_administrador.*;

import Controller.adminController;
import Model.cadeira;
import Model.pessoa;
import Model.professor;
import Model.roleEnum;
import Model.sala;
import Model.turma;
import View.botao_redondo;
import java.awt.Color;
import java.awt.Font;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.JComboBox;
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
        botao_redondo botao_cadeira = new botao_redondo("Cadastrar nova cadeira");
        
        // Array para o loop
        botao_redondo[] botoes = {botao_sala, botao_turma, botao_aluno, botao_profe, botao_cadeira};

        configPainel();
        adicionaComponentes(titulo, botoes );
        botao_sala.addActionListener(e -> this.cadastrarSala());
        botao_turma.addActionListener(e -> this.cadastrarTurma());
        botao_aluno.addActionListener(e -> this.cadastrarAluno());
        botao_profe.addActionListener(e -> this.cadastrarProfessor());
        botao_cadeira.addActionListener(e -> this.cadastrarCadeira());
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

    private cadeira painelCadeira() {        
        JTextField NomeCadeiraField = new JTextField(20);
        JTextField CodigoCadeiraField = new JTextField(2);

        JPanel myPanel = new JPanel();       
        myPanel.add(new JLabel("Nome da cadeira:"));
        myPanel.add(NomeCadeiraField);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("Código da cadeira:"));
        myPanel.add(CodigoCadeiraField);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer

        int result = JOptionPane.showConfirmDialog(null, myPanel, 
                "Por favor cadastre os dados: ", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
           cadeira cadeira = new cadeira();            
            cadeira.setNome_Cadeira(NomeCadeiraField.getText());
            cadeira.setCodigo_Cadeira(CodigoCadeiraField.getText());
            return cadeira;
        }
        return null;

    }

    private void cadastrarCadeira(){
        adminController admController = new adminController();
        admController.cadastrarCadeira(this.painelCadeira());
    }

    private int painelSala() {        
        JTextField CapacidadeField = new JTextField(2);

        JPanel myPanel = new JPanel();       
        myPanel.add(new JLabel("Capacidade da sala:"));
        myPanel.add(CapacidadeField);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer

        int result = JOptionPane.showConfirmDialog(null, myPanel, 
                "Por favor cadastre os dados: ", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            return Integer.parseInt(CapacidadeField.getText());
        }
        return -1;

    }
    
    private void cadastrarSala(){
        adminController admController = new adminController();
        admController.cadastrarSala(this.painelSala());
    }


    private turma painelTurma() {             
        
        adminController admController = new adminController();
        JPanel myPanel = new JPanel();
        JTextField semestre = new JTextField(9);
        JTextField vagas_disponibilizadas = new JTextField(2);
        JTextField dias = new JTextField(20);
        JTextField horario = new JTextField(9);
        JComboBox<cadeira> comboBoxCadeira = new JComboBox<>(admController.listarCadeiras().toArray(new cadeira[0]));
        JComboBox<professor> comboBoxProfessor = new JComboBox<>(admController.listarProfessores().toArray(new professor[0]));
        JComboBox<sala> comboBoxSala = new JComboBox<>(admController.listarSalas().toArray(new sala[0]));
        
        myPanel.add(new JLabel("Digite o semestre atual :"));
        myPanel.add(semestre);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("Digite as vagas disponibilizadas para a turma:"));
        myPanel.add(vagas_disponibilizadas);
        myPanel.add(Box.createHorizontalStrut(50)); // a spacer
        myPanel.add(new JLabel("Digite os dias de aula(separados por vírgula):"));
        myPanel.add(dias);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("Digite o horário da turma:"));
        myPanel.add(horario);
        myPanel.add(new JLabel("Nome da Cadeira:"));
        myPanel.add(comboBoxCadeira);
        myPanel.add(new JLabel("Nome de Professores:"));
        myPanel.add(comboBoxProfessor);
        myPanel.add(new JLabel("Escolha uma sala para a turma:"));
        myPanel.add(comboBoxSala);

        int result = JOptionPane.showConfirmDialog(null, myPanel, 
                "Por favor cadastre os dados: ", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            professor professorSelecionado = (professor) comboBoxProfessor.getSelectedItem();
            sala salaSelecionado = (sala) comboBoxSala.getSelectedItem();
            cadeira cadeiraSelecionada = (cadeira) comboBoxCadeira.getSelectedItem();
            turma turma = new turma();
            turma.setDias(dias.getText());
            turma.setVagas_disponibilizadas(Integer.parseInt(vagas_disponibilizadas.getText()));
            turma.setHorario(horario.getText());
            turma.setSemestre(semestre.getText());
            turma.setId_professor(professorSelecionado.getId_Professor());
            turma.setId_sala(salaSelecionado.getId_Sala());
            turma.setId_cadeira(cadeiraSelecionada.getId_Cadeira());
            return turma;
        }
        return null;


    }     

    private void cadastrarTurma(){
        adminController admController = new adminController();
        admController.cadastrarTurma(this.painelTurma());
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

    private void listarSalas() {
         adminController controller = new adminController();
        JPanel myPanel = new JPanel();
        JComboBox<sala> comboBoxTurma = new JComboBox<>(controller.listarSalas().toArray(new sala[0]));
        myPanel.add(new JLabel("Turmas disponiveis"));
        myPanel.add(comboBoxTurma);

        JOptionPane.showConfirmDialog(null, myPanel, 
                "Por favor cadastre os dados: ", JOptionPane.OK_CANCEL_OPTION);
    }
}


