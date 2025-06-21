package Database;

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
    private final String SENHA = "";

    public boolean getConnection(){
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, LOGIN, SENHA);
            return true;
        } catch (ClassNotFoundException erro){
            return false;
        } catch (SQLException erro){
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
