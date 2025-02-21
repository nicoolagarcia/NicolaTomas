package modelo;

import controlador.ControladorSimulacion;
import java.util.Comparator;
import micelaneos.*;

public class Planificador {
    private List readyList;
    private List blockedList;
    private List exitList;
    private List allProcessList;
    private ControladorSimulacion controlador;
    public int selectedAlgorithm;

    public Planificador(List readyList, List blockedList, List exitList,List allProcess, ControladorSimulacion controaldor) {
        this.controlador = controaldor;
        this.readyList = readyList;
        this.blockedList = blockedList;
        this.exitList = exitList;
        this.allProcessList = allProcess;
    }

    public int getSelectedAlgorithm() {
        return selectedAlgorithm;
    }

    public List getReadyList() {
        return readyList;
    }

    public void setSelectedAlgorithm(int selectedAlgorithm) {
        this.selectedAlgorithm = selectedAlgorithm;
    }
    
    public Proceso getProcess(){
        Proceso output = null;
        if(this.readyList.isEmpty()){
        
        if(selectedAlgorithm !=controlador.getPolitica()){
            selectedAlgorithm = controlador.getPolitica();
            sortReadyQueue(selectedAlgorithm);
            }
            Nodo pAux = this.readyList.getHead();
            this.readyList.delete(pAux);
            //aqui hay que actulizar la interfaz
            output = (Proceso) pAux.getValue();
            output.setEstado("running");
       
        }
         //aqui hay que actulizar la interfaz
        this.updateReadyList();
        this.updateProcessList();
        if(output == null){
            System.out.println("process null") ;
        }
        return output;    
    }
    
    /** Ordenar la cola de procesos antes de la selección **/
    private void sortReadyQueue(int schedulingAlgorithm) {
        switch (schedulingAlgorithm) {
            case 0: // FCFS (No requiere ordenamiento
                readyList=sortByWaitingTime(readyList);
                break;
            case 1: // Round Robin (Mantiene el orden)
                readyList=sortByWaitingTime(readyList);
                break;
            case 2: // SPN - Ordenar por menor duración
                readyList = sortByDuration(readyList);
                break;
            case 3: // SRT - Ordenar por menor tiempo restante
                readyList = sortByRemainingTime(readyList);
                break;
            case 4: // HRR - Ordenar por mayor Response Ratio
                readyList = sortByHRR(readyList);
                break;
        }
    }
    private List sortByWaitingTime(List list) {
        return bubbleSort(list, (p1, p2) -> Integer.compare(((Proceso) p2).getTiempoEspera(), ((Proceso) p1).getTiempoEspera()));
    }
    
    public boolean ifSRT(Proceso process){
        if(controlador.getPolitica() == 3){
        Nodo current = this.readyList.getHead();
        while (current != null) {
            if (((Proceso) current.getValue()).getInstrucciones() - ((Proceso) current.getValue()).getMar() < 
                    process.getInstrucciones()- process.getMar()) {
                return true;
            }
            current = current.getpNext();
        }    
        }
        return false;
    }
    
    /** Métodos de Ordenamiento **/
    private List sortByDuration(List list) {
        return bubbleSort(list, (p1, p2) -> Integer.compare(((Proceso) p1).getInstrucciones(), ((Proceso) p2).getInstrucciones()));
    }

    private List sortByRemainingTime(List list) {
        return bubbleSort(list, (p1, p2) -> Integer.compare(
                ((Proceso) p1).getInstrucciones() - ((Proceso) p1).getPc(),
                ((Proceso) p2).getInstrucciones() - ((Proceso) p2).getPc()
        ));
    }

    private List sortByHRR(List list) {
        return bubbleSort(list, (p1, p2) -> Double.compare(getHRR((Proceso) p2), getHRR((Proceso) p1)));
    }

    private double getHRR(Proceso p) {
        return (p.getTiempoEspera() + p.getInstrucciones()) / (double) p.getInstrucciones();
    }

    private List bubbleSort(List list, Comparator comparator) {
        if (list.getSize() <= 1) return list;

        boolean swapped;
        do {
            swapped = false;
            Nodo current = list.getHead();
            while (current != null && current.getpNext() != null) {
                if (comparator.compare(current.getValue(), current.getpNext().getValue()) > 0) {
                    Object temp = current.getValue();
                    current.setValue(current.getpNext().getValue());
                    current.getpNext().setValue(temp);
                    swapped = true;
                }
                current = current.getpNext();
            }
        } while (swapped);

        return list;
    }

