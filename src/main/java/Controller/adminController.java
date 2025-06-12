package Controller;

import Model.sala;
import Model.turma;
import Model.aluno;
import Model.pessoa;
import DAO.pessoaDao;
import Model.professor;


import java.sql.SQLException;

import javax.swing.*;

public class adminController {

    private pessoa pessoa;
    private pessoaDao pessoaDao;

    
    public adminController() {
    }

    public adminController(pessoa pessoa) {
        this.pessoa = pessoa;
        this.pessoaDao = new pessoaDao();
        pessoaDao.cadastrar(pessoa);
    }

    public void cadastrarTurma() {
        Integer numero = pedirTamTurma();
        if (numero != null) {
            new turma().cadastrar(numero);
        } else {
            System.out.println("Erro no cadastro da Turma.");
        }
    }

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
            aluno.cadastrar(this.pessoa);
        } catch (SQLException exception) {

        }
    }

    public void cadastrarProfessor() {
        try {
            professor professor = new professor();
            professor.cadastrar(this.pessoa);
        } catch (SQLException exception) {

        }

    }

    private static Integer pedirNumeroInteiro() {
        while (true) {
            String input = JOptionPane.showInputDialog(null, "Digite a capacidade da sala:", "Entrada", JOptionPane.QUESTION_MESSAGE);

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


    private static Integer pedirTamTurma() {
        while (true) {
            String input = JOptionPane.showInputDialog(null, "Digite o tamanho da turma:", "Entrada", JOptionPane.QUESTION_MESSAGE);

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
