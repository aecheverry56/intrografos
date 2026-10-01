package co.edu.unisalle.interfaces;

import java.util.List;

public interface IGrafo {


        //G = {V, E}
        void agregarNodo(int valor); // pre: el valor no debe estar ya en el grafo
        void agregarArista(int origen, int destino); // pre: nodos deben existir

        void mostrarMatrizAdyacencia(); // Muestra la matriz de adyacencia
        void mostrarListaAdyacencia(); // Muestra la lista de adyacencia

        void bfs(int inicio); // pre: el nodo inicio debe existir
        void dfs(int inicio); // pre: el nodo inicio debe existir

        /** Devuelve el camino con menos aristas entre los nodos, o una lista vacía si no existe. */
        List<Integer> buscarCaminoMasCorto(int origen, int destino);

        /** Devuelve el camino simple con más aristas entre los nodos, o una lista vacía si no existe. */
        List<Integer> buscarCaminoMasLargo(int origen, int destino);


}
