public class ArbolBinarioDeBusquedaEnteros extends ArbolBinarioDeBusqueda<Integer> {

    private int suma(Nodo<Integer> nodo) {
        if (nodo == null) return 0;
        return nodo.dato + suma(nodo.izquierda) + suma(nodo.derecha);
    }

    public int getSuma() {
        return suma(raiz);
    }

}
