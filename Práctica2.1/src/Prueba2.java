import java.util.ArrayList;
import java.util.List;

public class Prueba2 {

    public static void main(String[] args) {
        ArbolBinarioDeBusquedaEnteros arbol = new ArbolBinarioDeBusquedaEnteros();
        List<Integer> numeros = new ArrayList<Integer>();

        //Introducir números del 0 al 128
        for (int i = 0; i <= 128; i++) {
            numeros.add(i);
        }

        for (int num : numeros) {
            arbol.add(num);
        }

        //Definir la variable suma
        int suma = arbol.getSuma();
        System.out.println(suma);

        //Definir los 3 tipos de sumas dependiendo de su recorrido
        List<Integer> lista = arbol.getListaPreOrden();
        int sumaPre = 0;
        for (int i = 0; i < lista.size(); i++) {
            sumaPre += lista.get(i);
        }

        List<Integer> lista2 = arbol.getListaPostOrden();
        int sumaPost = 0;
        for (int i = 0; i < lista.size(); i++) {
            sumaPost += lista.get(i);
        }

        List<Integer> lista3 = arbol.getListaOrdenCentral();
        int sumaCentral = 0;
        for (int i = 0; i < lista.size(); i++) {
            sumaCentral += lista.get(i);
        }

        //Mostrar los resultados de los 3 tipos de sumas
        System.out.println("Suma1:" + sumaPre);
        System.out.println("Suma2:" + sumaPost);
        System.out.println("Suma3:" + sumaCentral);

        //Definimos los subárboles
        int sumaIzq = arbol.getSubArbolIzquierda().getSuma();
        int sumaDer = arbol.getSubArbolDerecha().getSuma();
        System.out.println("Suma Izquierda + Derecha: " + (sumaIzq + sumaDer));

        //Definimos la altura
        int altura = arbol.getAltura();
        System.out.println("Altura: " + altura);

        //Camino hacia el nodo 110
        List<Integer> camino = arbol.getCamino(110);
        System.out.println("Camino: " + camino);
        System.out.println("Longitud de camino: " + (camino.size() - 1));
    }
}
