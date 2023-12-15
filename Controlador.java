import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Controlador {
    public ServerSocket ss;

    public void criarServerSocket(int porta) throws IOException {
        ss = new ServerSocket(porta);
    }

    public Socket esperaConexao() throws IOException {
        Socket socket = ss.accept();
        return socket;
    }

    public void transferenciaDados(Socket socket) throws IOException {
        try {
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inputValor = new ObjectInputStream(socket.getInputStream());
            //ObjectInputStream inputMoeda = new ObjectInputStream(socket.getInputStream());

            // fluxo
            // cliente envia um valor
            // Servidor vai enviar uma resposta para o cliente com a moeda convertida

            String valorString = inputValor.readUTF();
            
            System.out.println("Valor recebido pelo cliente: " + valorString);
            output.writeUTF("Escolha uma moeda para a conversão: \n"+
                                "1 - Dollar \n"+
                                "2 - Euro \n"+
                                "3 - Peso \n"+
                                "4 - Franco Suiço \n"+
                                "5 - Bitcoin \n"
            );  
            output.flush();

            Double valor = Double.parseDouble(valorString);
            //Double valor = valorString;
            Double resultado = 0.0;
            String MoedaRecebida = inputValor.readUTF();
            int Moeda = Integer.parseInt(MoedaRecebida);
            // Dollar, euro, peso, franco Suiço, bitcoin
            Double Dollar = 4.91;
            Double Euro = 5.26;
            Double Peso = 0.014;
            Double Franco = 5.43;
            Double BitCoin = 5.02;

            System.out.println(MoedaRecebida);
            switch (Moeda) {
                case 1:
                    //Dollar
                    resultado = valor / Dollar;
                    break;

                case 2:
                    //Euro
                    resultado = valor / Euro;
                    break;

                case 3:
                    //Peso
                    resultado = valor / Peso;
                    break;

                case 4:
                    //Franco Suiço
                    resultado = valor / Franco;
                    break;

                case 5:
                    //BitCoin
                    resultado = valor / BitCoin;
                    break;
            
                default:
                    break;
            }

            System.out.println(resultado);
            
            output.writeUTF("O Valor convertido é: " + resultado);
            output.flush();
            inputValor.close();

            output.close();

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Problema no tratamento da conexao com cliente" + socket.getInetAddress());
            System.out.println("Erro" + e.getMessage());
        } finally {
            fechaSocket(socket);
        }
    }

    public void fechaSocket(Socket socket) throws IOException {
        socket.close();
        System.out.println("Cliente finalizado");
    }

}