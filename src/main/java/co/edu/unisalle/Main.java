package co.edu.unisalle;

import co.edu.unisalle.interfaces.IGrafo;
import co.edu.unisalle.modelo.Grafo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Main {
    public static void main(String[] args) {

        System.out.println("Iniciando con grafos ....");

        Map<String, List<String>> graph = new HashMap<>();

        graph.put("Tunja", new ArrayList<>());
        graph.put("Bogotá", new ArrayList<>());

        // La relación funciona en ambos sentidos
        graph.get("Bogotá").add("Tunja");
        graph.get("Tunja").add("Bogotá");

        System.out.println(graph);

        System.out.println("************************************************************************************************");


        IGrafo grafo = new Grafo();

        // Crear nodos
        grafo.agregarNodo(1);
        grafo.agregarNodo(2);
        grafo.agregarNodo(3);
        grafo.agregarNodo(4);
        grafo.agregarNodo(5);
        grafo.agregarNodo(6);

        // Crear conexiones (aristas)
        grafo.agregarArista(1, 2);
        grafo.agregarArista(1, 3);
        grafo.agregarArista(2, 4);
        grafo.agregarArista(2, 5);
        grafo.agregarArista(3, 1);
        grafo.agregarArista(4, 6);
        grafo.agregarArista(5, 5);
        grafo.agregarArista(6, 5);
        grafo.agregarArista(1, 2);
        grafo.agregarArista(1, 2);
        grafo.agregarArista(1, 2);

        // Visualización y recorridos
        grafo.mostrarMatrizAdyacencia();
        grafo.mostrarListaAdyacencia();
        grafo.bfs(1);
        grafo.dfs(1);

        int origen = 1;
        int destino = 6;
        System.out.println("Camino más corto entre " + origen + " y " + destino + ": "
                + grafo.buscarCaminoMasCorto(origen, destino));
        System.out.println("Camino más largo entre " + origen + " y " + destino + ": "
                + grafo.buscarCaminoMasLargo(origen, destino));

    }
}
