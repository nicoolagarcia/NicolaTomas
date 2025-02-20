/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package micelaneos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author tomas
 */

public class Proceso {

    private int id;
    private String nombre;
    private int instrucciones;
    private String tipo;
    private int ciclosParaExcepcion;
    private int ciclosParaSatisfacerExcepcion;
    private int pc; // Program Counter
    private int mar; // Program Counter
    private int prioridad;
    private String estado;
    
//    
    public Proceso(){
    }
    
    @JsonCreator
    public Proceso(@JsonProperty("id") int id, @JsonProperty("nombre") String nombre, @JsonProperty("tipo") String tipo, @JsonProperty("instrucciones") int instrucciones,
                   @JsonProperty("ciclosParaExcepcion") int ciclosParaExcepcion,
                   @JsonProperty("ciclosParaSatisfacerExcepcion") int ciclosParaSatisfacerExcepcion, @JsonProperty("prioridad") int prioridad) {
        this.id = id;
        this.nombre = nombre;
        this.instrucciones = instrucciones;
        this.ciclosParaExcepcion = ciclosParaExcepcion;
        this.ciclosParaSatisfacerExcepcion = ciclosParaSatisfacerExcepcion;
        this.prioridad = prioridad;
        this.pc = 1; // Initialize Program Counter to 1
        this.mar = 0;
        this.estado = "Listo";
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getInstrucciones() {
        return instrucciones;
    }
    
    public int getCiclosParaExcepcion() {
        return ciclosParaExcepcion;
    }

    public int getCiclosParaSatisfacerExcepcion() {
        return ciclosParaSatisfacerExcepcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getPc() {
        return pc;
    }

    public void setPc(int pc) {
        this.pc = pc;
    }

    public int getMar() {
        return mar;
    }

    public void setMar(int mar) {
        this.mar = mar;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
