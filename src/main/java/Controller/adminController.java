package Controller;

import Model.sala;
import Model.turma;
import Model.pessoa;
import Model.turma;
import DAO.alunoDao;
import DAO.professorDao;
import DAO.salaDao;
import DAO.turmaDao;


import javax.swing.*;

public class adminController {
    
    public adminController() {
    } 
    
    public void cadastrarTurma() {
        turmaDao turma = new turmaDao();
        sala.cadastrar(turma);
    }

    public void cadastrarSala() {
        salaDao sala = new salaDao();
        sala.cadastrar(sala);
    }

    public void cadastrarAluno(pessoa pessoa) {
        alunoDao aluno = new alunoDao();
        aluno.cadastrar(pessoa);
    }

    public void cadastrarProfessor(pessoa pessoa) {

        professorDao professor = new professorDao();
        professor.cadastrar(pessoa);
    }    
    
}

    
