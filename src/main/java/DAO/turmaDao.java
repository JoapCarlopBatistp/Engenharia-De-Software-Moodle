package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Database.databaseconn;
import Model.turma;

public class turmaDao {
    public void cadastrar(turma turma){

        databaseconn bd = new databaseconn();
        PreparedStatement statement;
        try {
            if(!bd.getConnection()){
                JOptionPane.showMessageDialog(null, "Falha na conexão, o sistem será fechado!");
                System.exit(0);
            }

             statement = bd.connection.prepareStatement(this.cadastrarQuery());             
             statement.setString(1, turma.getSemestre());
             statement.setInt(2, turma.getVagas_disponibilizadas());
             statement.setInt(3, turma.getVagas_ocupadas());
             statement.setString(4, turma.getDias());
             statement.setString(5, turma.getHorario());
             statement.executeUpdate();
             statement.close();
             bd.connection.close();
        } catch(Exception erro) {
            JOptionPane.showMessageDialog(null, "Algo de errado aconteceu:\n " + erro.toString());
            System.out.println(erro.toString());
        }
    }

    public turma buscar(int id_Turma, String query) {
        turma turma = new turma ();
        databaseconn bd = new databaseconn();
        PreparedStatement statement;
        ResultSet rs = null;
        
        try {
            if(!bd.getConnection()){
                JOptionPane.showMessageDialog(null, "Falha na conexão, o sistem será fechado!");
                System.exit(0);
            }
            
            statement = bd.connection.prepareStatement(query);
            statement.setInt(1, id_Turma);
            rs = statement.executeQuery();
         
            while (rs.next()) {                
                turma.setSemestre(rs.getString("Semestre"));  
                turma.setVagas_disponibilizadas(rs.getInt("Vagas_Disponibilizadas"));
                turma.setVagas_ocupadas(rs.getInt("Vagas_Ocupadas")); 
                turma.setDias(rs.getString("Dias"));
                turma.setHorario(rs.getString("Horario"));     
            }
            statement.close();
            bd.connection.close();
        } catch(Exception erro) {
            JOptionPane.showMessageDialog(null, "Algo de errado aconteceu no cadastro:\n " + erro.toString());
        }

        return turma;
    }
 
    public List<turma> buscarTodos() {
        List<turma> turmas = new ArrayList<>();
        databaseconn bd = new databaseconn();
        PreparedStatement statement;
        ResultSet rs = null;
        try {
            if(!bd.getConnection()){
                JOptionPane.showMessageDialog(null, "Falha na conexão, o sistem será fechado!");
                System.exit(0);
            }

            statement = bd.connection.prepareStatement(this.buscarTodosQuery());
            rs = statement.executeQuery();

            while (rs.next()) {
                turma turma = new turma ();               
                turma.setSemestre(rs.getString("Semestre"));  
                turma.setVagas_disponibilizadas(rs.getInt("Vagas_Disponibilizadas"));
                turma.setVagas_ocupadas(rs.getInt("Vagas_Ocupadas")); 
                turma.setDias(rs.getString("Dias"));
                turma.setHorario(rs.getString("Horario"));     
                turmas.add(turma);
            }
            statement.close();
            bd.connection.close();
        } catch(Exception erro) {
            JOptionPane.showMessageDialog(null, "Algo de errado aconteceu no cadastro:\n " + erro.toString());
        }

        return turmas;
    }

    private String buscarTodosQuery() {
        return "SELECT * FROM Turma";
    }

    private String cadastrarQuery() {
        return "INSERT INTO Sala (Id_Turma, Semestre, Vagas_Disponibilizadas, Vagas_Ocupadas, Dias, Horario, Id_Sala, Id_Cadeira) VALUES (nextval('Turma_Id_Turma_seq'),?,?,?,?,?,?,?)";
    }

    public turmaDao() {}
    
}  
    

