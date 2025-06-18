package Model;

public class turma {
    private int Id_Turma;
    private String semestre;
    private int vagas_disponibilidadas;
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

    public int getVagas_disponibilidadas() {
        return vagas_disponibilidadas;
    }

    public void setVagas_disponibilidadas(int vagas_disponibilidadas) {
        this.vagas_disponibilidadas = vagas_disponibilidadas;
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
