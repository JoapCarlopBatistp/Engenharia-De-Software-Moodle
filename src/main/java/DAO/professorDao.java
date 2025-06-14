package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Database.databaseconn;
import Model.pessoa;
import Model.professor;

public class professorDao extends pessoaDao{

    public void cadastrar(pessoa pessoa){
        databaseconn bd = new databaseconn();
        PreparedStatement statement;
        super.cadastrar(pessoa);
        try {
            if(!bd.getConnection()){
                JOptionPane.showMessageDialog(null, "Falha na conexão, o sistem será fechado!");
                System.exit(0);
            }

            String url = this.cadastrarQuery();
            statement = bd.connection.prepareStatement(url);
            statement.setString(1, pessoa.getUsername());
            statement.executeUpdate();
            statement.close();
            bd.connection.close();
        } catch(Exception erro) {
            JOptionPane.showMessageDialog(null, "Algo de errado aconteceu:\n " + erro.toString());
            System.out.println(erro.toString());
        }
    }

    public pessoa buscar(String username) {
       return super.buscar(username, this.buscarQuery());
    }

    public List<professor> buscarTodosProfessores() {
        List<professor> professores = new ArrayList<>();
        databaseconn bd = new databaseconn();
        PreparedStatement statement;
        ResultSet rs = null;
        try {
            if(!bd.getConnection()){
                JOptionPane.showMessageDialog(null, "Falha na conexão, o sistem será fechado!");
                System.exit(0);
            }

            String url = this.buscarTodosQuery();
            statement = bd.connection.prepareStatement(url);
            rs = statement.executeQuery();

            while (rs.next()) {
                professor professor = new professor ();
                professor.setDdd(rs.getInt("DDD_Telefone"));
                professor.setEmail(rs.getString("Email"));
                professor.setNome(rs.getString("Nome"));
                professor.setPapel(rs.getInt("Papel"));
                professor.setTelefone(rs.getInt("Telefone"));
                professor.setUsername(rs.getString("Nome_de_Usuario"));
                professor.setId(rs.getInt("Id_Pessoa"));
                professor.setId_Professor(rs.getInt("Id_Professor"));
                professores.add(professor);
            }
            statement.close();
            bd.connection.close();
        } catch(Exception erro) {
            JOptionPane.showMessageDialog(null, "Algo de errado aconteceu no cadastro:\n " + erro.toString());
        }

        return professores;
    }

     private String buscarTodosQuery() {
        return "select pes.*, pro.id_professor from professor pro	join pessoa pes on pes.id_pessoa = pro.id_pessoa";
    }
    
    private String buscarQuery() {
        return "select pes.* from professor pro join pessoa pes on pes.id_pessoa = pro.id_pessoa WHERE pes.Nome_de_Usuario = ?";
    }

    
    private String cadastrarQuery() {
        return "INSERT INTO Professor (Id_Professor, Id_Pessoa) SELECT nextval('Professor_Id_Professor_seq'), Id_Pessoa FROM pessoa where Nome_de_Usuario = ?";
    }
    
}
