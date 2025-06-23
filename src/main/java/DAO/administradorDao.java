package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Database.databaseconn;
import Model.admin;
import Model.pessoa;

public class administradorDao extends pessoaDao {
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

    public List<admin> buscarTodoAdmin() {
        List<admin> admins = new ArrayList<>();
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
                admin admin = new admin ();
                admin.setDdd(rs.getInt("DDD_Telefone"));
                admin.setEmail(rs.getString("Email"));
                admin.setNome(rs.getString("Nome"));
                admin.setPapel(rs.getInt("Papel"));
                admin.setTelefone(rs.getInt("Telefone"));
                admin.setUsername(rs.getString("Nome_de_Usuario"));
                admin.setId(rs.getInt("Id_Pessoa"));
                admin.setId_Admininstrador(rs.getInt("Id_admin"));
                admins.add(admin);
            }
            statement.close();
            rs.close();
            bd.connection.close();
        } catch(Exception erro) {
            JOptionPane.showMessageDialog(null, "Algo de errado aconteceu no cadastro:\n " + erro.toString());
        }

        return admins;
    }
    
    private String buscarTodosQuery() {
        return "select pes.*, adm.id_administrador from administrador adm	join pessoa pes on pes.id_pessoa = alu.id_pessoa";
    }
    
    private String buscarQuery() {
        return "select pes.* from administrador adm join pessoa pes on pes.id_pessoa = adm.id_pessoa WHERE pes.Nome_de_Usuario = ?";
    }

    
    private String cadastrarQuery() {
        return "INSERT INTO Administrador (Id_Administrador, Id_Pessoa) SELECT nextval('Administrador_Id_Administrador_seq'), Id_Pessoa FROM pessoa where Nome_de_Usuario = ?";
    }
    
}
    
    

