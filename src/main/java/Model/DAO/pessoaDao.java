package Model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Model.pessoa;
import Model.Database.databaseconn;

public class pessoaDao{

    public void cadastrar(pessoa pessoa){
        databaseconn bd = new databaseconn();
        PreparedStatement statement;
        try {
            if(!bd.getConnection()){
                JOptionPane.showMessageDialog(null, "Falha na conexão, o sistem será fechado!");
                System.exit(0);
            }

            statement = bd.connection.prepareStatement(this.cadastrarQuery());
            statement.setString(1, pessoa.getNome());
            statement.setInt(2, pessoa.getDdd());
            statement.setInt(3, pessoa.getTelefone());
            statement.setString(4, pessoa.getUsername());
            statement.setString(5, new String(pessoa.getPassword()));
            statement.setString(6, pessoa.getEmail());
            statement.setLong(7, pessoa.getPapel());
            statement.executeUpdate();
            statement.close();
           bd.close();
        } catch(Exception erro) {
            JOptionPane.showMessageDialog(null, "Algo de errado aconteceu:\n " + erro.toString());
            System.out.println(erro.toString());
        }
    }

    public pessoa buscar(String username, String query) {
        pessoa pessoa = new pessoa ();
        databaseconn bd = new databaseconn();
        PreparedStatement statement;
        ResultSet rs = null;
        try {
            if(!bd.getConnection()){
                JOptionPane.showMessageDialog(null, "Falha na conexão, o sistem será fechado!");
                System.exit(0);
            }
            
            statement = bd.connection.prepareStatement(query);
            statement.setString(1, username);
            rs = statement.executeQuery();

            while (rs.next()) {
                pessoa.setDdd(rs.getInt("DDD_Telefone"));
                pessoa.setEmail(rs.getString("Email"));
                pessoa.setNome(rs.getString("Nome"));
                pessoa.setPapel(rs.getInt("Papel"));
                pessoa.setTelefone(rs.getInt("Telefone"));
                pessoa.setUsername(rs.getString("Nome_de_Usuario"));
            }
            statement.close();
            rs.close();
            bd.close();
        } catch(Exception erro) {
            JOptionPane.showMessageDialog(null, "Algo de errado aconteceu no cadastro:\n " + erro.toString());
        }

        return pessoa;
    }

    public List<pessoa> buscarTodos() {
        List<pessoa> pessoas = new ArrayList<pessoa>();
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
                pessoa pessoa = new pessoa ();
                pessoa.setDdd(rs.getInt("DDD_Telefone"));
                pessoa.setEmail(rs.getString("Email"));
                pessoa.setNome(rs.getString("Nome"));
                pessoa.setPapel(rs.getInt("Papel"));
                pessoa.setTelefone(rs.getInt("Telefone"));
                pessoa.setUsername(rs.getString("Nome_de_Usuario"));
                pessoa.setId(rs.getInt("Id_Pessoa"));
                pessoas.add(pessoa);
            }
            statement.close();
            rs.close();
            bd.close();
        } catch(Exception erro) {
            JOptionPane.showMessageDialog(null, "Algo de errado aconteceu no cadastro:\n " + erro.toString());
        }

        return pessoas;
    }

     private String buscarTodosQuery() {
        return "SELECT * FROM Pessoa";
    }

    private String cadastrarQuery() {
        return "INSERT INTO Pessoa (Id_Pessoa, Nome, DDD_Telefone, Telefone, Nome_de_Usuario, Senha, Email, Papel) VALUES (nextval('Pessoa_Id_Pessoa_seq'),?,?,?,?,?,?,?)";
    }

    public pessoaDao() {}
    
}
