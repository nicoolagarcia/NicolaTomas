/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package micelaneos;

/**
 *
 * @author tomas
 */

public class Proceso {

    private int id;
    private String nombre;
    private int instrucciones;
    private boolean cpuBound;
    private int ciclosParaExcepcion;
    private int ciclosParaSatisfacerExcepcion;
    private int pc; // Program Counter
    private int mar; // Program Counter
    private int prioridad;
    private String estado;

    public Proceso(int id, String nombre, int instrucciones, boolean cpuBound, int ciclosParaExcepcion, int ciclosParaSatisfacerExcepcion, int prioridad) {
        this.id = id;
        this.nombre = nombre;
        this.instrucciones = instrucciones;
        this.pc = 1; // Inicializar el Program Counter en 1
        this.mar = 0;
        this.estado = "Listo";
        this.ciclosParaExcepcion = ciclosParaExcepcion;
        this.ciclosParaSatisfacerExcepcion = ciclosParaSatisfacerExcepcion;
        this.prioridad = 0;
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

    public boolean isCpuBound() {
        return cpuBound;
    }

    public int getCiclosParaExcepcion() {
        return ciclosParaExcepcion;
    }

    public int getCiclosParaSatisfacerExcepcion() {
        return ciclosParaSatisfacerExcepcion;
    }
}
