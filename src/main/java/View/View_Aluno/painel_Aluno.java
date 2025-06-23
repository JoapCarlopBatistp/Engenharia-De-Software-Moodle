package View.View_Aluno;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.lowagie.text.Image;
import Controller.alunoController;
import Controller.reports.report_minhas_turmas;
import Model.sessao;
import Model.turma;
import View.View_Login.tela_cadastro;
import View.*;

public class painel_Aluno extends JPanel{
    private sessao sessao;
    private botao_imagem botao_gato_esfinge;
    public painel_Aluno(sessao sessao) throws IOException{
        this.sessao = sessao;
        JLabel titulo = new JLabel("Opções de Aluno");
        ImageIcon icone_gato_3 = new ImageIcon(tela_cadastro.class.getResource("/gato_secreto.png"));
        java.awt.Image gato_3 = icone_gato_3.getImage();
        java.awt.Image imagemEmEscala_gato_3 = gato_3.getScaledInstance(200,70,  java.awt.Image.SCALE_SMOOTH);

        botao_redondo botao_turmas_disponiveis = new botao_redondo("Visualizar turmas disponíveis");
        botao_redondo botao_turmas_atuais = new botao_redondo("Visualizar minhas turmas");
        botao_redondo botao_presenca = new botao_redondo("Visualizar presença");
        botao_redondo botao_matricula = new botao_redondo("Fazer nova matrícula");
        botao_redondo botao_historico = new botao_redondo("Gerar histórico de matrícula");
        botao_imagem  botao_gato = new botao_imagem(imagemEmEscala_gato_3);
        
        botao_gato_esfinge = botao_gato;
    
        botao_redondo[] botoes = {botao_turmas_atuais, botao_turmas_disponiveis, botao_presenca, botao_matricula, botao_historico};

        configPainel();
        adicionaComponentes(titulo, botoes );
        botao_matricula.addActionListener(e -> this.fazerMatricula());
        // botao_presenca.addActionListener(e -> this.configPainel());
        // botao_historico.addActionListener(e -> this.configPainel());
        // botao_turmas_atuais.addActionListener(e -> this.configPainel());
        botao_turmas_disponiveis.addActionListener(e -> this.visualizarTurmasDisponiveis());
        
        botao_turmas_atuais.addActionListener(new report_minhas_turmas(sessao));
        setVisible(true);
    }

    private void configPainel() throws IOException{
        // Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        setBackground(new Color(61, 46, 85));
        //setSize((int)screensize.getWidth()/4, (int)screensize.getHeight()/2);
        setLayout(null);
        //setLayout(new FlowLayout(FlowLayout.CENTER));
    }

    private void adicionaComponentes(JLabel  titulo, botao_redondo[] botoes){

        // Config do título 
        titulo.setFont(new Font("Britannic Bold", Font.BOLD, 30));
        titulo.setForeground(new Color(194,48,160));
        titulo.setBounds(380,20,400,100);
        add(titulo);
        
                // Configuração dos botões
        int x = 140;  // Centraliza horizontalmente
        int yBase = 170;                     // Posição Y inicial
        int espacamento_x = 250;                // Espaço entre botões
        int espacamento_y = 150;                // Espaço entre botões
        int width_botao = 230;

        for (int i = 0; i < botoes.length; i++) {
            botao_redondo btn = botoes[i];
            if(i < 3){
                btn.setBounds(x  +(i*espacamento_x), yBase , width_botao, 70);    
            }else{
                btn.setBounds(x   + ( (i-3)*espacamento_x), yBase + espacamento_y, width_botao, 70);    
            }
            btn.setBackground(Color.WHITE);
            btn.setFocusable(false);
            add(btn);
        }

        
        botao_gato_esfinge.setBounds(x   + ( (2)*espacamento_x), yBase + espacamento_y, width_botao, 70); 
        botao_gato_esfinge.setSound(getClass().getResource("/miado_gato.wav"));
        add(botao_gato_esfinge);
    }

    private void fazerMatricula() {
        try {
            alunoController controller = new alunoController();
            controller.cadastrarMatricula(this.PainelMatricula(), this.sessao);
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private turma PainelMatricula() {
        alunoController controller = new alunoController();
        JPanel myPanel = new JPanel();
        JComboBox<turma> comboBoxTurma = new JComboBox<>(controller.listarTurmas().toArray(new turma[0]));
        myPanel.add(new JLabel("Selecione a turma: "));
        myPanel.add(comboBoxTurma);

        int result = JOptionPane.showConfirmDialog(null, myPanel, 
                "Nova matrícula", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            turma turmaSelecionada = (turma) comboBoxTurma.getSelectedItem();
            turma turma = new turma();
            turma.setId_turma(turmaSelecionada.getId_turma());
            turma.setSemestre(turmaSelecionada.getSemestre());
            turma.setVagas_disponibilizadas(turmaSelecionada.getVagas_disponibilizadas());
            turma.setVagas_ocupadas(turmaSelecionada.getVagas_ocupadas());
            turma.setDias(turmaSelecionada.getDias());
            turma.setHorario(turmaSelecionada.getHorario());
            turma.setId_sala(turmaSelecionada.getId_sala());
            turma.setId_cadeira(turmaSelecionada.getId_cadeira());
            turma.setId_professor(turmaSelecionada.getId_professor());
            return turma;
        }
        return null;
    }

    private void visualizarTurmasDisponiveis() {
        alunoController controller = new alunoController();
        JPanel myPanel = new JPanel();
        JComboBox<turma> comboBoxTurma = new JComboBox<>(controller.listarTurmas().toArray(new turma[0]));
        myPanel.add(new JLabel("Selecione a turma: "));
        myPanel.add(comboBoxTurma);

        JOptionPane.showConfirmDialog(null, myPanel, 
                "Turmas disponiveis", JOptionPane.OK_CANCEL_OPTION);
    }
}
