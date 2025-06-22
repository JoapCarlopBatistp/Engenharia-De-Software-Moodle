package Model;


public class turma {
    private int id_turma;
	private String semestre;
	private int vagas_disponibilizadas;
    private int vagas_ocupadas;
	private String dias;
	private String horario;
	private int id_sala;
	private int id_cadeira;
	private int id_professor;

    public int getId_professor() {
        return id_professor;
    }
    public void setId_professor(int id_professor) {
        this.id_professor = id_professor;
    }
    public int getId_turma() {
        return id_turma;
    }
    public void setId_turma(int id_turma) {
        this.id_turma = id_turma;
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
    public int getId_sala() {
        return id_sala;
    }
    public void setId_sala(int id_sala) {
        this.id_sala = id_sala;
    }
    public int getId_cadeira() {
        return id_cadeira;
    }
    public void setId_cadeira(int id_cadeira) {
        this.id_cadeira = id_cadeira;
    }

    @Override
    public String toString() {
        return "Turma " + Integer.toString(id_turma) + " " + dias + "-" + horario; // Display only the name in the combo box
    }
}
