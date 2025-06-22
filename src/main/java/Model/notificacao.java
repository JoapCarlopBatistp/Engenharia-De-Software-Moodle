package Model;

public class notificacao {
    private int id_professor;
    private int id_matricula;
    private String status;
    
    public int getId_professor() {
        return id_professor;
    }
    public void setId_professor(int id_professor) {
        this.id_professor = id_professor;
    }
    public int getId_matricula() {
        return id_matricula;
    }
    public void setId_matricula(int id_matricula) {
        this.id_matricula = id_matricula;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
