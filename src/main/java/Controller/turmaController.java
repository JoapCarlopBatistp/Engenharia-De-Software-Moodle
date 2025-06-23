package Controller;


import Model.sala;
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

    public void alocarSala(sala sala, turma turma) throws Exception {
        turmaDao dao = new turmaDao();
        dao.alocarSala(sala, turma);
    }
}
