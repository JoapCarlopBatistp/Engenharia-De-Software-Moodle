package Controller;

import java.util.ArrayList;
import java.util.List;

import DAO.professorDao;
import Model.notificacao;
import Model.sessao;
import Model.turma;

public class professorController {

    public static List<notificacao> buscarNotificacoesParaProcessar(sessao sessao) throws Exception {
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
    
}
