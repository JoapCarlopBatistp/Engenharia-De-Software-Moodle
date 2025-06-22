package Model;

public class sala {
    private int id_Sala;
    private int capacidade_Sala;

    public int getId_Sala() {
        return id_Sala;
    }

    public void setId_Sala(int id_Sala) {
        this.id_Sala = id_Sala;
    }

    public int getCapacidade_Sala() {
        return capacidade_Sala;
    }

    public void setCapacidade_Sala(int capacidade_Sala) {
        this.capacidade_Sala = capacidade_Sala;
    }

     public sala() {
        
    }  

       @Override//para mostrar o id na sala do combobox
    public String toString() {
        return Integer.toString(id_Sala) + " - " + "Vagas possíves: " + Integer.toString(capacidade_Sala); // Display only the name in the combo box
    }
}


