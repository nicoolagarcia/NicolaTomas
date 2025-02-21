/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;
import vistas.*;
/**
 *
 * @author DELL
 */
public class ControladorSimulacion {
    private VistaSimulacion vista;

    public ControladorSimulacion(VistaSimulacion vista) {
        this.vista = vista;
    }
    public void setCPUText(int id,String text) {
        if(id==1){
            vista.setCPU1(text);
        }else if(id ==2){
            vista.setCPU2(text);
        }else{
            vista.setCPU3(text);
        }
    }

    public VistaSimulacion getVista() {
        return vista;
    }

    public void setVista(VistaSimulacion vista) {
        this.vista = vista;
    }
    
    public void setListosText(String text) {
        vista.setListos(text);
    }

    public void setBloqueadosText(String text) {
        vista.setBloqueados(text);
    }

    public void setSalidaText(String text) {
        vista.setSalida(text);
    }
    public void setRelojGlobal(int i){
        vista.setReloj(i+"");
    }
    public int getPolitica(){
        return vista.getPolitica();
    }
    public int getTiempo(){
        return vista.getTiempoInstrucion();
    }
    public void actulizarCiclo(int i){
        vista.setReloj(i+"");
    }
    public void setPcbs(String text){
        vista.setPcbs(text);
    }
    public void updateDataset(int id, String t){
        vista.updateDataset(id, t, 1);
        vista.updateDataset(4, t, 1);
    }
}
