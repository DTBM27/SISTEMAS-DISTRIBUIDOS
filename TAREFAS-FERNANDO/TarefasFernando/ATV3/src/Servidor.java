// Arquivo: Servidor.java

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class Servidor {

    public static void main(String[] args) {
        try {
            // 1. Cria uma instância da implementação do controle remoto
            ControleRemotoImpl objControle = new ControleRemotoImpl();

            // 2. Inicia o serviço de registro RMI na porta 1099 (padrão)
            // Isso facilita, pois não precisamos iniciar o 'rmiregistry' manualmente no terminal.
            LocateRegistry.createRegistry(1099);

            // 3. Registra o objeto remoto com um nome para que o cliente possa encontrá-lo.
            // O formato é: //<host>/<nome_do_servico>
            Naming.rebind("//localhost/ControleRemotoService", objControle);

            System.out.println("###################################");
            System.out.println("#                                 #");
            System.out.println("#      SERVIDOR RMI INICIADO      #");
            System.out.println("#                                 #");
            System.out.println("###################################");
            System.out.println("Aguardando conexões de clientes...");

        } catch (Exception e) {
            System.err.println("Erro no servidor: " + e.toString());
            e.printStackTrace();
        }
    }
}