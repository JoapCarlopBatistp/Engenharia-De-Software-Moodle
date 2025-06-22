package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Database.databaseconn;
import Model.aluno;
import Model.notificacao;
import Model.pessoa;
import Model.sessao;
import Model.turma;

public class alunoDao extends pessoaDao{

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

    public List<aluno> buscarTodoAlunos() {
        List<aluno> alunos = new ArrayList<aluno>();
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
                aluno aluno = new aluno ();
                aluno.setDdd(rs.getInt("DDD_Telefone"));
                aluno.setEmail(rs.getString("Email"));
                aluno.setNome(rs.getString("Nome"));
                aluno.setPapel(rs.getInt("Papel"));
                aluno.setTelefone(rs.getInt("Telefone"));
                aluno.setUsername(rs.getString("Nome_de_Usuario"));
                aluno.setId(rs.getInt("Id_Pessoa"));
                aluno.setId_Aluno(rs.getInt("Id_Aluno"));
                alunos.add(aluno);
            }
            statement.close();
           bd.close();
        } catch(Exception erro) {
            JOptionPane.showMessageDialog(null, "Algo de errado aconteceu no cadastro:\n " + erro.toString());
        }

        return alunos;
    }

    public void cadastrarMatricula(turma turmaParaMatricular, sessao sessao) throws Exception{
        PreparedStatement statement = null;
        PreparedStatement statementUpdateTurma = null;
        try {
            statement = sessao.getConnection().connection.prepareStatement(this.cadastrarMatricula());
            statement.setInt(1, sessao.getId());
            statement.setInt(2, turmaParaMatricular.getId_turma());
            statement.executeUpdate();

            statementUpdateTurma = sessao.getConnection().connection.prepareStatement(this.updateVagasTurmasQuery());
            statementUpdateTurma.setInt(2, turmaParaMatricular.getId_turma());
            statementUpdateTurma.executeUpdate();
        
            statement.close();
            statementUpdateTurma.close();
        } catch(Exception erro) {
            throw erro;
        }
    }

    public void notificarProfessorMatricula(turma turmaParaMatricular, sessao sessao) throws Exception{
        PreparedStatement statement = null;
        PreparedStatement statementNotificacao = null;
        try {
            statement = sessao.getConnection().connection.prepareStatement(this.cadastrarMatriculaPendente(),Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, sessao.getId());
            statement.setInt(2, turmaParaMatricular.getId_turma());
            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    Long matriculaId = generatedKeys.getLong(1);
                    
                    statementNotificacao = sessao.getConnection().connection.prepareStatement(this.cadastrarNotificacaoAlunoQuery());
                    statementNotificacao.setInt(1, turmaParaMatricular.getId_professor());
                    statementNotificacao.setLong(2, matriculaId);
                    statementNotificacao.executeUpdate();
                }
            }
        
            statement.close();
        } catch(Exception erro) {
            throw erro;
        }
    }

    public List<turma> buscarTurmasMatriculadas(sessao sessao) throws Exception {
         List<turma> turmas = new ArrayList<turma>(); 
        
        PreparedStatement statement;
        ResultSet rs = null;
        try {
            statement = sessao.getConnection().connection.prepareStatement(this.buscarTurmasAlunoAtualQuery());
            statement.setInt(1, sessao.getId());
            rs = statement.executeQuery();

            while (rs.next()) {
                turma turma = new turma ();
                turma.setId_turma(rs.getInt("id_turma"));
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
        } catch(Exception erro) {
            throw erro;
        }

        return turmas;
    }

    public List<notificacao> buscarNotificacoesAluno (sessao sessao) {
        List<notificacao> notificacoesDoAluno = new ArrayList<notificacao>();
        
        PreparedStatement statement;
        ResultSet rs = null;
        try {
            statement = sessao.getConnection().connection.prepareStatement(this.buscarRespostaPedidoNotificacoesAluno());
            statement.setInt(1, sessao.getId());
            rs = statement.executeQuery();

            while (rs.next()) {
                notificacao notificacao = new notificacao ();
                notificacao.setId_matricula(rs.getInt("Id_Matricula_Pendente"));
                notificacao.setId_professor(rs.getInt("Id_Professor"));
                notificacao.setStatus(rs.getString("Status"));
                notificacoesDoAluno.add(notificacao);
            }
            statement.close();
        } catch(Exception erro) {
            JOptionPane.showMessageDialog(null, "Algo de errado aconteceu no cadastro:\n " + erro.toString());
        }

        return notificacoesDoAluno;
    }

     private String buscarTodosQuery() {
        return "select pes.*, alu.id_aluno from aluno alu join pessoa pes on pes.id_pessoa = alu.id_pessoa";
    }
    
    private String buscarQuery() {
        return "select pes.* from aluno alu join pessoa pes on pes.id_pessoa = alu.id_pessoa WHERE pes.Nome_de_Usuario = ?";
    }

    
    private String cadastrarQuery() {
        return "INSERT INTO Aluno (Id_Aluno, Id_Pessoa) SELECT nextval('Aluno_Id_Aluno_seq'), Id_Pessoa FROM pessoa where Nome_de_Usuario = ?";
    }

    private String cadastrarMatricula() {
       return "INSERT INTO matricula " +
                "(id_aluno, " +
                 "id_turma, " +
                 "data_matricula, " +
                 "data_cancelamentomatricula, " +
                 "data_encerramentomatricula, " +
                 "id_matricula) " +
       "VALUES((select id_aluno from aluno where id_pessoa = ?), ?, CURRENT_DATE, null, null, nextval('matricula_id_matricula_seq'::regclass))";
    }

    private String cadastrarMatriculaPendente() {
       return "INSERT INTO matricula_pendente " +
                "(id_aluno, " +
                 "id_turma, " +
                 "data_matricula, " +
                 "data_cancelamentomatricula, " +
                 "data_encerramentomatricula, " +
                 "id_matricula, " +
                 "status) " +
       "VALUES((select id_aluno from aluno where id_pessoa = ?), ?, CURRENT_DATE, null, null, nextval('matricula_pendente_id_matricula_pendente_seq'::regclass), null)";
    }

     private String buscarRespostaPedidoNotificacoesAluno() {
       return "select " +
                    "noti.id_professor, " +
                    "noti.id_matricula_pendente, "+
                    "noti.status "+
                "from "+
                    "notificacao noti "+
                    "join matricula_pendente mati on mati.id_matricula_pendente = noti.id_matricula_pendente "+
                    "join aluno alu on alu.id_aluno = mati.id_aluno "+
                "where "+
                    "noti.status is not null "+
                    "and alu.id_pessoa = ?";
    }

    private String buscarTurmasAlunoAtualQuery(){
        return "select " +
                    "tur.* "+
                "from "+
                    "matricula mati "+
                    "join aluno alu on alu.id_aluno = mati.id_aluno "+
                    "join turma tur on tur.id_turma = mati.id_turma "+
                "where "+
                    "alu.id_pessoa = ?";
    }

    private String updateVagasTurmasQuery(){
        return "update " +
                    "turma "+
                "set "+
                    "vagas_ocupadas = vagas_ocupadas + 1 "+
                "where "+
                    "id_turma = ?";
    }
    private String cadastrarNotificacaoAlunoQuery(){
        return "INSERT INTO notificacao "+
        "(id_professor, status, id_matricula_pendente) " +
        "VALUES(?, null, ?)";
    }
    
}
