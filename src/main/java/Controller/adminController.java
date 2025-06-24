package Controller;

import Model.cadeira;
import Model.pessoa;
import Model.professor;
import Model.sala;
import Model.turma;
import Model.DAO.alunoDao;
import Model.DAO.cadeiraDao;
import Model.DAO.professorDao;
import Model.DAO.salaDao;

import java.util.ArrayList;
import java.util.List;


public class adminController {
    
    public adminController() {
    } 

    public void cadastrarCadeira(cadeira cadeira) throws Exception {
        if (cadeira == null) { 
            return; 
        }
        if(cadeira.getNome_Cadeira().equals("") || cadeira.getNome_Cadeira().equals(null)) throw new Exception("Dados inválidos");
        cadeiraDao cadeiraDAO = new cadeiraDao();
        cadeiraDAO.cadastrar(cadeira);
    }

    public void cadastrarTurma(turma turma) {
        turmaController turmaContrl = new turmaController();
        turmaContrl.cadastrarTurma(turma);
    }

    public void cadastrarSala(int capacidade) {
        if (capacidade == -1) return;
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
        cadeiras = dao.buscarTodos();
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

    public void alocarSalaTurma(sala sala, turma turma) throws Exception{
        turmaController controller = new turmaController();
        controller.alocarSala(sala, turma);
    }

    public void alocarProfessorTurma(professor professor, turma turma) throws Exception {
        turmaController controller = new turmaController();
        controller.alocarProfessor(professor, turma);        
    }

}

    
