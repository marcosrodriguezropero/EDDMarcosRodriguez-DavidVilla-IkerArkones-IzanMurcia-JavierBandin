import java.util.*;

class ArbolBinarioDeBusquedaEnteros extends ArbolBinarioDeBusqueda<Integer> {
    public int getSuma() {
        List<Integer> lista = getListaOrdenCentral();
        int suma = 0;
        for (int n : lista) suma += n;
        return suma;
    }

    @Override
    public ArbolBinarioDeBusquedaEnteros getSubArbolIzquierda() {
        ArbolBinarioDeBusquedaEnteros sub = new ArbolBinarioDeBusquedaEnteros();
        sub.raiz = (this.raiz != null) ? this.raiz.izquierda : null;
        return sub;
    }

    @Override
    public ArbolBinarioDeBusquedaEnteros getSubArbolDerecha() {
        ArbolBinarioDeBusquedaEnteros sub = new ArbolBinarioDeBusquedaEnteros();
        sub.raiz = (this.raiz != null) ? this.raiz.derecha : null;
        return sub;
    }
}
