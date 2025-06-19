package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Database.databaseconn;
import Model.sala;

public class salaDao {
    
    public void cadastrar(sala sala){

        databaseconn bd = new databaseconn();
        PreparedStatement statement;
        try {
            if(!bd.getConnection()){
                JOptionPane.showMessageDialog(null, "Falha na conexão, o sistem será fechado!");
                System.exit(0);
            }

             statement = bd.connection.prepareStatement(this.cadastrarQuery());             
             statement.setInt(1, sala.getCapacidade_Sala());
             statement.executeUpdate();
             statement.close();
             bd.connection.close();
        } catch(Exception erro) {
            JOptionPane.showMessageDialog(null, "Algo de errado aconteceu:\n " + erro.toString());
            System.out.println(erro.toString());
        }
    }

    public sala buscar(int id_Sala, String query) {
        sala sala = new sala ();
        databaseconn bd = new databaseconn();
        PreparedStatement statement;
        ResultSet rs = null;
        
        try {
            if(!bd.getConnection()){
                JOptionPane.showMessageDialog(null, "Falha na conexão, o sistem será fechado!");
                System.exit(0);
            }
            
            statement = bd.connection.prepareStatement(query);
            statement.setInt(1, id_Sala);
            rs = statement.executeQuery();
         
            while (rs.next()) {                
                sala.setCapacidade_Sala(rs.getInt("Capacidade"));                
            }
            statement.close();
            bd.connection.close();
        } catch(Exception erro) {
            JOptionPane.showMessageDialog(null, "Algo de errado aconteceu no cadastro:\n " + erro.toString());
        }

        return sala;
    }
 
    public List<sala> buscarTodos() {
        List<sala> salas = new ArrayList<>();
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
                sala sala = new sala ();               
                sala.setCapacidade_Sala(rs.getInt("Capacidade")); 
                sala.setCapacidade_Sala(rs.getInt("Id_Sala"));
                salas.add(sala);
            }
            statement.close();
            bd.connection.close();
        } catch(Exception erro) {
            JOptionPane.showMessageDialog(null, "Algo de errado aconteceu no cadastro:\n " + erro.toString());
        }

        return salas;
    }

    private String buscarTodosQuery() {
        return "SELECT * FROM Sala";
    }

    private String cadastrarQuery() {
        return "INSERT INTO Sala (Id_Sala, Capacidade) VALUES (nextval('Sala_Id_Sala_seq'),?)";
    }

    public salaDao() {}
    
}  
    

