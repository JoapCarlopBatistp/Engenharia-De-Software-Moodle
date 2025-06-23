package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Database.databaseconn;
import Model.sala;
import Model.turma;
import Model.professor;
import Model.sala;

public class turmaDao {
     public void cadastrar(turma turma){
        databaseconn bd = new databaseconn();
        PreparedStatement statement;
        String horarioEscolhido = turma.getHorario();
        String horarioParaInsert = null;
        try {
            if(!bd.getConnection()){
                JOptionPane.showMessageDialog(null, "Falha na conexão, o sistem será fechado!");
                System.exit(0);
            }

            //checa se o formato da data é valido
            int firstColon = horarioEscolhido.indexOf(':');
            int secondColon = horarioEscolhido.indexOf(':', firstColon + 1);
            if (secondColon == -1) {
                horarioParaInsert = horarioEscolhido + ":00";
            } else if(secondColon != -1) {
                horarioParaInsert = horarioEscolhido;
            } else {
                throw new Exception("Formato do horário inválido");
            }


            statement = bd.connection.prepareStatement(this.cadastrarQuery());
            statement.setString(1, turma.getSemestre());
            statement.setInt(2, turma.getVagas_disponibilizadas());
            statement.setInt(3, turma.getVagas_ocupadas());
            statement.setString(4, turma.getDias());
            statement.setTime(5, Time.valueOf(horarioParaInsert));
            statement.setInt(6, turma.getId_sala());
            statement.setInt(7, turma.getId_cadeira());
            statement.setInt(8, turma.getId_professor());
            statement.executeUpdate();
            statement.close();
           bd.close();

        } catch(Exception erro) {
            JOptionPane.showMessageDialog(null, "Algo de errado aconteceu:\n " + erro.toString());
            System.out.println(erro.toString());
        }
    }

    public List<turma> buscarTodasTurmas(boolean buscarApenasComVagasDisponiveis) {
        List<turma> turmas = new ArrayList<turma>();
        databaseconn bd = new databaseconn();
        PreparedStatement statement;
        ResultSet rs = null;
        try {
            if(!bd.getConnection()){
                JOptionPane.showMessageDialog(null, "Falha na conexão, o sistem será fechado!");
                System.exit(0);
            }

            statement = bd.connection.prepareStatement(this.buscarTodosQuery(buscarApenasComVagasDisponiveis));
            rs = statement.executeQuery();

            while (rs.next()) {
                turma turma = new turma ();
                turma.setId_turma(rs.getInt("Id_Turma"));
                turma.setSemestre(rs.getString("semestre"));
                turma.setVagas_disponibilizadas(rs.getInt("vagas_disponibilizadas"));
                turma.setVagas_ocupadas(rs.getInt("vagas_ocupadas"));
                turma.setDias(rs.getString("dias"));
                turma.setHorario(rs.getString("horario"));
                turma.setId_sala(rs.getInt("id_sala"));
                turma.setId_cadeira(rs.getInt("id_cadeira"));
                turma.setId_professor(rs.getInt("id_professor"));

                turmas.add(turma);
            }
            statement.close();
            rs.close();
            bd.close();
        } catch(Exception erro) {
            JOptionPane.showMessageDialog(null, "Algo de errado aconteceu no cadastro:\n " + erro.toString());
        }

        return turmas;
    }

     public void alocarSala(sala sala, turma turma) throws Exception {
        databaseconn bd = new databaseconn();
        PreparedStatement statement;
        try {
            if(!bd.getConnection()){
                JOptionPane.showMessageDialog(null, "Falha na conexão, o sistem será fechado!");
                System.exit(0);
            }

            statement = bd.connection.prepareStatement(this.alocarSalaQuery());
            statement.setInt(1, sala.getId_Sala());
            statement.setInt(2, turma.getId_turma());
            statement.executeUpdate();
            statement.close();
           bd.close();
        } catch(Exception erro) {
            throw erro;
        }
    }

    public boolean verificaDisponibilidadeSala(turma turmaParaIncluirASala, sala salaEscolhida) {
        boolean retorno = false;
        databaseconn bd = new databaseconn();
        PreparedStatement statement;
        ResultSet rs = null;
        try {
            if(!bd.getConnection()){
                JOptionPane.showMessageDialog(null, "Falha na conexão, o sistem será fechado!");
                System.exit(0);
            }

            statement = bd.connection.prepareStatement(this.buscarTodasSalasNoMesmoHorarioQuery());
            statement.setTime(1, Time.valueOf(turmaParaIncluirASala.getHorario()));
            statement.setString(2, "%"+turmaParaIncluirASala.getDias()+"%");
            statement.setInt(3, salaEscolhida.getId_Sala());
            statement.setString(4, turmaParaIncluirASala.getSemestre());
            
            
            rs = statement.executeQuery();
            if(!rs.next()) retorno = true;
            statement.close();
            rs.close();
            bd.close();
        } catch(Exception erro) {
            JOptionPane.showMessageDialog(null, "Algo de errado aconteceu no cadastro:\n " + erro.toString());
        }

        return retorno;
    }

    private String buscarTodosQuery(boolean buscarApenasComVagasDisponiveis) {
        StringBuffer query = new StringBuffer();
        query.append("select * from turma tur");
        if(buscarApenasComVagasDisponiveis) {
            query.append("where tur.vagas_disponibilizadas > tur.vagas_ocupadas");
        }
        return query.toString();
    }

    private String cadastrarQuery() {
        return "insert " +
                    "into " +
                    "turma " +
                    "(Id_Turma, " +
                    "semestre, " +
                    "vagas_disponibilizadas, " +
                    "vagas_ocupadas, " +
                    "dias, " +
                    "horario, " +
                    "id_sala, " +
                    "id_cadeira, "+
                    "id_professor) " +
                    "values(nextval('turma_Id_Turma_seq'), ?, ?, ?, ?, ?, ?, ?, ?)";

    }    

    public void alocarProfessor(professor professor, turma turma) throws Exception {
        databaseconn bd = new databaseconn();
        PreparedStatement statement;
        try {
            if(!bd.getConnection()){
                JOptionPane.showMessageDialog(null, "Falha na conexão, o sistem será fechado!");
                System.exit(0);
            }

            statement = bd.connection.prepareStatement(this.alocarProfessorQuery());
            statement.setInt(1, professor.getId_Professor());
            statement.setInt(2, turma.getId_turma());
            statement.executeUpdate();
            statement.close();
           bd.close();
        } catch(Exception erro) {
            throw erro;
        }
    }

    private String alocarProfessorQuery() {
        return "update turma " +
                "set id_professor = ? " +
                "where id_turma = ?";

    }

    private String alocarSalaQuery() {
        return "update turma " +
                "set id_sala = ? " +
                "where id_turma = ?";

    }

    private String buscarTodasSalasNoMesmoHorarioQuery() {
        return "select " +
                    "tur.id_turma " +
                "from " +
                    "turma tur " +
                    "left join sala sal on sal.id_sala = tur.id_sala " +
                "where " +
                    "tur.horario = ? " +
                    "and tur.dias like ? " +
                    "and sal.id_sala = ?" +
                    "and tur.semestre = ?";
    }
}
