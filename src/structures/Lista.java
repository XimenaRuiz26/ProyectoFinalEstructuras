package structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Lista<T> implements Iterable<T> {

    private Nodo<T> nodoPrimero;
    private Nodo<T> nodoUltimo;
    private int tamanio;

    public Lista() {
        nodoPrimero = null;
        nodoUltimo = null; // Corrección aquí
        tamanio = 0;
    }

    // Métodos básicos

    // Agregar al inicio de la lista
    public void agregarInicio(T valorNodo) {

        Nodo<T> nuevoNodo = new Nodo<>(valorNodo);

        if (estaVacia()) {
            nodoPrimero = nuevoNodo;
        } else {
            nuevoNodo.setSiguienteNodo(nodoPrimero);
            nodoPrimero = nuevoNodo;
        }
        tamanio++;
    }

    public Nodo<T> buscarNodoPorValor(T valor) {
        Nodo<T> actual = nodoPrimero;

        while (actual != null) {
            if (actual.getValorNodo().equals(valor)) {
                return actual;
            }
            actual = actual.getSiguienteNodo();
        }

        return null;
    }

    // Agregar al final de la lista
    public void agregarfinal(T valorNodo) {

        Nodo<T> nodo = new Nodo<>(valorNodo);

        if (estaVacia()) {
            nodoPrimero = nodoUltimo = nodo;
        } else {
            nodoUltimo.setSiguienteNodo(nodo);
            nodoUltimo = nodo;
        }

        tamanio++;
    }

    // Obtener el valor de un Nodo
    public T obtenerValorNodo(int indice) {

        Nodo<T> nodoTemporal = null;
        int contador = 0;

        if (indiceValido(indice)) {
            nodoTemporal = nodoPrimero;

            while (contador < indice) {
                nodoTemporal = nodoTemporal.getSiguienteNodo();
                contador++;
            }
        }

        if (nodoTemporal != null)
            return nodoTemporal.getValorNodo();
        else
            throw new RuntimeException("Índice no válido");
    }

    // Verificar si el índice es válido
    private boolean indiceValido(int indice) {
        if (indice >= 0 && indice < tamanio) {
            return true;
        }
        throw new RuntimeException("Índice no válido");
    }

    // Verificar si la lista está vacía
    public boolean estaVacia() {
        return (nodoPrimero == null);
    }

    /**
     * Imprime en consola la lista enlazada
     */
    public void imprimirLista() {

        Nodo<T> aux = nodoPrimero;

        while (aux != null) {
            System.out.print(aux.getValorNodo() + "\t");
            aux = aux.getSiguienteNodo();
        }

        System.out.println();
    }

    public void imprimir2() {
        Nodo<T> actual = nodoPrimero;
        while (actual != null) {
            T dato = actual.getValorNodo();
            System.out.println(dato.toString());
            actual = actual.getSiguienteNodo();
        }
    }

    // Eliminar dado el valor de un nodo
    public T eliminar(T dato) {
        Nodo<T> nodo = nodoPrimero;
        Nodo<T> previo = null;
        Nodo<T> siguiente;

        while (nodo != null) {
            if (nodo.getValorNodo().equals(dato)) {
                siguiente = nodo.getSiguienteNodo();
                if (previo == null) {
                    nodoPrimero = siguiente;
                } else {
                    previo.setSiguienteNodo(siguiente);
                }

                if (siguiente == null) {
                    // nodoUltimo = previo;
                } else {
                    nodo.setSiguienteNodo(null);
                }

                tamanio--;
                return dato;
            }
            previo = nodo;
            nodo = nodo.getSiguienteNodo();
        }

        throw new RuntimeException("El elemento no existe");
    }

    // Elimina el primer nodo de la lista
    public T eliminarPrimero() {

        if (!estaVacia()) {
            Nodo<T> n = nodoPrimero;
            T valor = n.getValorNodo();
            nodoPrimero = n.getSiguienteNodo();

            if (nodoPrimero == null) {
                // nodoUltimo = null;
            }

            tamanio--;
            return valor;
        }

        throw new RuntimeException("Lista vacía");
    }

    private Nodo<T> obtenerNodo(int indice) {

        if (indice >= 0 && indice < tamanio) {

            Nodo<T> nodo = nodoPrimero;

            for (int i = 0; i < indice; i++) {
                nodo = nodo.getSiguienteNodo();
            }

            return nodo;
        }

        return null;
    }

    /**
     * Cambia el valor de un nodo dada su posición en la lista
     * 
     * @param indice posición donde se va a cambiar el dato
     * @param nuevo  nuevo valor por el que se actualizará el nodo
     */
    public void modificarNodo(int indice, T nuevo) {

        if (indiceValido(indice)) {
            Nodo<T> nodo = obtenerNodo(indice);
            nodo.setValorNodo(nuevo);
        }

    }

    /**
     * Retorna la primera posición donde está guardado el dato
     * 
     * @param dato valor a buscar dentro de la lista
     * @return primera posición del dato
     */
    public int obtenerPosicionNodo(T dato) {

        int i = 0;

        for (Nodo<T> aux = nodoPrimero; aux != null; aux = aux.getSiguienteNodo()) {
            if (aux.getValorNodo().equals(dato)) {
                return i;
            }
            i++;
        }

        return -1;
    }

    @Override
    public Iterator<T> iterator() {
        return new IteradorListaSimple(nodoPrimero);
    }

    public class IteradorListaSimple implements Iterator<T> {

        private Nodo<T> nodo;
        private int posicion;

        /**
         * Constructor de la clase Iterador
         * 
         * @param aux Primer Nodo de la lista
         */
        public IteradorListaSimple(Nodo<T> nodo) {
            this.nodo = nodo;
            this.posicion = 0;
        }

        @Override
        public boolean hasNext() {
            return nodo != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T valor = nodo.getValorNodo();
            nodo = nodo.getSiguienteNodo();
            posicion++;
            return valor;
        }

        /**
         * Posición actual de la lista
         * 
         * @return posición
         */
        public int getPosicion() {
            return posicion;
        }

    }

    
    
    public Nodo<T> getNodoPrimero() {
		return nodoPrimero;
	}

	public void setNodoPrimero(Nodo<T> nodoPrimero) {
		this.nodoPrimero = nodoPrimero;
	}

	public int getTamanio() {
		return tamanio;
	}

	public void setTamanio(int tamano) {
		this.tamanio = tamano;
	}

}
