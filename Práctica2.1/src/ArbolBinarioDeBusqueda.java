import java.util.*;

// Clase genérica para Árbol Binario de Búsqueda
public class ArbolBinarioDeBusqueda<T extends Comparable<T>> {
    protected Nodo<T> raiz;

    // Clase interna Nodo
    protected static class Nodo<T> {
        T dato;
        Nodo<T> izquierda, derecha;

        Nodo(T dato) {
            this.dato = dato;
        }
    }

    // Agregar un elemento al árbol
    public void add(T dato) {
        raiz = addRecursivo(raiz, dato);
    }

    private Nodo<T> addRecursivo(Nodo<T> nodo, T dato) {
        if (nodo == null) return new Nodo<>(dato);
        if (dato.compareTo(nodo.dato) < 0)
            nodo.izquierda = addRecursivo(nodo.izquierda, dato);
        else
            nodo.derecha = addRecursivo(nodo.derecha, dato);
        return nodo;
    }

    public ArbolBinarioDeBusqueda<T> getSubArbolIzquierda() {
        ArbolBinarioDeBusqueda<T> sub = new ArbolBinarioDeBusqueda<>();
        sub.raiz = (raiz != null) ? raiz.izquierda : null;
        return sub;
    }

    public ArbolBinarioDeBusqueda<T> getSubArbolDerecha() {
        ArbolBinarioDeBusqueda<T> sub = new ArbolBinarioDeBusqueda<>();
        sub.raiz = (raiz != null) ? raiz.derecha : null;
        return sub;
    }

    public int getGrado() {
        return getGradoRec(raiz);
    }

    private int getGradoRec(Nodo<T> nodo) {
        if (nodo == null) return 0;
        int gradoActual = 0;
        if (nodo.izquierda != null) gradoActual++;
        if (nodo.derecha != null) gradoActual++;
        return Math.max(gradoActual, Math.max(getGradoRec(nodo.izquierda), getGradoRec(nodo.derecha)));
    }

    public int getAltura() {
        return getAlturaRec(raiz);
    }

    private int getAlturaRec(Nodo<T> nodo) {
        if (nodo == null) return -1;
        return 1 + Math.max(getAlturaRec(nodo.izquierda), getAlturaRec(nodo.derecha));
    }

    public List<T> getListaDatosNivel(int nivel) {
        List<T> lista = new ArrayList<>();
        getDatosNivelRec(raiz, 0, nivel, lista);
        return lista;
    }

    private void getDatosNivelRec(Nodo<T> nodo, int actual, int objetivo, List<T> lista) {
        if (nodo == null) return;
        if (actual == objetivo) {
            lista.add(nodo.dato);
        } else {
            getDatosNivelRec(nodo.izquierda, actual + 1, objetivo, lista);
            getDatosNivelRec(nodo.derecha, actual + 1, objetivo, lista);
        }
    }

    public boolean isArbolHomogeneo() {
        return esHomogeneo(raiz);
    }

    private boolean esHomogeneo(Nodo<T> nodo) {
        if (nodo == null) return true;
        if ((nodo.izquierda == null && nodo.derecha != null) || (nodo.izquierda != null && nodo.derecha == null))
            return false;
        return esHomogeneo(nodo.izquierda) && esHomogeneo(nodo.derecha);
    }

    public boolean isArbolCompleto() {
        int altura = getAltura();
        return esCompleto(raiz, 0, altura);
    }

    private boolean esCompleto(Nodo<T> nodo, int nivel, int altura) {
        if (nodo == null) return true;
        if (nodo.izquierda == null && nodo.derecha == null) return nivel == altura;
        if (nodo.izquierda == null || nodo.derecha == null) return false;
        return esCompleto(nodo.izquierda, nivel + 1, altura) && esCompleto(nodo.derecha, nivel + 1, altura);
    }

    public boolean isArbolCasiCompleto() {
        List<Nodo<T>> lista = new ArrayList<>();
        Queue<Nodo<T>> cola = new LinkedList<>();
        if (raiz != null) cola.add(raiz);
        boolean fin = false;

        while (!cola.isEmpty()) {
            Nodo<T> actual = cola.poll();
            if (actual.izquierda != null) {
                if (fin) return false;
                cola.add(actual.izquierda);
            } else {
                fin = true;
            }

            if (actual.derecha != null) {
                if (fin) return false;
                cola.add(actual.derecha);
            } else {
                fin = true;
            }
        }
        return true;
    }

    public List<T> getCamino(T dato) {
        List<T> camino = new ArrayList<>();
        Nodo<T> actual = raiz;
        while (actual != null) {
            camino.add(actual.dato);
            int cmp = dato.compareTo(actual.dato);
            if (cmp == 0) break;
            actual = (cmp < 0) ? actual.izquierda : actual.derecha;
        }
        return camino;
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
        ordenCentral(raiz, lista);
        return lista;
    }

    private void ordenCentral(Nodo<T> nodo, List<T> lista) {
        if (nodo != null) {
            ordenCentral(nodo.izquierda, lista);
            lista.add(nodo.dato);
            ordenCentral(nodo.derecha, lista);
        }
    }
}
