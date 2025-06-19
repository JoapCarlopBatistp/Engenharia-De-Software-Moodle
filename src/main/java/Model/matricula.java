package Model;

public class matricula {
    
    private int Id_Matricula;
    private int Id_Aluno;
    private int Id_Turma;
    private int Data_Matricula;
    private int Data_Cancelamento;
    private int Data_Encerramento;

    public int getId_Matricula(){
        return Id_Matricula;
    }

    public void setId_Matricula(int Id_Matricula){
        this.Id_Matricula = Id_Matricula;
    }

    public int getId_Aluno(){
        return Id_Aluno;
    }

    public void setId_Aluno(int Id_Aluno){
        this.Id_Aluno = Id_Aluno;
    }

    public int getId_Turma(){
        return Id_Turma;
    }

    public void setId_Turma(int Id_Turma){
        this.Id_Turma = Id_Turma;
    }

    public void setId_Data_Matricula(int Data_Matricula){
        this.Data_Matricula = Data_Matricula;
    }

    public void setId_Data_Cancelamento(int Data_Cancelamento){
        this.Data_Cancelamento = Data_Cancelamento;
    }

    public void setId_Data_Encerramento(int Data_Encerramento){
        this.Data_Encerramento = Data_Encerramento;
    }    
    
    public matricula() {
        
    }

}
