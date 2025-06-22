package Model;


import java.util.ArrayList;
import java.util.List;

import Controller.alunoController;
import Controller.professorController;
import Database.databaseconn;

public class sessao extends pessoa {

    private databaseconn connection;
    List<turma> turmasMatriculadas = new ArrayList<turma>();
    List<turma> turmasEnsinadas = new ArrayList<turma>();
    List<notificacao> notificacoes = new ArrayList<notificacao>();

    public databaseconn getConnection() {
        connection = new databaseconn();
        connection.getConnection();
        return connection;
    }

    public sessao(pessoa pessoa) throws Exception {

        try {
            this.setDdd(pessoa.getDdd());
            this.setEmail(pessoa.getEmail());
            this.setNome(pessoa.getNome());
            this.setPapel(pessoa.getPapel());
            this.setTelefone(pessoa.getTelefone());
            this.setUsername(pessoa.getUsername());
            this.setId(pessoa.getId());
            this.connection = new databaseconn();
            this.connection.getConnection();
            switch(roleEnum.values()[pessoa.getPapel()]) {
                case PROFESSOR:
                    this.sessaoProfessor(pessoa);
                    break;
                case ALUNO:
                    this.sessaoAluno(pessoa);
                    break;
                default:
            }
        } catch(Exception ex) {
            throw ex;
        }

        
    }

    public sessao() {
        
    }

    private void sessaoProfessor(pessoa pessoa) throws Exception {
        professorController controller = new professorController();
        try {
            this.notificacoes = controller.buscarNotificacoesParaProcessar(this);
            this.turmasEnsinadas = controller.buscarTurmasEnsinadas(this);
            this.turmasMatriculadas = null;
        } catch (Exception e) {
            throw e;
        }
    }

    private void sessaoAluno(pessoa pessoa) throws Exception {
        alunoController controller = new alunoController();
        try {
            this.notificacoes = controller.buscarNotificacoesProcessadas(this);
            this.turmasMatriculadas = controller.buscarTurmasMatriculadas(this);
            this.turmasEnsinadas = null;
        } catch (Exception e) {
            throw e;
        }
    }

    public void disconnect() {
        this.connection.close();
    }
    
}
