import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class Servidor {

    public static void main(String[] args) {
        try {
            ControleRemotoImpl objControle = new ControleRemotoImpl();

            LocateRegistry.createRegistry(1099);

            Naming.rebind("//localhost/ControleRemotoScervice", objControle);

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