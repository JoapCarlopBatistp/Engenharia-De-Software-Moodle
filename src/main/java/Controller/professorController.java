package Controller;

import java.util.ArrayList;
import java.util.List;

import Model.notificacao;
import Model.sessao;
import Model.turma;
import Model.DAO.professorDao;

public class professorController {

    public List<notificacao> buscarNotificacoesParaProcessar(sessao sessao) throws Exception {
        List<notificacao> notificacoes = new ArrayList<notificacao>();
        try {
            professorDao dao = new professorDao();
            notificacoes = dao.buscarNotificacoesParaProcessar(sessao);
        } catch(Exception ex) {
            throw ex;
        }
        
        return notificacoes;
    }


    public List<turma> buscarTurmasEnsinadas(sessao sessao) throws Exception {
        List<turma> turmas = new ArrayList<turma>();
        try {
            professorDao dao = new professorDao();
            turmas = dao.buscarTurmasEnsinadas(sessao);
        } catch(Exception ex) {
            throw ex;
        }

        return turmas;
    }

    public void processarNotificacaoMatricula(notificacao notificacao, sessao sessao) throws Exception {
        try {
            professorDao dao = new professorDao();
            dao.atualizarNotificao(notificacao, sessao);
            if(notificacao.getStatus().equals("ACE")) {
                alunoController control = new alunoController();
                control.matriculaAposProfessorAceitarNotificacao(notificacao);
            }
        } catch(Exception ex) {
            throw ex;
        }
    }
    
}
