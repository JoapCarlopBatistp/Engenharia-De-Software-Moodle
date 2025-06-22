package Controller;

import Model.notificacao;
import Model.sessao;
import Model.turma;

import java.util.ArrayList;
import java.util.List;

import DAO.alunoDao;



public class alunoController {
    
    public alunoController() {
    }

    public List<turma> listarTurmas() {
        turmaController turmaDao = new turmaController();
        return turmaDao.listarTurmas();
    }

    public void cadastrarMatricula(turma turmaParaMatricular, sessao sessao) throws Exception {
       alunoDao alunoDao = new alunoDao();
       /*
        * se a turma estiver lotada guardar notificacao para o professor*/

        if(turmaParaMatricular.getVagas_ocupadas() >=  turmaParaMatricular.getVagas_disponibilizadas()) {
            alunoDao.notificarProfessorMatricula(turmaParaMatricular, sessao);
            return;
        }
        // if(existemHorariosConflitando(turmaParaMatricular, sessao)) {

        // }
       alunoDao.cadastrarMatricula(turmaParaMatricular, sessao);
    }

    public List<notificacao> buscarNotificacoesProcessadas (sessao sessaoAtual) {
        List<notificacao> notificacoesProcessadas = new ArrayList<notificacao>();
        alunoDao alunoDao = new alunoDao();
        try {
            notificacoesProcessadas = alunoDao.buscarNotificacoesAluno(sessaoAtual);
        } catch (Exception ex) {
            throw ex;
        }
        return notificacoesProcessadas;
    }

    public List<turma> buscarTurmasMatriculadas (sessao sessaoAtual) throws Exception {
        List<turma> turmasMatriculadas = new ArrayList<turma>();
        alunoDao alunoDao = new alunoDao();
        try {
            turmasMatriculadas = alunoDao.buscarTurmasMatriculadas(sessaoAtual);
        } catch (Exception ex) {
            throw ex;
        }
        return turmasMatriculadas;
    }

}
