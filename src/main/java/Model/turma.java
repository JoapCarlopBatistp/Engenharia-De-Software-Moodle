package Model;

public class turma {
    private int Id_Turma;
    private String semestre;
    private int vagas_disponibilizadas;
    private int vagas_ocupadas;
    private String dias;
    private String horario; 

    public int getId_Turma() {
        return Id_Turma;
    }

    public void setId_Turma(int Id_Turma) {
        this.Id_Turma = Id_Turma;  
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public int getVagas_disponibilizadas() {
        return vagas_disponibilizadas;
    }

    public void setVagas_disponibilizadas(int vagas_disponibilizadas) {
        this.vagas_disponibilizadas = vagas_disponibilizadas;
    }

    public int getVagas_ocupadas() {
        return vagas_ocupadas;
    }

    public void setVagas_ocupadas(int vagas_ocupadas) {
        this.vagas_ocupadas = vagas_ocupadas;
    }

    public String getDias() {
        return dias;
    }

    public void setDias(String dias) {
        this.dias = dias;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
       this.horario = horario;
    }

    public turma() {

    }
}
