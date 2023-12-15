import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;

public class Principal {
    public static void main(String[] args) {
        System.out.println("Estabelecendo conexao");
        try {
            Socket socket = new Socket("localhost", 5555);
            System.out.println("Conexao boa");

            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

            String valorString = JOptionPane.showInputDialog("Digite o valor a ser convertido");
            output.writeUTF(valorString);
            output.flush();// libera buffer para envio
            System.out.println("Valor enviado para servidor");

            valorString = input.readUTF();
            String moedaString = JOptionPane.showInputDialog(null, valorString);
            output.writeUTF(moedaString);
            output.flush();
            JOptionPane.showMessageDialog(null, input.readUTF());

            input.close();
            output.close();
            socket.close();
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("erro cliente" + e);
        }
    }
}