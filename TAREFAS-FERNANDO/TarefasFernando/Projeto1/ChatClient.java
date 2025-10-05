import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ChatClient {

    // --- Configurações do Cliente ---
    private static final String SERVER_ADDRESS = "127.0.0.1";
    private static final int SERVER_PORT = 55555;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT)) {
            
            // Fluxos de entrada e saída para o servidor
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

            // 1. Thread para receber mensagens do servidor
            Thread receiveThread = new Thread(() -> {
                try {
                    String serverMessage;
                    while ((serverMessage = in.readLine()) != null) {
                        // Se o servidor pedir o apelido, não mostre a mensagem 'NICK'
                        if ("NICK".equals(serverMessage)) {
                            System.out.print("Escolha seu apelido: ");
                            String nickname = consoleReader.readLine();
                            out.println(nickname);
                        } else {
                            System.out.println(serverMessage);
                        }
                    }
                } catch (IOException e) {
                    System.out.println("Desconectado do servidor.");
                }
            });
            receiveThread.start();

            // 2. A thread principal lida com o envio de mensagens do usuário
            // A solicitação de apelido é tratada pela outra thread,
            // então esta thread só precisa enviar as mensagens de chat.
            String userInput;
            // O loop será quebrado quando a conexão for fechada
            while (socket.isConnected()) {
                userInput = consoleReader.readLine();
                if (userInput != null) {
                   out.println(userInput);
                }
            }

        } catch (UnknownHostException e) {
            System.err.println("Servidor não encontrado: " + SERVER_ADDRESS);
        } catch (IOException e) {
            System.err.println("Não foi possível conectar ao servidor. Verifique se ele está online.");
        }
    }
}
