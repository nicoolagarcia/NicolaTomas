/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package micelaneos;

/**
 *
 * @author tomas
 * @param <T>
 */
public class Nodo <T>{
    private Nodo pNext;
    private T value;

    public Nodo(T value) {
        this.pNext = null;
        this.value = value;
    }

    public Nodo getpNext() {
        return pNext;
    }

    public void setpNext(Nodo pNext) {
        this.pNext = pNext;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T  value) {
        this.value = value;
    }
}
