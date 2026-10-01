package co.edu.unisalle.modelo;

import co.edu.unisalle.interfaces.INodo;

import java.util.ArrayList;
import java.util.List;

public class Nodo implements INodo {
    private int valor; // Valor almacenado en el nodo; T <T>

    private List<INodo> vecinos; // Lista de nodos vecinos (adyacentes)

    // Constructor
    public Nodo(int valor) {
        this.valor = valor;
        this.vecinos = new ArrayList<>(); ///crea la lista!!! VACIO!!!
    }

    // Getter del valor
    public int getValor() {
        return valor;
    }

    // Setter del valor
    public void setValor(int valor) {
        this.valor = valor;
    }





    // Agrega un nodo vecino a la lista de adyacencia
    public void agregarVecino(INodo vecino) {

        if (!vecinos.contains(vecino)) {
            vecinos.add(vecino);
        }

    }








    // Devuelve la lista de vecinos
    public List<INodo> getVecinos() {
        return vecinos;
    }
}
