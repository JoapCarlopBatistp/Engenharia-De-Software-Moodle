package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Database.databaseconn;
import Model.aluno;
import Model.pessoa;

public class alunoDao extends pessoaDao{

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

    public List<aluno> buscarTodoAlunos() {
        List<aluno> alunos = new ArrayList<>();
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
                aluno aluno = new aluno ();
                aluno.setDdd(rs.getInt("DDD_Telefone"));
                aluno.setEmail(rs.getString("Email"));
                aluno.setNome(rs.getString("Nome"));
                aluno.setPapel(rs.getInt("Papel"));
                aluno.setTelefone(rs.getInt("Telefone"));
                aluno.setUsername(rs.getString("Nome_de_Usuario"));
                aluno.setId(rs.getInt("Id_Pessoa"));
                aluno.setId_Aluno(rs.getInt("Id_Aluno"));
                alunos.add(aluno);
            }
            statement.close();
            bd.connection.close();
        } catch(Exception erro) {
            JOptionPane.showMessageDialog(null, "Algo de errado aconteceu no cadastro:\n " + erro.toString());
        }

        return alunos;
    }

     private String buscarTodosQuery() {
        return "select pes.*, alu.id_aluno from aluno alu	join pessoa pes on pes.id_pessoa = alu.id_pessoa";
    }
    
    private String buscarQuery() {
        return "select pes.* from aluno alu join pessoa pes on pes.id_pessoa = alu.id_pessoa WHERE pes.Nome_de_Usuario = ?";
    }

    
    private String cadastrarQuery() {
        return "INSERT INTO Aluno (Id_Aluno, Id_Pessoa) SELECT nextval('Aluno_Id_Aluno_seq'), Id_Pessoa FROM pessoa where Nome_de_Usuario = ?";
    }
    
}
