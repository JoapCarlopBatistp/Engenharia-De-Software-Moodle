package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Database.databaseconn;
import Model.notificacao;
import Model.pessoa;
import Model.professor;
import Model.sessao;
import Model.turma;

public class professorDao extends pessoaDao{

    public void cadastrar(pessoa pessoa){
        databaseconn bd = new databaseconn();
        PreparedStatement statement;
        super.cadastrar(pessoa);
        try {
            if(!bd.getConnection()){
                JOptionPane.showMessageDialog(null, "Falha na conexão, o sistem será fechado!");
                System.exit(0);
            }

            statement = bd.connection.prepareStatement(this.cadastrarQuery());
            statement.setString(1, pessoa.getUsername());
            statement.executeUpdate();
            statement.close();
           bd.close();
        } catch(Exception erro) {
            JOptionPane.showMessageDialog(null, "Algo de errado aconteceu:\n " + erro.toString());
            System.out.println(erro.toString());
        }
    }

    public pessoa buscar(String username) {
       return super.buscar(username, this.buscarQuery());
    }

    public List<professor> buscarTodosProfessores() {
        List<professor> professores = new ArrayList<professor>();
        databaseconn bd = new databaseconn();
        PreparedStatement statement;
        ResultSet rs = null;
        try {
            if(!bd.getConnection()){
                JOptionPane.showMessageDialog(null, "Falha na conexão, o sistem será fechado!");
                System.exit(0);
            }

            statement = bd.connection.prepareStatement(this.buscarTodosQuery());
            rs = statement.executeQuery();

            while (rs.next()) {
                professor professor = new professor ();
                professor.setDdd(rs.getInt("DDD_Telefone"));
                professor.setEmail(rs.getString("Email"));
                professor.setNome(rs.getString("Nome"));
                professor.setPapel(rs.getInt("Papel"));
                professor.setTelefone(rs.getInt("Telefone"));
                professor.setUsername(rs.getString("Nome_de_Usuario"));
                professor.setId(rs.getInt("Id_Pessoa"));
                professor.setId_Professor(rs.getInt("Id_Professor"));
                professores.add(professor);
            }
            statement.close();
            rs.close();
            bd.close();
        } catch(Exception erro) {
            JOptionPane.showMessageDialog(null, "Algo de errado aconteceu no cadastro:\n " + erro.toString());
        }

        return professores;
    }

    public List<turma> buscarTurmasEnsinadas(sessao sessao) throws Exception {
        List<turma> turmas = new ArrayList<turma>();
        PreparedStatement statement;
        ResultSet rs = null;
        try {
            statement = sessao.getConnection().connection.prepareStatement(this.buscarTurmasEnsinadasQuery());
            statement.setInt(1, sessao.getId());
            rs = statement.executeQuery();

            while (rs.next()) {
                turma turma = new turma();
                turma.setId_turma(rs.getInt("Id_Turma"));
                turma.setSemestre(rs.getString("semestre"));
                turma.setVagas_disponibilizadas(rs.getInt("vagas_disponibilizadas"));
                turma.setVagas_ocupadas(rs.getInt("vagas_ocupadas"));
                turma.setDias(rs.getString("dias"));
                turma.setHorario(rs.getString("horario"));
                turma.setId_sala(rs.getInt("id_sala"));
                turma.setId_cadeira(rs.getInt("id_cadeira"));
                turma.setId_professor(rs.getInt("id_professor"));
                turmas.add(turma);
            }
            statement.close();
            rs.close();
        } catch(Exception ex) {
            throw ex;
        }
        return turmas;
    }

    public  List<notificacao> buscarNotificacoesParaProcessar(sessao sessao) throws Exception {
        List<notificacao> notificacoes = new ArrayList<notificacao>();
        PreparedStatement statement;
        ResultSet rs = null;
        try {
            statement = sessao.getConnection().connection.prepareStatement(this.buscarNotificacoesParaProcessarQuery());
            statement.setInt(1, sessao.getId());
            rs = statement.executeQuery();

            while (rs.next()) {
                notificacao notificacao = new notificacao();
                notificacao.setId_professor(rs.getInt("Id_Professor"));
                notificacao.setId_matricula(rs.getInt("Id_Matricula_Pendente"));
                notificacao.setStatus(rs.getString("Status"));
                notificacoes.add(notificacao);
            }
            statement.close();
            rs.close();
        } catch(Exception ex) {
            throw ex;
        }
        return notificacoes;
    }

    private String buscarTodosQuery() {
        return "select pes.*, pro.id_professor from professor pro join pessoa pes on pes.id_pessoa = pro.id_pessoa";
    }
    
    private String buscarQuery() {
        return "select pes.* from professor pro join pessoa pes on pes.id_pessoa = pro.id_pessoa WHERE pes.Nome_de_Usuario = ?";
    }

    
    private String cadastrarQuery() {
        return "INSERT INTO Professor (Id_Professor, Id_Pessoa) SELECT nextval('Professor_Id_Professor_seq'), Id_Pessoa FROM pessoa where Nome_de_Usuario = ?";
    }

    private String buscarNotificacoesParaProcessarQuery() {
        return "select " +
                    "noti.id_professor, " +
                    "noti.id_matricula_pendente, "+
                    "noti.status "+
                "from "+
                    "notificacao noti "+
                    "join matricula_pendente mati on mati.id_matricula_pendente = noti.id_matricula_pendente "+
                    "join professor pro on pro.id_professor = noti.id_professor "+
                "where "+
                    "noti.status is null "+
                    "and pro.id_pessoa = ?";
    }
    
    private String buscarTurmasEnsinadasQuery() {
        return "select " +
                    "tur.* " +
                "from "+
                    "turma tur "+
                    "join professor pro on tur.id_professor = pro.id_professor "+
                "where "+
                    "pro.id_pessoa =  ?";
    }
    
}
