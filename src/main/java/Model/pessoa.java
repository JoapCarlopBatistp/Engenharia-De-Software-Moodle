package Model;

import java.sql.PreparedStatement;

import javax.swing.JOptionPane;

import Database.databaseconn;

public class pessoa {

    private String nome;
    private int ddd;
    private int telefone;
    private String username;
    private char[] password;
    private Long papel;
    
    public void setPapel(Long papel) {
        this.papel = papel;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getDdd() {
        return ddd;
    }

    public void setDdd(int ddd) {
        this.ddd = ddd;
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String email;

    public pessoa() {
        
    }

    public void cadastrar(){
        databaseconn bd = new databaseconn();
        PreparedStatement statement;
        try {
            if(!bd.getConnection()){
                JOptionPane.showMessageDialog(null, "Falha na conexão, o sistem será fechado!");
                System.exit(0);
            }

            String url = "INSERT INTO Pessoa (Id_Pessoa, Nome, DDD_Telefone, Telefone, Nome_de_Usuario, Senha, Email, Papel) VALUES (nextval('Pessoa_Id_Pessoa_seq'),?,?,?,?,?,?,?)";
            statement = bd.connection.prepareStatement(url);
            statement.setString(1, this.nome);
            statement.setInt(2, this.ddd);
            statement.setInt(3, this.telefone);
            statement.setString(4, this.username);
            statement.setString(5, new String(this.password));
            statement.setString(6, this.email);
            statement.setLong(7, this.papel);
            statement.executeUpdate();
            statement.close();
            bd.close();
        } catch(Exception erro) {
            JOptionPane.showMessageDialog(null, "Algo de errado aconteceu:\n " + erro.toString());
            System.out.println(erro.toString());
        }
    }
}
