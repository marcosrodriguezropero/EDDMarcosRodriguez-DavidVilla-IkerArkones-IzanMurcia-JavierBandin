import java.util.*;

import java.util.*;

public class ArbolBinarioDeBusqueda<T extends Comparable<T>> {
    protected static class Nodo<T> {
        T dato;
        Nodo<T> izquierda, derecha;

        Nodo(T dato) {
            this.dato = dato;
        }
    }

    protected Nodo<T> raiz;

    public void add(T dato) {
        raiz = addRec(raiz, dato);
    }

    private Nodo<T> addRec(Nodo<T> actual, T dato) {
        if (actual == null) return new Nodo<>(dato);
        if (dato.compareTo(actual.dato) < 0) {
            actual.izquierda = addRec(actual.izquierda, dato);
        } else if (dato.compareTo(actual.dato) > 0) {
            actual.derecha = addRec(actual.derecha, dato);
        }
        return actual;
    }

    public List<T> getListaPreOrden() {
        List<T> lista = new ArrayList<>();
        preOrden(raiz, lista);
        return lista;
    }

    private void preOrden(Nodo<T> nodo, List<T> lista) {
        if (nodo != null) {
            lista.add(nodo.dato);
            preOrden(nodo.izquierda, lista);
            preOrden(nodo.derecha, lista);
        }
    }

    public List<T> getListaPostOrden() {
        List<T> lista = new ArrayList<>();
        postOrden(raiz, lista);
        return lista;
    }

    private void postOrden(Nodo<T> nodo, List<T> lista) {
        if (nodo != null) {
            postOrden(nodo.izquierda, lista);
            postOrden(nodo.derecha, lista);
            lista.add(nodo.dato);
        }
    }

    public List<T> getListaOrdenCentral() {
        List<T> lista = new ArrayList<>();
        enOrden(raiz, lista);
        return lista;
    }

    private void enOrden(Nodo<T> nodo, List<T> lista) {
        if (nodo != null) {
            enOrden(nodo.izquierda, lista);
            lista.add(nodo.dato);
            enOrden(nodo.derecha, lista);
        }
    }

    public List<T> getListaDatosNivel(int nivel) {
        List<T> lista = new ArrayList<>();
        obtenerNivel(raiz, nivel, 0, lista);
        return lista;
    }

    private void obtenerNivel(Nodo<T> nodo, int nivelObjetivo, int nivelActual, List<T> lista) {
        if (nodo == null) return;
        if (nivelActual == nivelObjetivo) {
            lista.add(nodo.dato);
        } else {
            obtenerNivel(nodo.izquierda, nivelObjetivo, nivelActual + 1, lista);
            obtenerNivel(nodo.derecha, nivelObjetivo, nivelActual + 1, lista);
        }
    }

    public int getAltura() {
        return calcularAltura(raiz);
    }

    private int calcularAltura(Nodo<T> nodo) {
        if (nodo == null) return -1;
        return 1 + Math.max(calcularAltura(nodo.izquierda), calcularAltura(nodo.derecha));
    }

    public int getGrado() {
        return calcularGrado(raiz);
    }

    private int calcularGrado(Nodo<T> nodo) {
        if (nodo == null) return 0;
        int gradoNodo = 0;
        if (nodo.izquierda != null) gradoNodo++;
        if (nodo.derecha != null) gradoNodo++;
        int gradoIzq = calcularGrado(nodo.izquierda);
        int gradoDer = calcularGrado(nodo.derecha);
        return Math.max(gradoNodo, Math.max(gradoIzq, gradoDer));
    }

    public boolean isArbolHomogeneo() {
        return comprobarHomogeneo(raiz);
    }

    private boolean comprobarHomogeneo(Nodo<T> nodo) {
        if (nodo == null) return true;
        if ((nodo.izquierda == null && nodo.derecha != null) || (nodo.izquierda != null && nodo.derecha == null)) {
            return false;
        }
        return comprobarHomogeneo(nodo.izquierda) && comprobarHomogeneo(nodo.derecha);
    }

    public boolean isArbolCompleto() {
        int totalNodos = contarNodos(raiz);
        return esCompleto(raiz, 0, totalNodos);
    }

    private boolean esCompleto(Nodo<T> nodo, int indice, int totalNodos) {
        if (nodo == null) return true;
        if (indice >= totalNodos) return false;
        return esCompleto(nodo.izquierda, 2 * indice + 1, totalNodos) &&
                esCompleto(nodo.derecha, 2 * indice + 2, totalNodos);
    }

    private int contarNodos(Nodo<T> nodo) {
        if (nodo == null) return 0;
        return 1 + contarNodos(nodo.izquierda) + contarNodos(nodo.derecha);
    }

    public boolean isArbolCasiCompleto() {
        int altura = getAltura();
        return esCasiCompleto(raiz, 0, altura);
    }

    private boolean esCasiCompleto(Nodo<T> nodo, int nivel, int altura) {
        if (nodo == null) return true;
        if (nivel == altura - 1) return true;
        if (nivel > altura - 1) return false;
        return esCasiCompleto(nodo.izquierda, nivel + 1, altura) && esCasiCompleto(nodo.derecha, nivel + 1, altura);
    }

    public List<T> getCamino(T valor) {
        List<T> camino = new ArrayList<>();
        Nodo<T> actual = raiz;
        while (actual != null) {
            camino.add(actual.dato);
            int cmp = valor.compareTo(actual.dato);
            if (cmp == 0) break;
            else if (cmp < 0) actual = actual.izquierda;
            else actual = actual.derecha;
        }
        return camino;
    }

    public ArbolBinarioDeBusqueda<T> getSubArbolIzquierda() {
        ArbolBinarioDeBusqueda<T> sub = new ArbolBinarioDeBusqueda<>();
        sub.raiz = (this.raiz != null) ? this.raiz.izquierda : null;
        return sub;
    }

    public ArbolBinarioDeBusqueda<T> getSubArbolDerecha() {
        ArbolBinarioDeBusqueda<T> sub = new ArbolBinarioDeBusqueda<>();
        sub.raiz = (this.raiz != null) ? this.raiz.derecha : null;
        return sub;
    }
}