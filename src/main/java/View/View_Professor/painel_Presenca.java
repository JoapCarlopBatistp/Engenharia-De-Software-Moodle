
package View.View_Professor;

import View.TabelaPresencaUtil;
import View.botao_redondo;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Controller.presencaController;
import Controller.turmaController;
import Model.presenca;
import Model.sessao;
import Model.turma;

public class painel_Presenca extends JPanel{

    private sessao sessao;
    public painel_Presenca(sessao sessao) throws IOException{
        this.sessao = sessao;
        JLabel titulo = new JLabel("Presença");
        botao_redondo botao_relatorio_presenca = new botao_redondo("Gerar Relatório de Presença");
        botao_redondo botao_verificar_presenca = new botao_redondo("Fazer chamada");
    
        botao_redondo[] botoes = {botao_relatorio_presenca, botao_verificar_presenca};

        configPainel();
        adicionaComponentes(titulo, botoes );
        botao_verificar_presenca.addActionListener(e->this.fazerChamada());
        setVisible(true);
    }

    private void configPainel() throws IOException{
        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        setBackground(new Color(61, 46, 85));
        setSize((int)screensize.getWidth()/3, (int)screensize.getHeight()/3);
        setLayout(null);
        //setLayout(new FlowLayout(FlowLayout.CENTER));
    }

    private void adicionaComponentes(JLabel  titulo, botao_redondo[] botoes){

        // Config do título 
        titulo.setFont(new Font("Britannic Bold", Font.BOLD, 30));
        titulo.setForeground(new Color(194,48,160));
        titulo.setBounds(115,20,150,100);
        add(titulo);
        
                // Configuração dos botões
        int x = 70;  // Centraliza horizontalmente
        int yBase = 150;                     // Posição Y inicial
        int espacamento_y = 125;                // Espaço entre botões
        int width_botao = 230;

        for (int i = 0; i < botoes.length; i++) {
            botao_redondo btn = botoes[i];            
            btn.setBounds(x , yBase + (espacamento_y*i), width_botao, 70);                
            btn.setBackground(Color.WHITE);
            btn.setFocusable(false);
            add(btn);
        }


    }

    private void fazerChamada() {
    JComboBox<turma> comboBoxTurma = new JComboBox<>(this.sessao.getTurmasEnsinadas().toArray(new turma[0]));
    presencaController controller = new presencaController();
    TabelaPresencaUtil tabela = new TabelaPresencaUtil();
    JPanel myPanel = new JPanel();
    myPanel.add(new JLabel("Escolha uma turma:"));
    myPanel.add(comboBoxTurma);

    int option = JOptionPane.showConfirmDialog(null, myPanel, 
            "Seleção de Turma", JOptionPane.OK_CANCEL_OPTION);

    if (option == JOptionPane.OK_OPTION) {
        turma turmaSelecionada = (turma) comboBoxTurma.getSelectedItem();

        // Monte a lista de presencas (de acordo com sua lógica/banco)
        List<presenca> listaPresenca = controller.buscarPresencasPorTurma(turmaSelecionada); // Você precisa implementar esse método

        // Mostra a tabela para o professor marcar presença
        List<presenca> resultado = tabela.mostrarTabelaPresenca(listaPresenca);

        // Aqui você pode salvar/processar o resultado como quiser
        if (resultado != null && !resultado.isEmpty()) {
            // Salve as presenças (ex: enviar para o banco, etc)
            controller.salvarPresencas(resultado); // Implemente de acordo com seu app!
            JOptionPane.showMessageDialog(this, "Presenças salvas com sucesso!");
        }
    }
}
    

}

