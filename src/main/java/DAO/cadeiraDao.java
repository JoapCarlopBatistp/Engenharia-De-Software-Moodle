package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Database.databaseconn;
import Model.cadeira;

public class cadeiraDao {
    public void cadastrar(cadeira cadeira){

        databaseconn bd = new databaseconn();
        PreparedStatement statement;
        try {
            if(!bd.getConnection()){
                JOptionPane.showMessageDialog(null, "Falha na conexão, o sistem será fechado!");
                System.exit(0);
            }

             statement = bd.connection.prepareStatement(this.cadastrarQuery());             
             statement.setString(1, cadeira.getNome_Cadeira());
             statement.setString(2, cadeira.getCodigo_Cadeira());
             statement.executeUpdate();
             statement.close();
             bd.connection.close();
        } catch(Exception erro) {
            JOptionPane.showMessageDialog(null, "Algo de errado aconteceu:\n " + erro.toString());
            System.out.println(erro.toString());
        }
    }

    public cadeira buscar(String Codigo_Cadeira, String query) {
        cadeira cadeira = new cadeira ();
        databaseconn bd = new databaseconn();
        PreparedStatement statement;
        ResultSet rs = null;
        
        try {
            if(!bd.getConnection()){
                JOptionPane.showMessageDialog(null, "Falha na conexão, o sistem será fechado!");
                System.exit(0);
            }
            
            statement = bd.connection.prepareStatement(query);
            statement.setString(1, Codigo_Cadeira);
            rs = statement.executeQuery();
         
            while (rs.next()) {                
                cadeira.setNome_Cadeira(rs.getString("Nome"));
                cadeira.setCodigo_Cadeira(rs.getString("Codigo"));                
            }
            statement.close();
            bd.connection.close();
        } catch(Exception erro) {
            JOptionPane.showMessageDialog(null, "Algo de errado aconteceu no cadastro:\n " + erro.toString());
        }

        return cadeira;
    }
 
    public List<cadeira> buscarTodos() {
        List<cadeira> cadeiras = new ArrayList<>();
        databaseconn bd = new databaseconn();
        PreparedStatement statement;
        ResultSet rs = null;
        try {
            if(!bd.getConnection()){
                JOptionPane.showMessageDialog(null, "Falha na conexão, o sistem será fechado!");
                System.exit(0);
            }

            statement = bd.connection.prepareStatement(this.buscaCadeirasQuery());
            rs = statement.executeQuery();

            while (rs.next()) {
                cadeira cadeira = new cadeira ();               
                cadeira.setNome_Cadeira(rs.getString("Nome")); 
                cadeira.setCodigo_Cadeira(rs.getString("Codigo")); 
                cadeira.setId_Cadeira(rs.getInt("Id_Cadeira"));
                cadeiras.add(cadeira);
            }
            statement.close();
            bd.connection.close();
        } catch(Exception erro) {
            JOptionPane.showMessageDialog(null, "Algo de errado aconteceu no cadastro:\n " + erro.toString());
        }

        return cadeiras;
    }

    private String cadastrarQuery() {
        return "INSERT INTO cadeira (Id_cadeira,nome,codigo) VALUES (nextval('cadeira_Id_cadeira_seq'),?,?)";
    }
    
    private String buscaCadeirasQuery() {
        return "select * from cadeira";
    }
}
