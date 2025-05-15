
import View.View_Login.tela_cadastro;

public class main {
    public static void main(String[] args) {
        
        try {
            new tela_cadastro();
        } catch (Exception e) {
            System.err.println("Não foi possível iniciar a operação");
        }
    }
}
