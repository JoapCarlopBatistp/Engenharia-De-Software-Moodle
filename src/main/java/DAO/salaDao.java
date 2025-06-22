package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Database.databaseconn;
import Model.sala;

public class salaDao {
    
    public void cadastrar(final Integer capacidade) {
        databaseconn bd = new databaseconn();
        PreparedStatement statement;
        try {
            if(!bd.getConnection()){
                JOptionPane.showMessageDialog(null, "Falha na conexão, o sistem será fechado!");
                System.exit(0);
            }

            statement = bd.connection.prepareStatement(this.cadastrarQuery());
            statement.setInt(1, capacidade);
            statement.executeUpdate();
            statement.close();
            bd.close();
        } catch(Exception erro) {
            JOptionPane.showMessageDialog(null, "Algo de errado aconteceu no cadastro da sala:\n " + erro.toString());
            System.out.println(erro.toString());
        }

    }

    public List<sala> buscaSalas() {
        List<sala> salas = new ArrayList<sala>();
        databaseconn bd = new databaseconn();
        PreparedStatement statement;
        ResultSet rs = null;
        try {
            if(!bd.getConnection()){
                JOptionPane.showMessageDialog(null, "Falha na conexão, o sistem será fechado!");
                System.exit(0);
            }

            statement = bd.connection.prepareStatement(this.buscaTodasSalas());
            rs = statement.executeQuery();

            while (rs.next()) {
                sala sala = new sala ();
                sala.setId_sala(rs.getInt("Id_Sala"));
                sala.setCapacidade(rs.getInt("capacidade"));
                salas.add(sala);
            }
            statement.close();
           bd.close();
        } catch(Exception erro) {
            JOptionPane.showMessageDialog(null, "Algo de errado aconteceu no cadastro:\n " + erro.toString());
        }

        return salas;
    }

    public List<sala> buscaSalasDisponivelNoHorario(String horario) {
        List<sala> salas = new ArrayList<sala>();
        databaseconn bd = new databaseconn();
        PreparedStatement statement;
        ResultSet rs = null;
        try {
            if(!bd.getConnection()){
                JOptionPane.showMessageDialog(null, "Falha na conexão, o sistem será fechado!");
                System.exit(0);
            }

            statement = bd.connection.prepareStatement(this.buscaSalasDisponivelNoHorarioQuery());
            statement.setString(1, horario);
            rs = statement.executeQuery();

            while (rs.next()) {
                sala sala = new sala ();
                sala.setId_sala(rs.getInt("Id_Sala"));
                sala.setCapacidade(rs.getInt("capacidade"));
                salas.add(sala);
            }
            statement.close();
           bd.close();
        } catch(Exception erro) {
            JOptionPane.showMessageDialog(null, "Algo de errado aconteceu no cadastro:\n " + erro.toString());
        }

        return salas;
    }

    private String cadastrarQuery() {
        return "INSERT INTO Sala (Id_Sala,Capacidade) VALUES (nextval('Sala_Id_Sala_seq'),?)";
    }

    private String buscaTodasSalas() {
        return "select " + //
                "* " + //
                "from " + //
                "sala ";
    }

    private String buscaSalasDisponivelNoHorarioQuery() {
        return "select " +
                    "sala.* " +
                "from " +
                    "sala sala " +
                    "left join turma tur on tur.id_sala = sala.id_sala " +
                "where " +
                   "tur.id_sala is null " +
                    "and tur.horario = ? " +
                "order by sala.id_sala";
    }

}
