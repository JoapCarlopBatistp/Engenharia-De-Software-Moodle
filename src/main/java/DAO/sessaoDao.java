package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import Database.databaseconn;
import Model.pessoa;
import Model.sessao;

public class sessaoDao {

    public sessao login(String username, String password) throws Exception {
        
        pessoa pessoa = new pessoa ();
        try {
                PreparedStatement statement = null;
                ResultSet resultSet = null;
                databaseconn bd = new databaseconn();
                if(!bd.getConnection()){
                    JOptionPane.showMessageDialog(null, "Falha na conexão, o sistem será fechado!");
                    System.exit(0);
                }
                statement = bd.connection.prepareStatement("SELECT * FROM pessoa WHERE nome_de_usuario=? AND senha=?");
                statement.setString(1, username);
                statement.setString(2, password);
                resultSet = statement.executeQuery();
                if (!resultSet.next()) throw new Exception("Usuário e/ou senha inválidos.");
                
                pessoa.setDdd(resultSet.getInt("DDD_Telefone"));
                pessoa.setEmail(resultSet.getString("Email"));
                pessoa.setNome(resultSet.getString("Nome"));
                pessoa.setPapel(resultSet.getInt("Papel"));
                pessoa.setTelefone(resultSet.getInt("Telefone"));
                pessoa.setUsername(resultSet.getString("Nome_de_Usuario"));
                pessoa.setId(resultSet.getInt("Id_Pessoa"));
                resultSet.close();
                statement.close();
                bd.close();
            } catch(Exception erro) {
                throw erro;
            }
            return new sessao(pessoa);
    } 
    
}
