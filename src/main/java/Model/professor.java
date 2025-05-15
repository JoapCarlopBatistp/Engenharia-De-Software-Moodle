package Model;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import Database.databaseconn;

public class professor {
    public void cadastrar(final pessoa pessoa) throws SQLException {
        databaseconn bd = new databaseconn();
        PreparedStatement statement;
        try {
            if(!bd.getConnection()){
                JOptionPane.showMessageDialog(null, "Falha na conexão, o sistem será fechado!");
                System.exit(0);
            }

            String url = "INSERT INTO Professor (Id_Professor, Id_Pessoa) SELECT nextval('Professor_Id_Professor_seq'), Id_Pessoa FROM pessoa where Nome_de_Usuario = ?;";
            statement = bd.connection.prepareStatement(url);
            statement.setString(1, pessoa.getUsername());
            statement.executeUpdate();
            statement.close();
            bd.close();
        } catch(SQLException erro) {
            bd.connection.rollback();
            JOptionPane.showMessageDialog(null, "Algo de errado aconteceu:\n " + erro.toString());
            System.out.println(erro.toString());
        }
    }
}
