package Controller;


import Model.turma;

import java.util.ArrayList;
import java.util.List;

import DAO.turmaDao;


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
        turmas = dao.buscarTodasTurmas();
        return turmas;
    }
}
