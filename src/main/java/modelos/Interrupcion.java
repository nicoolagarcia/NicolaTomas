/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import micelaneos.*;
import controlador.*;

public class Interrupcion extends Thread {

    private int originCPU;
    private int exceptionCycle; 
    private ControladorSimulacion controlador; 
    private int processId;
    private List interruptionList;
    private Semaphore mutex;

    public Interrupcion(int originCPU, int exceptionCycle, ControladorSimulacion controlador, int processId, List interruptionList, Semaphore mutex) {
        this.controlador = controlador;
        this.originCPU = originCPU;
        this.exceptionCycle = exceptionCycle;
        this.processId = processId;
        this.interruptionList = interruptionList;
        this.mutex = mutex;
    }

    public int getOriginCPU() {
        return originCPU;
    }

    public void setOriginCPU(int originCPU) {
        this.originCPU = originCPU;
    }

    public int getExceptionCycle() {
        return exceptionCycle;
    }

    public void setExceptionCycle(int exceptionCycle) {
        this.exceptionCycle = exceptionCycle;
    }

    public int getProcessId() {
        return processId;
    }

    public void setProcessId(int processId) {
        this.processId = processId;
    }

    public List getInterruptionList() {
        return interruptionList;
    }

    public void setInterruptionList(List interruptionList) {
        this.interruptionList = interruptionList;
    }

    public Semaphore getMutex() {
        return mutex;
    }

    public void setMutex(Semaphore mutex) {
        this.mutex = mutex;
    }
    
    
    
    /**
     * Clase para las interruciones
     */
    @Override
    public void run(){
 
        for(int i = 0; i <= this.exceptionCycle;i++){
            try {
                sleep(controlador.getTiempo());
            } catch (InterruptedException ex) {
                Logger.getLogger(Interrupcion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }  
        try {
            //Aqui va un semaforo
            mutex.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(Interrupcion.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.interruptionList.appendLast(this);
        mutex.release();
        
    }

}

