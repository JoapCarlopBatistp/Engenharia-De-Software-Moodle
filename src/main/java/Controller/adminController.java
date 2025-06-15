package Controller;

import Model.turma;
import Model.pessoa;
import DAO.alunoDao;
import DAO.professorDao;
import DAO.salaDao;

import javax.swing.*;

public class adminController {
    
    public adminController() {
    }

    public void cadastrarTurma() {
        Integer numero = pedirTamTurma();
        if (numero != null) {
            new turma().cadastrar(numero);
        } else {
            System.out.println("Erro no cadastro da Turma.");
        }
    }

    public void cadastrarSala(int capacidade) {
        salaDao sala = new salaDao();
        sala.cadastrar(capacidade);
    }

    public void cadastrarAluno(pessoa pessoa) {
        alunoDao aluno = new alunoDao();
        aluno.cadastrar(pessoa);
    }

    public void cadastrarProfessor(pessoa pessoa) {

        professorDao professor = new professorDao();
        professor.cadastrar(pessoa);
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
