package Controller;

import java.util.List;

import Model.presenca;
import Model.turma;
import Model.DAO.presencaDao;

public class presencaController {
    public List<presenca> buscarPresencasPorTurma( turma turmaSelecionada) {
        presencaDao dao = new presencaDao();
        return dao.buscarPresencasPorTurma(turmaSelecionada);
    }

    public void salvarPresencas (List<presenca> presencas){
        presencaDao dao = new presencaDao();
        dao.salvarPresencas(presencas);
    }
}
