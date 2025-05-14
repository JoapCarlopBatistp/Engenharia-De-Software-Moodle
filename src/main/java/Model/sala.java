package main.java.Model;

import main.java.Database.databaseconn;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class sala {
    public void cadastrar(final Integer capacidade) {
        databaseconn bd = new databaseconn();
        PreparedStatement statement;
        try {
            if(!bd.getConnection()){
                JOptionPane.showMessageDialog(null, "Falha na conexão, o sistem será fechado!");
                System.exit(0);
            }

            String url = "INSERT INTO Sala (Id_Sala,Capacidade) VALUES (nextval('Sala_Id_Sala_seq'),?)";
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
