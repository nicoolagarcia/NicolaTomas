package modelos;

import static java.lang.Thread.sleep;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import controlador.*;

public class Reloj extends Thread {
    private Semaphore mutex;
    private ControladorSimulacion controlador;
    private Planificador planificador;
    private int ciclo;
    
    public Reloj(Semaphore mutex,Planificador dispatcher, ControladorSimulacion controlador) {
        this.mutex = mutex;
        this.ciclo = 0;
        this.planificador = dispatcher;
        this.controlador = controlador;
    }

    public Semaphore getMutex() {
        return mutex;
    }

    public void setMutex(Semaphore mutex) {
        this.mutex = mutex;
    }

    public ControladorSimulacion getControlador() {
        return controlador;
    }

    public void setControlador(ControladorSimulacion controlador) {
        this.controlador = controlador;
    }

    public Planificador getDispatcher() {
        return planificador;
    }

    public void setDispatcher(Planificador dispatcher) {
        this.planificador = dispatcher;
    }

    public int getCycle() {
        return ciclo;
    }

    public void setCycle(int cycle) {
        this.ciclo = cycle;
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
            this.planificador.updateWaitingTime();
            mutex.release();
            ciclo++;
            controlador.actulizarCiclo(ciclo);
        }
    }
    
}
