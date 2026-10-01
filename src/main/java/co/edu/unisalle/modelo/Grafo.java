package co.edu.unisalle.modelo;

import co.edu.unisalle.interfaces.IGrafo;
import co.edu.unisalle.interfaces.INodo;

import java.util.*;

public class Grafo implements IGrafo {
    //(clave, valor)
    //Clave valor como diccionarios de python
    ///Lista : Nico , Brenda, Pepe
    ///clave:  111,    222,    333
    private Map<Integer, Nodo> nodos = new HashMap<>(); // Mapa de valor -> Nodo
    ///nodos serian los vertices

    // Agrega un nodo al grafo si no existe aún

    public void agregarNodo(int valor) {///Valor será el dato... T
        if (!nodos.containsKey(valor)) {
            ///constansKey
            ///nodos.put(p.dni, new Nodo(p))
            ///Valor equilvante a Persona
            ///key equivalente a Persona.dni
            nodos.put(valor, new Nodo(valor));
            ///Put es el equivalente al add
        }
    }

    // Agrega una arista (conexión) entre dos nodos existentes
    public void agregarArista(int origen, int destino) {

        if (nodos.containsKey(origen) && nodos.containsKey(destino)) {
            Nodo nodoOrigen = nodos.get(origen); //Agarro el nodo que tiene
            //como clave al origen... get retorna el NODO
            Nodo nodoDestino = nodos.get(destino);

            nodoOrigen.agregarVecino(nodoDestino);

            nodoDestino.agregarVecino(nodoOrigen); // Grafo no dirigido
        }
    }

    // Muestra la matriz de adyacencia del grafo
    public void mostrarMatrizAdyacencia() {
        System.out.println("Matriz de Adyacencia:");
        List<Integer> claves = new ArrayList<>(nodos.keySet());

        Collections.sort(claves); // Ordenar nodos por valor

        // Encabezado
        System.out.print("   ");
        for (int i : claves) System.out.print(i + " ");
        System.out.println();

        // Filas de la matriz
        for (int i : claves) { /// 1--- 2  ---- 3 Columnas
            System.out.print(i + ": ");
            for (int j : claves) {  /// 1 , 2, 3 --- Filas
                Nodo nodoI = nodos.get(i);
                Nodo nodoJ = nodos.get(j);
                System.out.print(nodoI.getVecinos().contains(nodoJ) ? "1 " : "0 ");
            }
            System.out.println();
        }
    }

    // Muestra la lista de adyacencia del grafo
    public void mostrarListaAdyacencia() {
        System.out.println("Lista de Adyacencia:");

        for (Map.Entry<Integer, Nodo> entrada : nodos.entrySet()) {

            System.out.print(entrada.getKey() + ": ");
            List<INodo> vecinos = entrada.getValue().getVecinos();

            for (INodo vecino : vecinos) {
                System.out.print(vecino.getValor() + " ");
            }
            System.out.println();
        }
    }

    // Recorrido en anchura (Breadth First Search)
    public void bfs(int inicio) {

        if (!nodos.containsKey(inicio)) return; // precondición
        ///Lista y cola
        Set<Integer> visitados = new HashSet<>(); // Conjunto de nodos visitados

        Queue<Nodo> cola = new LinkedList<>(); // Cola para el recorrido

        Nodo nodoInicio = nodos.get(inicio);
        cola.add(nodoInicio);
        visitados.add(inicio);

        System.out.println("Recorrido BFS:");
        while (!cola.isEmpty()) {
            Nodo actual = cola.poll();
            System.out.print(actual.getValor() + " ");

            for (INodo vecino : actual.getVecinos()) {
                if (!visitados.contains(vecino.getValor())) {
                    visitados.add(vecino.getValor());
                    cola.add((Nodo) vecino);
                }
            }
        }
        System.out.println();
    }

    // Recorrido en profundidad (Depth First Search)
    public void dfs(int inicio) {
        if (!nodos.containsKey(inicio)) return; // precondición

        Set<Integer> visitados = new HashSet<>();
        System.out.println("Recorrido DFS:");
        dfsRec(nodos.get(inicio), visitados); //Pila!!!!
        System.out.println();
    }

    // Función recursiva auxiliar para DFS
    private void dfsRec(Nodo actual, Set<Integer> visitados) {
        visitados.add(actual.getValor());
        System.out.print(actual.getValor() + " ");

        List<INodo> vecinos = actual.getVecinos();
        for (int i = vecinos.size() - 1; i >= 0; i--) {
            INodo vecino = vecinos.get(i);
            if (!visitados.contains(vecino.getValor())) {
                dfsRec((Nodo) vecino, visitados);
            }
        }
    }

    /** Busca un camino mínimo en número de aristas usando BFS. */
    @Override
    public List<Integer> buscarCaminoMasCorto(int origen, int destino) {
        if (!nodos.containsKey(origen) || !nodos.containsKey(destino)) return Collections.emptyList();

        Queue<Integer> cola = new LinkedList<>();
        Map<Integer, Integer> predecesores = new HashMap<>();
        Set<Integer> visitados = new HashSet<>();
        cola.add(origen);
        visitados.add(origen);

        while (!cola.isEmpty()) {
            int actual = cola.poll();
            if (actual == destino) return reconstruirCamino(origen, destino, predecesores);

            for (INodo vecino : vecinosOrdenados(nodos.get(actual))) {
                int valorVecino = vecino.getValor();
                if (visitados.add(valorVecino)) {
                    predecesores.put(valorVecino, actual);
                    cola.add(valorVecino);
                }
            }
        }
        return Collections.emptyList();
    }

    /** Busca el camino simple más largo. La búsqueda exhaustiva puede ser costosa en grafos grandes. */
    @Override
    public List<Integer> buscarCaminoMasLargo(int origen, int destino) {
        if (!nodos.containsKey(origen) || !nodos.containsKey(destino)) return Collections.emptyList();

        List<Integer> caminoActual = new ArrayList<>();
        List<Integer> mejorCamino = new ArrayList<>();
        Set<Integer> visitados = new HashSet<>();
        buscarCaminoMasLargoRec(origen, destino, visitados, caminoActual, mejorCamino);
        return mejorCamino;
    }

    private void buscarCaminoMasLargoRec(int actual, int destino, Set<Integer> visitados,
                                         List<Integer> caminoActual, List<Integer> mejorCamino) {
        visitados.add(actual);
        caminoActual.add(actual);

        if (actual == destino) {
            if (caminoActual.size() > mejorCamino.size()) {
                mejorCamino.clear();
                mejorCamino.addAll(caminoActual);
            }
        } else {
            for (INodo vecino : vecinosOrdenados(nodos.get(actual))) {
                int valorVecino = vecino.getValor();
                if (!visitados.contains(valorVecino)) {
                    buscarCaminoMasLargoRec(valorVecino, destino, visitados, caminoActual, mejorCamino);
                }
            }
        }

        caminoActual.remove(caminoActual.size() - 1);
        visitados.remove(actual);
    }

    private List<Integer> reconstruirCamino(int origen, int destino, Map<Integer, Integer> predecesores) {
        LinkedList<Integer> camino = new LinkedList<>();
        Integer actual = destino;
        while (actual != null) {
            camino.addFirst(actual);
            if (actual == origen) return camino;
            actual = predecesores.get(actual);
        }
        return Collections.emptyList();
    }

    private List<INodo> vecinosOrdenados(Nodo nodo) {
        List<INodo> vecinos = new ArrayList<>(nodo.getVecinos());
        vecinos.sort(Comparator.comparingInt(INodo::getValor));
        return vecinos;
    }
}
