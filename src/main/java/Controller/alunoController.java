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
        turmaController turmaDao = new turmaController();
        if(turmaParaMatricular.getVagas_ocupadas() >=  turmaParaMatricular.getVagas_disponibilizadas()) {
            alunoDao.notificarProfessorMatricula(turmaParaMatricular, sessao);
            return;
        }
        if(this.existemHorariosConflitando(turmaParaMatricular, sessao)) {
            throw new Exception("Matricula rejeitada por conflito de horário");
        }
       alunoDao.cadastrarMatricula(turmaParaMatricular, sessao);
       turmaDao.incrementarNumeroAlunos(sessao, turmaParaMatricular);
       sessao.buscarTurmasMatriculadas();
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

    private boolean existemHorariosConflitando(turma turmaParaMatricular, sessao sessao){
        for (int i = 0; i < sessao.getTurmasMatriculadas().size(); i++) {
             if(sessao.getTurmasMatriculadas().get(i).getDias().length() > turmaParaMatricular.getDias().length()) {
                if (sessao.getTurmasMatriculadas().get(i).getDias().contains(turmaParaMatricular.getDias()) && sessao.getTurmasMatriculadas().get(i).getHorario().equals(turmaParaMatricular.getHorario())) return true;
            } else {
                if (turmaParaMatricular.getDias().contains(sessao.getTurmasMatriculadas().get(i).getDias()) && sessao.getTurmasMatriculadas().get(i).getHorario().equals(turmaParaMatricular.getHorario())) return true;
                
            }
        }
        return false;

    }

    public List<turma> historicoMatriculasAluno(sessao sessao) throws Exception {
        alunoDao dao = new alunoDao();
        return dao.historicoMatriculasAluno(sessao);
    }

    public List<turma> buscaTurmasComVagasDisponiveis() {
        turmaController controller = new turmaController();
        return controller.buscaTurmasComVagasDisponiveis();
    }

    public void matriculaAposProfessorAceitarNotificacao(notificacao notificacao) throws Exception {
        alunoDao dao = new alunoDao();
        turmaController controller = new turmaController();
        sessao sessao = new sessaoController().criarSessaoDePessoa(this.buscaAlunoDaMatricula(notificacao.getId_matricula()));
        turma turma = controller.buscarTurmaDaMatriculaPendente(notificacao.getId_matricula());
        dao.cadastrarMatricula(turma, sessao);
    }


    private int buscaAlunoDaMatricula(int id_matricula_pendente) {
        alunoDao dao = new alunoDao();
        return dao.buscaIdPessoaAlunoMatriculaPendente(id_matricula_pendente);
    }

}
