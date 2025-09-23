import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.HashMap;
import java.util.Map;

public class ServidorSensoresUDP {

    private static final int PORT = 9876;
    private static final byte[] BUFFER = new byte[1024];

    public static void main(String[] args) {
        Map<String, String> ultimasLeituras = new HashMap<>();

        try (DatagramSocket socket = new DatagramSocket(PORT)) {
            System.out.println("Servidor UDP iniciado na porta " + PORT);
            System.out.println("Aguardando leituras dos sensores...");

            while (true) {
                DatagramPacket incomingPacket = new DatagramPacket(BUFFER, BUFFER.length);

                socket.receive(incomingPacket);

                String mensagem = new String(incomingPacket.getData(), 0, incomingPacket.getLength());

                String[] partes = mensagem.split(":", 2);

                if (partes.length == 2) {
                    String sensorId = partes[0].trim();
                    String leitura = partes[1].trim();

                    ultimasLeituras.put(sensorId, leitura);
                    
                    exibirPainel(ultimasLeituras);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro no servidor: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void exibirPainel(Map<String, String> leituras) {
       
        System.out.println("\n--- PAINEL DE MONITORAMENTO EM TEMPO REAL ---");
        if (leituras.isEmpty()) {
            System.out.println("Nenhuma leitura recebida ainda.");
        } else {
            for (Map.Entry<String, String> entry : leituras.entrySet()) {
                System.out.println("  " + entry.getKey() + ": " + entry.getValue());
            }
        }
        System.out.println("---------------------------------------------\n");
    }
}