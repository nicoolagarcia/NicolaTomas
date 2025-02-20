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
public class List <T>{
    Nodo<T> head;
    Nodo<T> last;
    int size;

    public int getSize() {
        return size;
    }

    public Nodo<T> getLast() {
        return last;
    }
    
    
    public List() {
        head = null;
        last = null;
        size = 0;
    }
    public boolean isEmpty(){
        return head!=null;
    }
    
    public void delete(Nodo pDelete){
//        System.out.println(this.isEmpty());
         if(this.isEmpty()){
             Nodo pAux = head;
             if(head.getValue() != pDelete.getValue()){
                 head = head.getpNext();
                 delete(pDelete);
                 appendFirst(pAux);
             }else{
                 head = pDelete.getpNext();
//        System.out.println(this.isEmpty());
                 pDelete.setpNext(null);
                 size--;
             }
         }else{
             head = last = null;
         }
     }
    
    public void appendFirst(T x){
        Nodo <T> pNew = new Nodo <T> (x);
        if(isEmpty()){
            pNew.setpNext(head);
            head = pNew;
        }
        else{
            head = last = pNew;
            pNew.setpNext(null);
        }
        size++;
    }
    public void appendFirst(Nodo pNew){
//        NodoList <T> pNew = new NodoList <> (x);
        if(isEmpty()){
            pNew.setpNext(head);
            head = pNew;
        }
        else{
            head= last = pNew;
            pNew.setpNext(null);
        }
        size++;
    }
    public void appendLast(Nodo pNew){
        if(isEmpty()){
            last.setpNext(pNew);
            last = pNew;
            pNew.setpNext(null);
        }
        else{
            head = last = pNew;
            pNew.setpNext(null);
        }
        size++;
    }
    
    public void appendLast(T x){
//        NodoList pNew = new NodoList (x);
        Nodo <T> pNew = new Nodo <T> (x);
        if(isEmpty()){
            last.setpNext(pNew);
            last = pNew;
            pNew.setpNext(null);
        }
        else{
            head = last = pNew;
            pNew.setpNext(null);
        }
        size++;
    }

    public Nodo getHead() {
        return head;
    }
    
    public Nodo getNodoById(int k){
        Nodo pAux = head;
        if(k>=this.size){
            return null;
        }else{
            for(int i= 0;i<=k-1; i++){
                pAux = pAux.getpNext();
            }
            return pAux;
        }
    }
}