    public void updatePCB(Proceso process,int programCounter,int memoryAddressRegister,String state){ 
        process.setEstado(state);
        process.setPc(programCounter);
        process.setMar(memoryAddressRegister);
        process.setTiempoEspera(0);
        if(state=="Bloqueado"){
            this.blockedList.appendLast(process);   
        }else if(state=="Listo"){
            this.readyList.appendLast(process);
        }else{
            this.exitList.appendLast(process);
        }
        this.updateReadyList();
        this.updateBlockedList();
        this.updateexitList();
        this.updateProcessList();
    }
    public void updatePCB(Proceso process,String state){
        process.setEstado(state);
        process.setTiempoEspera(0);
        if(state=="Bloqueado"){
            this.blockedList.appendLast(process);   
        }else if(state=="Listo"){
            this.readyList.appendLast(process);
        }else{
            this.exitList.appendLast(process);
        }
        this.updateReadyList();
        this.updateBlockedList();
        this.updateexitList();
        this.updateProcessList();
    }
    
    public void updateWaitingTime(){
        if(selectedAlgorithm != controlador.getPolitica()){
            selectedAlgorithm = controlador.getPolitica();
            sortReadyQueue(selectedAlgorithm);
            this.updateReadyList();
        }
        Nodo pAux = this.readyList.getHead();
        while(pAux!=null){
            Proceso process = (Proceso)pAux.getValue();
            int time = process.getTiempoEspera();
            process.setTiempoEspera(time+1);
            pAux = pAux.getpNext();
        }
        this.updateProcessList();
    }
    
    public void updateBlockToReady(int id){
        Nodo pAux = this.blockedList.getHead();
        while(pAux!=null){
            if(id== ((Proceso)pAux.getValue()).getId()){
                ((Proceso)pAux.getValue()).setEstado("ready");
                ((Proceso)pAux.getValue()).setTiempoEspera(0);
                blockedList.delete(pAux);
                readyList.appendLast(pAux);
                break;                
            }
            pAux = pAux.getpNext();
        }
        
        this.updateBlockedList();
        this.updateReadyList();
        this.updateProcessList();
    }
    
    public void updateProcessList(){
        Nodo pAux = allProcessList.getHead();
        String display = "";
        while(pAux!=null){
            Proceso process=(Proceso) pAux.getValue();
            display += this.stringInterfaz(process);
            pAux = pAux.getpNext();
        }
        controlador.setPcbs(display);
    }
    
    public void updateReadyList(){
        Nodo pAux = readyList.getHead();
        String display = "";
        while(pAux!=null){
            Proceso process=(Proceso) pAux.getValue();
            
            display += "\n ----------------------------------\n "
                    + "ID: " + process.getId() +
                      "\n Nombre: " + process.getNombre();
            pAux = pAux.getpNext();
        }
        controlador.setListosText(display);
    }
    public void updateBlockedList(){
        Nodo pAux = blockedList.getHead();
        String display = "";
        //System.out.println(pAux);
        while(pAux!=null){
            Proceso process=(Proceso) pAux.getValue();
            
            display += "\n ----------------------------------\n "
                    + "ID: " + process.getId() +
                      "\n Nombre: " + process.getNombre();
            pAux = pAux.getpNext();
        }
        controlador.setBloqueadosText(display);
    }
    
    public void updateexitList(){
        Nodo pAux = exitList.getHead();
        String display = "";
        //System.out.println(pAux);
        while(pAux!=null){
            Proceso process=(Proceso) pAux.getValue();
            
            display += "\n ----------------------------------\n "
                    + "Id: " + process.getId() +
                      "\n Nombre: " + process.getNombre();
            pAux = pAux.getpNext();
        }
        controlador.setSalidaText(display);
    }
    
    public static String stringInterfaz(Proceso currentProcess){
        String display = "\n ----------------------------------\n Id: " + currentProcess.getId() + 
                "\n Estado: " + currentProcess.getEstado()+ 
                "\n Nombre: " + currentProcess.getNombre() +
                "\n PC: " + currentProcess.getPc() + 
                "\n MAR: " + currentProcess.getMar() +
                "\n Espera: " + currentProcess.getTiempoEspera()
                ;
        return display;
    }
}
