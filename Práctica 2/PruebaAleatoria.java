import java.util.*;

class PruebaAleatoria {
    public static void main(String[] args) {
        ArbolBinarioDeBusquedaEnteros arbol = new ArbolBinarioDeBusquedaEnteros();
        List<Integer> numeros = new ArrayList<>();
        for (int i = 0; i <= 128; i++) {
            numeros.add(i);
        }
        Collections.shuffle(numeros);

        for (int n : numeros) {
            arbol.add(n);
        }

        System.out.println("\n=== PRUEBA ALEATORIA ===");
        System.out.println("Suma total: " + arbol.getSuma());
        System.out.println("Suma en preorden: " + sumaLista(arbol.getListaPreOrden()));
        System.out.println("Suma en postorden: " + sumaLista(arbol.getListaPostOrden()));
        System.out.println("Suma en orden central: " + sumaLista(arbol.getListaOrdenCentral()));

        int sumaSubArboles = arbol.getSubArbolIzquierda().getSuma() + arbol.getSubArbolDerecha().getSuma() + arbol.getListaDatosNivel(0).get(0);
        System.out.println("Suma subárboles + raíz: " + sumaSubArboles);

        System.out.println("Altura del árbol: " + arbol.getAltura());
        List<Integer> camino110 = arbol.getCamino(110);
        System.out.println("Camino a 110: " + camino110);
        System.out.println("Longitud del camino: " + camino110.size());
    }

    public static int sumaLista(List<Integer> lista) {
        int suma = 0;
        for (int n : lista) suma += n;
        return suma;
    }
}
