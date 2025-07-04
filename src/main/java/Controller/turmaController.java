package Controller;


import Model.sala;
import Model.sessao;
import Model.turma;
import Model.DAO.turmaDao;
import Model.professor;

import java.util.ArrayList;
import java.util.List;


public class turmaController {
    
    public turmaController() {
    }

    public void cadastrarTurma(turma turma) {
        if(turma == null) return;
        turmaDao turmaDao = new turmaDao();
        turmaDao.cadastrar(turma);
    }

    public List<turma> listarTurmas() {
        List<turma> turmas = new ArrayList<turma>();
        turmaDao dao = new turmaDao();
        turmas = dao.buscarTodasTurmas(false);
        return turmas;
    }

    public void alocarProfessor(professor professor, turma turma) throws Exception {
        turmaDao dao = new turmaDao();
        dao.alocarProfessor(professor, turma);
    }

    public void alocarSala(sala sala, turma turma) throws Exception {
        turmaDao dao = new turmaDao();
        if(this.salaEstaDisponivelNoHorario(turma, sala)){
            dao.alocarSala(sala, turma);
            return;
        }
        throw new Exception("A turma não está disponivel neste horário.");
    }

    public boolean salaEstaDisponivelNoHorario(turma turma, sala sala) {
        turmaDao dao = new turmaDao();
        return dao.verificaDisponibilidadeSala(turma, sala);

    }

    public List<turma> buscaTurmasComVagasDisponiveis() {
        List<turma> turmas = new ArrayList<turma>();
        turmaDao dao = new turmaDao();
        turmas = dao.buscarTodasTurmas(true);
        return turmas;

    }

    public void incrementarNumeroAlunos(sessao sessao, turma turma) throws Exception {
        turmaDao dao = new turmaDao();
        dao.incrementarVagasOcupadas(sessao, turma);
    }

    public turma buscarTurmaDaMatriculaPendente(int id_matricula_pendente) throws Exception {
        turmaDao dao = new turmaDao();
        return dao.buscaTurmaMatriculaPendente(id_matricula_pendente);
    }
}
