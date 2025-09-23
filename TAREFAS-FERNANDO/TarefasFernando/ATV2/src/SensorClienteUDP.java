import java.io.IOException;
import java.net.*;
import java.util.Random;

public class SensorClienteUDP {

    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 9876;
    private static final int INTERVALO_ENVIO_MS = 1000;

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Uso: java SensorClienteUDP <ID_do_Sensor> <tipo>");
            System.out.println("Exemplo de tipo: 'temperatura' ou 'umidade'");
            return;
        }

        String sensorId = args[0];
        String sensorType = args[1].toLowerCase();

        try (DatagramSocket socket = new DatagramSocket()) {
            InetAddress serverAddress = InetAddress.getByName(SERVER_HOST);
            Random random = new Random();
            System.out.println("Sensor '" + sensorId + "' (" + sensorType + ") iniciado. Enviando dados para " + SERVER_HOST + ":" + SERVER_PORT);

            while (true) {
                String leitura = gerarLeituraSimulada(random, sensorType);
                String mensagem = sensorId + ": " + leitura;

                byte[] buffer = mensagem.getBytes();

                DatagramPacket sendPacket = new DatagramPacket(buffer, buffer.length, serverAddress, SERVER_PORT);

                socket.send(sendPacket);
                System.out.println("Enviado: " + mensagem);

                Thread.sleep(INTERVALO_ENVIO_MS);
            }

        } catch (IOException | InterruptedException e) {
            System.err.println("Erro no cliente: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static String gerarLeituraSimulada(Random random, String type) {
        if ("temperatura".equals(type)) {
            double temp = 15 + (15 * random.nextDouble());
            return String.format("%.1f C", temp);
        } else if ("umidade".equals(type)) {
            int umidade = 40 + random.nextInt(41);
            return String.format("%d%% RH", umidade);
        } else {
            return String.valueOf(random.nextInt(100));
        }
    }
}