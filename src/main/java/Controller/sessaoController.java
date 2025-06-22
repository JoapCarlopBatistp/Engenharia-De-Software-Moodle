package Controller;

import DAO.sessaoDao;
import Model.sessao;

public class sessaoController {
    
    public sessaoController() {}

    public void criarSessao(){}

    public sessao login(String username, String password) throws Exception {
        try{
            sessaoDao sessaoDao = new sessaoDao();
            return sessaoDao.login(username, password);

        } catch (Exception ex) {
            throw ex;
        }
    }
}
