package Model;

public class presenca {
     
    private int Id_Aluno;
    private int Id_Turma;
    private String Data;
    private boolean Presente;

    public int getId_Aluno() {
        return Id_Aluno;
    }
    public void setId_Aluno(int id_Aluno) {
        Id_Aluno = id_Aluno;
    }
    public int getId_Turma() {
        return Id_Turma;
    }
    public void setId_Turma(int id_Turma) {
        Id_Turma = id_Turma;
    }
    public String getData() {
        return Data;
    }
    public void setData(String data) {
        Data = data;
    }
    public boolean getPresente() {
        return Presente;
    }
    public void setPresente(boolean presente) {
        Presente = presente;
    }
    
    public presenca() {
        
    }
    
}
