package micelaneos;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import micelaneos.List;
import micelaneos.Proceso;


public class ProcesoJsonHandler {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    // Write a List of Proceso objects to a JSON file
    // Write a List of Proceso objects to a JSON file
    public static void writeProcesosToJson(List<Proceso> procesos, String filePath) throws IOException {
        Proceso[] procesosArray = new Proceso[procesos.getSize()];
        for (int i = 0; i < procesos.getSize(); i++) {
            Proceso currentProcess = (Proceso) procesos.getNodoById(i).getValue();
            procesosArray[i] = currentProcess;
        }
        objectMapper.writeValue(new File(filePath), procesosArray);
    }

    // Read a List of Proceso objects from a JSON file
    public static List<Proceso> readProcesosFromJson(String filePath) throws IOException {
        Proceso[] procesosArray = objectMapper.readValue(new File(filePath), Proceso[].class);
        List<Proceso> procesos = new List<>();
        for (Proceso proceso : procesosArray) {
            procesos.appendLast(proceso);
        }
        return procesos;
    }

    public static void main(String[] args) {
        // Example usage
        Proceso proceso = new Proceso(1, "Proceso1", "I/O Bound", 100 , 10, 5, 1);
        List<Proceso> procesos = new List<>();
        procesos.appendLast(proceso);

        String filePath = "procesos.json";

        try {
            // Write Proceso objects to JSON file
            writeProcesosToJson(procesos, filePath);

            // Read Proceso objects from JSON file
            List<Proceso> readProcesos = readProcesosFromJson(filePath);

            // Print read Proceso objects
            for (int i = 0; i < readProcesos.getSize(); i++) {
                System.out.println(((Proceso) readProcesos.getNodoById(i).getValue()).getNombre());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}