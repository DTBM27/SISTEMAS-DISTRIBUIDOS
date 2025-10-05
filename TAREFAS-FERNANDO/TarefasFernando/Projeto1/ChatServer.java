import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChatServer {

    // --- Configurações do Servidor ---
    private static final int PORT = 55555;
    // Lista para manter todos os handlers de clientes conectados.
    // Usamos uma lista sincronizada para garantir que seja thread-safe,
    // já que múltiplas threads (ClientHandlers) irão acessá-la.
    private static List<ClientHandler> clients = Collections.synchronizedList(new ArrayList<>());

    public static void main(String[] args) {
        System.out.println("Servidor de Chat iniciando...");
        ServerSocket serverSocket = null;

        try {
            // 1. Cria um ServerSocket para escutar na porta especificada.
            serverSocket = new ServerSocket(PORT);
            System.out.println("Servidor escutando na porta " + PORT);

            // 2. Loop infinito para aceitar novas conexões de clientes.
            while (true) {
                // O método accept() bloqueia a execução até que um cliente se conecte.
                Socket clientSocket = serverSocket.accept();
                System.out.println("Novo cliente conectado: " + clientSocket.getInetAddress().getHostAddress());

                // 3. Para cada cliente, cria um novo ClientHandler.
                ClientHandler clientHandler = new ClientHandler(clientSocket, clients);
                
                // 4. Adiciona o handler do novo cliente à lista de clientes.
                clients.add(clientHandler);
                
                // 5. Inicia uma nova thread para o handler do cliente.
                // Isso permite que o servidor lide com múltiplos clientes simultaneamente.
                new Thread(clientHandler).start();
            }
        } catch (IOException e) {
            System.err.println("Erro no servidor: " + e.getMessage());
        } finally {
            if (serverSocket != null && !serverSocket.isClosed()) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
