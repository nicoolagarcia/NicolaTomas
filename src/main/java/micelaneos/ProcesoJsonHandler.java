package micelaneos;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import micelaneos.List;
import micelaneos.Proceso;
import java.io.IOException;

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
    public static List<Proceso> readProcesosFromJson(String filePath){
        List procesos = new List();
        try{
            Proceso[] procesosArray = objectMapper.readValue(new File(filePath), Proceso[].class);
            procesos = new List<>();
            for (Proceso proceso : procesosArray) {
                procesos.appendLast(proceso);
            }
            
        }catch(Exception e){
            System.out.println("error cargar procesos");
        }
        return procesos;
    }
    
    public static void saveToJson(int[] numberArray, String filePath) {
        try {
            objectMapper.writeValue(new File(filePath), numberArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int[] readFromJson(String filePath) {
        try {
            return objectMapper.readValue(new File(filePath), int[].class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        int[] numbers = {5, 10};
        String filePath = "numbers.json";

        // Save to JSON
        ProcesoJsonHandler.saveToJson(numbers, filePath);

        // Read from JSON
        int[] readNumberArray = ProcesoJsonHandler.readFromJson(filePath);
        if (readNumberArray != null) {
            System.out.println(readNumberArray[0]);
            System.out.println(readNumberArray[1]);
        }
    }
}