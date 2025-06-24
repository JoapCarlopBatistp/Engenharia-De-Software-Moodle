package View.View_Administrador;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Controller.alunoController;
import Controller.reports.report_historico_aluno;
import Controller.reports.report_professor;
import Controller.reports.report_sala;
import Controller.reports.report_turma_vagas;
import Controller.sessaoController;
import Model.aluno;
import Model.sessao;
import static View.View_Administrador.tela_administrador.ALTURA_PAINEL;
import static View.View_Administrador.tela_administrador.LARGURA_PAINEL;
import View.botao_redondo;

public class painel_Relatorios extends JPanel{

        // Constantes baseadas na resolução 1920x1080
        // private final int LARGURA_PAINEL = 480;  // 1920/4
        // private final int ALTURA_PAINEL = 830;   // 1080 - 250 (como definido na tela principal)

        public painel_Relatorios() throws IOException{
        JLabel titulo = new JLabel("Relatórios");
        botao_redondo botao_sala = new botao_redondo("Listar salas");
        botao_redondo botao_turma_vagas = new botao_redondo("Listar turmas com vaga"); 
        botao_redondo botao_turma_professores = new botao_redondo("Listar turmas por professor");        
        botao_redondo botao_profe = new botao_redondo("Listar professores");
        botao_redondo botao_matricula = new botao_redondo("Gerar histórico de matrícula");
        
        // Adicionando o report ao botão
        botao_sala.addActionListener(new report_sala());
        botao_turma_vagas.addActionListener(new report_turma_vagas());
        botao_profe.addActionListener(new report_professor());
        botao_matricula.addActionListener(e -> this.PainelHistorico());
        // Array para o loop
        botao_redondo[] botoes = {botao_sala, botao_turma_vagas, botao_turma_professores, botao_matricula, botao_profe};

        configPainel();
        adicionaComponentes(titulo, botoes );
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
        titulo.setForeground(new Color(194,48,160));
        titulo.setBounds(
            (LARGURA_PAINEL - 300) / 2 + 60,  // Centralizado horizontalmente
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

    private void PainelHistorico() {
        alunoController controller = new alunoController();
        JPanel myPanel = new JPanel();
        
        JComboBox<aluno> comboBoxAluno = new JComboBox<>(controller.buscarTodosALunos().toArray(new aluno[0]));
        myPanel.add(new JLabel("Selecione o aluno: "));
        myPanel.add(comboBoxAluno);

        int result = JOptionPane.showConfirmDialog(null, myPanel, 
                "Gerar histórico de matrícula", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            aluno alunoSelecionado = (aluno) comboBoxAluno.getSelectedItem();
            
            // Mágica para criar a sessao do aluno
            sessao sessao_aluno;
            try {
                sessao_aluno = new sessaoController().criarSessaoDePessoa(alunoSelecionado.getId()  );
                //  System.out.println("Sem erro para criar a sessao");
            } catch (Exception ex) {
                // System.out.println(ex.getMessage());
                sessao_aluno = null;
            }
            
            // criando um listener na mão
             ActionListener listener = new report_historico_aluno(sessao_aluno);
             listener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "OK"));              
            
        }
        //return null;
    }

}
