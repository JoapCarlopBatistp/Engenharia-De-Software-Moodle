package Model;

public class cadeira {
    private int Id_Cadeira;
    private String Nome_Cadeira;
    private String Codigo_Cadeira;

    public int getId_Cadeira(){
        return Id_Cadeira;
    }

    public void setId_Cadeira(int Id_Cadeira){
       this.Id_Cadeira = Id_Cadeira;
    }

    public String getNome_Cadeira(){
        return Nome_Cadeira;
    }

    public void setNome_Cadeira(String Nome_Cadeira){
       this.Nome_Cadeira = Nome_Cadeira;
    }

    public String getCodigo_Cadeira(){
        return Codigo_Cadeira;
    }

    public void setCodigo_Cadeira(String Codigo_Cadeira){
       this.Codigo_Cadeira = Codigo_Cadeira;
    }

    public cadeira() {
        
    }

     @Override
    public String toString() {
        return Nome_Cadeira; // Display only the name in the combo box
    }
}
