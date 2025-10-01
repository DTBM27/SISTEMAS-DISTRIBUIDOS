import java.rmi.Naming;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) {
        try {
            
            ControleRemoto controle = (ControleRemoto) Naming.lookup("//172.20.10.2/ControleRemotoService");

            Scanner scanner = new Scanner(System.in);
            int escolha = -1;

            System.out.println("Cliente conectado ao servidor de Controle Remoto!");

            while (escolha != 0) {
                System.out.println("\n--- MENU DO CONTROLE REMOTO ---");
                System.out.println("1. Ligar a TV");
                System.out.println("2. Desligar a TV");
                System.out.println("3. Aumentar Volume");
                System.out.println("4. Diminuir Volume");
                System.out.println("5. Avançar Canal");
                System.out.println("6. Voltar Canal");
                System.out.println("7. Ativar/Desativar Mudo");
                System.out.println("8. Mostrar Informações da TV");
                System.out.println("0. Sair");
                System.out.print("Escolha uma opção: ");

                try {
                    escolha = scanner.nextInt();

                    switch (escolha) {
                        case 1:
                            controle.ligar();
                            System.out.println("Comando [LIGAR] enviado.");
                            break;
                        case 2:
                            controle.desligar();
                            System.out.println("Comando [DESLIGAR] enviado.");
                            break;
                        case 3:
                            controle.aumentarVolume();
                            System.out.println("Comando [AUMENTAR VOLUME] enviado.");
                            break;
                        case 4:
                            controle.diminuirVolume();
                            System.out.println("Comando [DIMINUIR VOLUME] enviado.");
                            break;
                        case 5:
                            controle.avancarCanal();
                            System.out.println("Comando [AVANÇAR CANAL] enviado.");
                            break;
                        case 6:
                            controle.voltarCanal();
                             System.out.println("Comando [VOLTAR CANAL] enviado.");
                            break;
                        case 7:
                            controle.alternarMudo();
                            System.out.println("Comando [MUDO] enviado.");
                            break;
                        case 8:
                            break;
                        case 0:
                            System.out.println("Saindo... Obrigado por usar o controle remoto RMI!");
                            continue;
                        default:
                            System.out.println("Opção inválida. Tente novamente.");
                            continue;
                    }

                    System.out.println("\n" + controle.obterStatus());

                } catch (java.util.InputMismatchException e) {
                    System.out.println("Entrada inválida. Por favor, digite um número.");
                    scanner.next();
                }
            }
            scanner.close();

        } catch (Exception e) {
            System.err.println("Erro no cliente: " + e.toString());
            e.printStackTrace();
        }
    }
}