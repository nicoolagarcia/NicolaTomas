package modelo;

import static java.lang.Thread.sleep;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import controlador.*;

public class Reloj extends Thread {
    private Semaphore mutex;
    private ControladorSimulacion controlador;
    private Planificador dispatcher;
    private int cycle;
    public Reloj(Semaphore mutex,Planificador dispatcher, ControladorSimulacion controlador) {
        this.mutex = mutex;
        this.cycle = 0;
        this.dispatcher = dispatcher;
        this.controlador = controlador;
    }
    
    @Override
    public void run() {
        while(true){
            try {
                sleep(controlador.getTiempo());
                mutex.acquire();
            } catch (InterruptedException ex) {
                Logger.getLogger(Interrupcion.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.dispatcher.updateWaitingTime();
            mutex.release();
            cycle++;
            controlador.actulizarCiclo(cycle);
        }
    }
    
}
