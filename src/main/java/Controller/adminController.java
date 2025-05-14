package Controller;

import Model.sala;
import Model.aluno;
import Model.pessoa;

import java.sql.SQLException;

import javax.swing.*;


public class adminController {

    private pessoa pessoa = new pessoa();
    public adminController() {}

    public void cadastrarSala() {
        Integer numero = pedirNumeroInteiro();
        if (numero != null) {
            new sala().cadastrar(numero);
        } else {
            System.out.println("Erro no cadastro da Sala.");
        }
    }

    public void cadastrarAluno() {
        try {
            aluno aluno = new aluno();
            cadastrarPessoa();
            this.pessoa.cadastrar();
            aluno.cadastrar(this.pessoa);
            System.out.println("Deu bom");
        } catch (SQLException exception) {

        }

    }

    public void cadastrarProfessor() {
        Integer numero = pedirNumeroInteiro();
        if (numero != null) {
            new sala().cadastrar(numero);
        } else {
            System.out.println("Erro no cadastro da Sala.");
        }
    }

    private void cadastrarPessoa() {

        String nome;
        int ddd;
        int telefone;
        String username;
        String password;
        String email;

        JTextField nomeField = new JTextField(20);
        JTextField dddField = new JTextField(2);
        JTextField telefoneField = new JTextField(9);
        JTextField usernameField = new JTextField(20);
        JTextField emailField = new JTextField(20);
        JPasswordField passwordField = new JPasswordField(28);

        JPanel myPanel = new JPanel();
        myPanel.add(new JLabel("Nome :"));
        myPanel.add(nomeField);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("DDD:"));
        myPanel.add(dddField);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("Telefone:"));
        myPanel.add(telefoneField);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("Username:"));
        myPanel.add(usernameField);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("Senha:"));
        myPanel.add(passwordField);
        myPanel.add(new JLabel("Email:"));
        myPanel.add(emailField);

        int result = JOptionPane.showConfirmDialog(null, myPanel, 
                "Por favor cadastre os dados: ", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            pessoa.setNome(nomeField.getText());
            pessoa.setDdd(Integer.parseInt(dddField.getText()));
            pessoa.setTelefone(Integer.parseInt(telefoneField.getText()));
            pessoa.setUsername(usernameField.getText());
            pessoa.setPassword(passwordField.getPassword());
            pessoa.setEmail(emailField.getText());
        }
    }


    private static Integer pedirNumeroInteiro() {
        while (true) {
            String input = JOptionPane.showInputDialog(null, "Digite um número inteiro:", "Entrada", JOptionPane.QUESTION_MESSAGE);

            if (input == null) {
                // Usuário cancelou
                return null;
            }

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Valor inválido. Por favor, digite um número inteiro.");
            }
        }
    }
}
