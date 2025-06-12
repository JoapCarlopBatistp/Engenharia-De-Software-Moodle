package DAO;

import java.sql.PreparedStatement;

import javax.swing.JOptionPane;

import Database.databaseconn;
import Model.pessoa;

public class pessoaDao implements dao {

    public void cadastrar(pessoa pessoa){
        databaseconn bd = new databaseconn();
        PreparedStatement statement;
        try {
            if(!bd.getConnection()){
                JOptionPane.showMessageDialog(null, "Falha na conexão, o sistem será fechado!");
                System.exit(0);
            }

            String url = this.cadastrarQuery();
            statement = bd.connection.prepareStatement(url);
            statement.setString(1, pessoa.getNome());
            statement.setInt(2, pessoa.getDdd());
            statement.setInt(3, pessoa.getTelefone());
            statement.setString(4, pessoa.getUsername());
            statement.setString(5, new String(pessoa.getPassword()));
            statement.setString(6, pessoa.getEmail());
            statement.setLong(7, pessoa.getPapel());
            statement.executeUpdate();
            statement.close();
            bd.connection.close();
        } catch(Exception erro) {
            JOptionPane.showMessageDialog(null, "Algo de errado aconteceu:\n " + erro.toString());
            System.out.println(erro.toString());
        }
    }

    private String cadastrarQuery() {
        return "INSERT INTO Pessoa (Id_Pessoa, Nome, DDD_Telefone, Telefone, Nome_de_Usuario, Senha, Email, Papel) VALUES (nextval('Pessoa_Id_Pessoa_seq'),?,?,?,?,?,?,?)";
    }

    public pessoaDao() {}
    
}
