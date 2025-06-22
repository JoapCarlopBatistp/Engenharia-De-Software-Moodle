package Model;

public class sala {

    private int id_sala;
    private int capacidade;

    public int getId_sala() {
        return id_sala;
    }

    public void setId_sala(int id_sala) {
        this.id_sala = id_sala;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    @Override//para mostrar o id na sala do combobox
    public String toString() {
        return Integer.toString(id_sala) + " - " + "Vagas possíves: " + Integer.toString(capacidade); // Display only the name in the combo box
    }
}
