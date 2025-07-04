package Model.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class databaseconn {

    public Connection connection = null;
    private final String DRIVER =  "org.postgresql.Driver";
    private final String DBNAME = "Trab_EngSoftware";
    private final String URL = "jdbc:postgresql://localhost:5432/" + DBNAME;
    private final String LOGIN = "postgres";
    private final String SENHA = "1234";

    public boolean getConnection(){
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, LOGIN, SENHA);
            System.out.println("Conectou no banco de dados");
            return true;
        } catch (ClassNotFoundException erro){
            System.out.println("Não foi possível conectar encontrar a classe do banco");
            return false;
        } catch (SQLException erro){
            System.out.println("Erro durante o sql");
            return false;
        }
    }

    public void close(){
        try {
            connection.close();
            System.out.println("Desconectou");
        } catch (SQLException erro){
            JOptionPane.showMessageDialog(null, "Problemas na conexão\n" + erro.toString());
        }
    }
}
