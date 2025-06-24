package Controller;

import Model.sessao;
import Model.DAO.sessaoDao;

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

    public sessao criarSessaoDePessoa(int id_pessoa) throws Exception {
        try{
            sessaoDao sessaoDao = new sessaoDao();
            return sessaoDao.criarSessao(id_pessoa);
        } catch (Exception ex) {
            throw ex;
        }
    }
}
