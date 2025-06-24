package View;
import Model.presenca;
import javax.swing.*;
import javax.swing.table.*;
import java.util.*;

public class TabelaPresencaUtil {

    public List<presenca> mostrarTabelaPresenca(List<presenca> lista) {
        String[] colunas = {"ID Aluno", "ID Turma", "Data", "Presente"};
        Object[][] dados = new Object[lista.size()][4];

        for (int i = 0; i < lista.size(); i++) {
            presenca p = lista.get(i);
            dados[i][0] = p.getId_Aluno();
            dados[i][1] = p.getId_Turma();
            dados[i][2] = p.getData();
            dados[i][3] = p.getPresente();
        }

        DefaultTableModel model = new DefaultTableModel(dados, colunas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 3; // Só coluna "Presente" editável
            }
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 3) return Boolean.class;
                return String.class;
            }
        };

        JTable tabela = new JTable(model);
        tabela.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(tabela);
        scrollPane.setPreferredSize(new java.awt.Dimension(500, 250));

        int result = JOptionPane.showConfirmDialog(
            null, scrollPane, "Lista de Presença", 
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE
        );

        List<presenca> novaLista = new ArrayList<>();
        if (result == JOptionPane.OK_OPTION) {
            for (int i = 0; i < model.getRowCount(); i++) {
                presenca p = new presenca();
                p.setId_Aluno((int) model.getValueAt(i, 0));
                p.setId_Turma((int) model.getValueAt(i, 1));
                p.setData((String) model.getValueAt(i, 2));
                p.setPresente((Boolean) model.getValueAt(i, 3));
                novaLista.add(p);
            }
        }
        return novaLista;
    }
}
