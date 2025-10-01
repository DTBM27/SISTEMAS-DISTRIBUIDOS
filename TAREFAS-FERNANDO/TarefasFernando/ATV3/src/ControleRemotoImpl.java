import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ControleRemotoImpl extends UnicastRemoteObject implements ControleRemoto {

    private boolean ligada;
    private int volume;
    private int canal;
    private boolean mudo;
    private int volumeAntesDoMudo;

    public ControleRemotoImpl() throws RemoteException {
        super(); 
        this.ligada = false;
        this.volume = 10;
        this.canal = 5;
        this.mudo = false;
        this.volumeAntesDoMudo = 10;
    }

    @Override
    public void ligar() throws RemoteException {
        System.out.println("Servidor: Recebido comando para LIGAR.");
        this.ligada = true;
    }

    @Override
    public void desligar() throws RemoteException {
        System.out.println("Servidor: Recebido comando para DESLIGAR.");
        this.ligada = false;
    }

    @Override
    public void aumentarVolume() throws RemoteException {
        if (ligada && volume < 100) {
            this.volume++;
            if(this.mudo) {
                alternarMudo(); 
            }
        }
    }

    @Override
    public void diminuirVolume() throws RemoteException {
        if (ligada && volume > 0) {
            this.volume--;
             if(this.mudo) {
                alternarMudo();
            }
        }
    }

    @Override
    public void avancarCanal() throws RemoteException {
        if (ligada) {
            this.canal++;
        }
    }

    @Override
    public void voltarCanal() throws RemoteException {
        if (ligada && canal > 1) {
            this.canal--;
        }
    }
    
    @Override
    public void alternarMudo() throws RemoteException {
        if (ligada) {
            this.mudo = !this.mudo;
            if (this.mudo) {
                this.volumeAntesDoMudo = this.volume;
                this.volume = 0;
            } else {
                this.volume = this.volumeAntesDoMudo;
            }
        }
    }

    @Override
    public String obterStatus() throws RemoteException {
        if (!ligada) {
            return "========================\n| TV está DESLIGADA. |\n========================";
        }
        
        String statusMudo = mudo ? "ATIVADO" : "DESATIVADO";
        
        return String.format(
            "========================\n" +
            "|      STATUS DA TV      |\n" +
            "------------------------\n" +
            "| Estado: LIGADA         |\n" +
            "| Canal:  %-14d |\n" +
            "| Volume: %-14d |\n" +
            "| Mudo:   %-14s |\n" +
            "========================",
            canal, volume, statusMudo
        );
    }
}