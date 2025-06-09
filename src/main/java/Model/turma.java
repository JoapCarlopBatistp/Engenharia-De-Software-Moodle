package Model;

import Database.databaseconn;

import javax.swing.*;
import java.sql.PreparedStatement;

public class turma {
    public void cadastrar(final Integer capacidade) {
        databaseconn bd = new databaseconn();
        PreparedStatement statement;
        try {
            if(!bd.getConnection()){
                JOptionPane.showMessageDialog(null, "Falha na conexão, o sistem será fechado!");
                System.exit(0);
            }

            String url = "INSERT INTO Turma (Id_Turma,Capacidade) VALUES (nextval('Sala_Id_Turma_seq'),?)";
            statement = bd.connection.prepareStatement(url);
            statement.setInt(1, capacidade);
            statement.executeUpdate();
            statement.close();
            bd.close();
        } catch(Exception erro) {
            JOptionPane.showMessageDialog(null, "Algo de errado aconteceu:\n " + erro.toString());
            System.out.println(erro.toString());
        }

    }
}
