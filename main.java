import java.io.IOException;
import java.net.Socket;

public class main {
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        Controlador c = new Controlador();
        c.criarServerSocket(5555);
        System.out.println("Aguardo Cliente");
        Socket s = c.esperaConexao();
        System.out.println("Cliente Conectado");
        c.transferenciaDados(s);
    }
}