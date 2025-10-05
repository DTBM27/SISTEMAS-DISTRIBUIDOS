import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

// Runnable permite que esta classe seja executada em uma thread separada.
public class ClientHandler implements Runnable {

    private Socket clientSocket;
    private List<ClientHandler> clients; // Referência à lista de clientes do servidor
    private PrintWriter out;
    private BufferedReader in;
    private String nickname;

    public ClientHandler(Socket socket, List<ClientHandler> clients) {
        this.clientSocket = socket;
        this.clients = clients;
    }

    @Override
    public void run() {
        try {
            // Inicializa os fluxos de entrada e saída para comunicação com o cliente.
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            // 1. Solicita o apelido do cliente.
            out.println("NICK"); // Envia o comando 'NICK' para o cliente
            this.nickname = in.readLine();
            System.out.println(nickname + " entrou no chat.");
            broadcastMessage(nickname + " entrou no chat!", this);

            // 2. Loop para ler as mensagens do cliente.
            String clientMessage;
            while ((clientMessage = in.readLine()) != null) {
                // Formata a mensagem para incluir o apelido do remetente.
                String formattedMessage = this.nickname + ": " + clientMessage;
                // Retransmite a mensagem para todos os outros clientes.
                broadcastMessage(formattedMessage, this);
            }

        } catch (IOException e) {
            // Erro geralmente ocorre quando o cliente se desconecta.
            System.out.println("Cliente " + nickname + " desconectado.");
        } finally {
            // 3. Garante que o cliente seja removido e os recursos liberados.
            removeClient();
        }
    }

    /**
     * Envia uma mensagem para todos os clientes conectados, exceto o remetente.
     * @param message A mensagem a ser enviada.
     * @param sender O cliente que enviou a mensagem original.
     */
    private void broadcastMessage(String message, ClientHandler sender) {
        // Itera sobre a lista de clientes.
        for (ClientHandler client : clients) {
            // Verifica se o cliente não é o remetente.
            if (client != sender) {
                client.out.println(message);
            }
        }
    }
    
    /**
     * Remove o cliente da lista de clientes ativos e fecha os recursos.
     */
    private void removeClient() {
        clients.remove(this);
        broadcastMessage(nickname + " saiu do chat.", this);
        try {
            if (in != null) in.close();
            if (out != null) out.close();
            if (clientSocket != null && !clientSocket.isClosed()) clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
