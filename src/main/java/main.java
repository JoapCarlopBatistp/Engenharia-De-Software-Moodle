
import View.View_Administrador.tela_administrador;
// import View.View_Login.tela_cadastro;

public class main {
    public static void main(String[] args) {
        
        try {
            //  new tela_cadastro();
            new tela_administrador();
        } catch (Exception e) {
            System.err.println("Não foi possível iniciar a operação");
        }
    }
}
