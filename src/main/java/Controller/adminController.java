package Controller;

import Model.cadeira;
import Model.pessoa;
import Model.professor;
import Model.sala;
import Model.turma;

import java.util.ArrayList;
import java.util.List;

import DAO.alunoDao;
import DAO.cadeiraDao;
import DAO.professorDao;
import DAO.salaDao;


public class adminController {
    
    public adminController() {
    }

    public void cadastrarTurma(turma turma) {
        turmaController turmaContrl = new turmaController();
        turmaContrl.cadastrarTurma(turma);
    }

    public void cadastrarSala(int capacidade) {
        salaDao sala = new salaDao();
        sala.cadastrar(capacidade);
    }

    public void cadastrarAluno(pessoa pessoa) {
        if(pessoa == null) return;
        alunoDao aluno = new alunoDao();
        aluno.cadastrar(pessoa);
    }

    public void cadastrarProfessor(pessoa pessoa) {
        if(pessoa == null) return;
        professorDao professor = new professorDao();
        professor.cadastrar(pessoa);
    }

    public List<cadeira> listarCadeiras() {
        List<cadeira> cadeiras = new ArrayList<cadeira>();
        cadeiraDao dao = new cadeiraDao();
        cadeiras = dao.buscarTodasCadeiras();
        return cadeiras;
    }

    public List<professor> listarProfessores() {
        List<professor> professores = new ArrayList<professor>();
        professorDao dao = new professorDao();
        professores = dao.buscarTodosProfessores();
        return professores;
    }

    public List<sala> listarSalas() {
        List<sala> salas = new ArrayList<sala>();
        salaDao dao = new salaDao();
        salas = dao.buscaSalas();
        return salas;
    }

}
