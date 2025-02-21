package operativos.nicolatomas;
import modelos.CPU;
import modelos.Planificador;
import modelos.Reloj;
import java.io.IOException;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import micelaneos.*;
import vistas.*;
import controlador.*;

public class NicolaTomas {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        String filepath = "procesos.json";
        List listos = ProcesoJsonHandler.readProcesosFromJson(filepath);
        
        String filepath1 = "numbers.json";
        int[] preferencias = ProcesoJsonHandler.readFromJson(filepath1);
        List todos = new List();
        List salida = new List();
        List bloqueados = new List();
        Nodo pw = listos.getHead();
        while(pw != null){
            todos.appendLast(pw.getValue());
            pw =pw.getpNext();
        }
        VistaSimulacion vista = new VistaSimulacion(preferencias[0],preferencias[1],listos,todos);
        Semaphore mutex = new Semaphore(1);
        ControladorSimulacion controlador = new ControladorSimulacion(vista);
        
        Planificador planificador = new Planificador(listos,bloqueados,salida,todos,controlador);
        
        CPU cpu1 = new CPU(controlador,planificador,1,mutex);
        CPU cpu2 = new CPU(controlador,planificador,2,mutex);
        CPU cpu3 = new CPU(controlador,planificador,3,mutex);
        Reloj reloj = new Reloj(mutex,planificador,controlador);
        CPU[] cpus = {cpu1,cpu2,cpu3};
        vista.setReloj(reloj);
        vista.setCpus(cpus);
    }
}
