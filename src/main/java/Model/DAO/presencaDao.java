package Model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Model.turma;
import Model.Database.databaseconn;
import Model.presenca;

public class presencaDao {
    public List<presenca> buscarPresencasPorTurma(turma turma){
        databaseconn bd = new databaseconn();
        PreparedStatement statement;
        List<presenca> presencas= new ArrayList<>();
        ResultSet rs = null;
        try {
            if(!bd.getConnection()){
                JOptionPane.showMessageDialog(null, "Falha na conexão, o sistem será fechado!");
                System.exit(0);
            }

            statement = bd.connection.prepareStatement(this.buscarPresencaTurmaQuery());
            statement.setInt(1,turma.getId_turma());
            rs = statement.executeQuery();
            if (rs.next()) {
                presenca presenca = new presenca();
                presenca.setId_Aluno(rs.getInt("Id_Turma"));
                presenca.setData(rs.getString("data"));
                presenca.setId_Turma(rs.getInt("id_turma"));
                presenca.setPresente(rs.getBoolean("presente"));
                presencas.add(presenca);
            }
            statement.close();
            rs.close();
            bd.close();
        } catch(Exception erro) {
            JOptionPane.showMessageDialog(null, "Algo de errado aconteceu no cadastro:\n " + erro.toString());
        }

        return presencas;
    }

    public void salvarPresencas(List<presenca> presencas) {
        databaseconn bd = new databaseconn();
        PreparedStatement statement = null;
        try {
            if(!bd.getConnection()){
                JOptionPane.showMessageDialog(null, "Falha na conexão, o sistem será fechado!");
                System.exit(0);
            }
            statement = bd.connection.prepareStatement(this.InsertPresencaQuery());

            for(int i = 0; i < presencas.size(); i++ ) {
                statement.setInt(1,presencas.get(i).getId_Aluno());
                statement.setInt(2, presencas.get(i).getId_Turma());
                statement.setBoolean(3,presencas.get(i).getPresente());
                statement.addBatch();
            }
            statement.executeBatch();

            statement.close();
            bd.close();
        } catch(Exception erro) {
            JOptionPane.showMessageDialog(null, "Algo de errado aconteceu no cadastro:\n " + erro.toString());
        }
    }

    private String buscarPresencaTurmaQuery() {
        return "select\r\n" + //
                        "\tpre.*\r\n" + //
                        "from\r\n" + //
                        "\tpresenca pre\r\n" + //
                        "\tjoin turma tur on tur.id_turma = pre.id_turma\r\n" + //
                        "where\r\n" + //
                        "\ttur.id_turma = ?";
    }

    private String InsertPresencaQuery() {
        return "INSERT INTO presenca " + //
                    "(id_aluno, id_turma, data, presente) " +
                    "VALUES( ?, ?, current_date, ?)";
    }

}
