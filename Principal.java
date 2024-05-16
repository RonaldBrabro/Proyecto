package Reto;

import java.util.ArrayList;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner leer = new Scanner(System.in);
        ArrayList<Reto> base = new ArrayList<>();
        Reto probabilidad1 = new Reto(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        double Vo, angulo, Vox, Voy, t, g, alcanceR, Altura, cos, sin, anguloRadianes;
        int opcion;
        g = 9.8;        
        do{
            System.out.println("  MENU  ");
            System.out.println("1.Ingresar datos");
            System.out.println("2.Mostrar datos");
            System.out.println("3.Calcular variables Estadisticas");
            System.out.println("4.Salir");
            System.out.println("Ingrese una opcion");
            opcion=leer.nextInt();
            switch(opcion){
                case 1:
                    System.out.println("Ingrese la velocidad inicial (m/s)");
                    Vo = leer.nextDouble();
                    System.out.println("Ingrese el angulo de lanzamiento");
                    angulo = leer.nextDouble();
                    anguloRadianes = Math.toRadians(angulo);
                    cos = Math.cos(anguloRadianes);
                    sin = Math.sin(anguloRadianes);
                    Vox = Vo * cos;
                    Voy = Vo * sin;
                    System.out.println("Desea que actue la fuerza y la friccion?");
                    System.out.println("1.Agregar Fuerza y friccion");
                    System.out.println("2.No es necesario");
                    System.out.println("Elija una opcion");
                    int opcion2;
                    opcion2=leer.nextInt();
                    switch(opcion2){
                        case 1:
                               System.out.println("Ingrese la fuerza aplicada (N)");
                                double fuerzaAplicada = leer.nextDouble();
                                System.out.println("Ingrese el coeficiente de fricción");
                                double coeficienteFriccion = leer.nextDouble();
                                System.out.println("Ingrese la masa del objeto(kg)");
                                double m=leer.nextDouble();
                                System.out.println("Ingrese el tiempo(s)");
                                t=leer.nextDouble();
                                double fuerzaFriccion = coeficienteFriccion * g;
                                double fuerzaHorizontal = fuerzaAplicada - fuerzaFriccion;
                                double fuerzaVertical = fuerzaAplicada;
                                double aceleracionHorizontal = fuerzaHorizontal / m; // m es la masa del objeto
                                double aceleracionVertical = (fuerzaVertical - m * g) / m; // Considerando la gravedad g
                                Vox = Vox + aceleracionHorizontal * t;
                                Voy = Voy + aceleracionVertical * t;
                                System.out.println("La velocidad inicial en el eje X es " + Vox + " m/s");
                                System.out.println("La velocidad inicial en el eje Y es " + Voy + " m/s");
                                break;
                        case 2:
                            System.out.println("La velocidad inicial en el eje X es " + Vox + " m/s");
                            System.out.println("La velocidad inicial en el eje Y es " + Voy + " m/s");
                            System.out.println("Datos guardados");
                            break;
                        default:
                            System.out.println("Elija una opcion valida");
                    }
        t=0;            
        t = Voy / g;
        alcanceR = Vox * t;
        Altura = Voy * Voy;
        Altura = Altura / (2 * g);
        Reto almacedados=new Reto(0,t,0, 0, 0, 0,0,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, Altura, alcanceR, 0);
          base.add(almacedados);
        break;
                case 2:
            System.out.println("Datos:");
        for (Reto probabilidades : base) {
            System.out.println("Tiempo maximo de vuelo: " + probabilidades.getT());
            System.out.println("Alcance maximo horizontal: " + probabilidades.getAlcanceR());
            System.out.println("Altura maxima: " + probabilidades.getAltura());
            System.out.println("--------------------");
    }
        break;

                case 3:
                    double sumaAlturas = 0;
                    double sumaAlcances = 0;
                    for (Reto probabilidades : base) {
                        sumaAlturas += probabilidades.getAltura();
                        sumaAlcances += probabilidades.getAlcanceR();
                    }
                    double mediaAlturas = sumaAlturas / base.size();
                    double mediaAlcances = sumaAlcances / base.size();
                    
                    ArrayList<Double> alturas = new ArrayList<>();
                    ArrayList<Double> alcances = new ArrayList<>();
                    for (Reto probabilidades : base) {
                        alturas.add(probabilidades.getAltura());
                        alcances.add(probabilidades.getAlcanceR());
                    }
                    double medianaAlturas = calcularMedianaSinOrdenar(alturas);
                    double medianaAlcances = calcularMedianaSinOrdenar(alcances);
                    
                    double sumaAlturasCuadrado = 0;
                    double sumaAlcancesCuadrado = 0;
                    for (Reto probabilidades : base) {
                        sumaAlturasCuadrado += Math.pow(probabilidades.getAltura() - mediaAlturas, 2);
                        sumaAlcancesCuadrado += Math.pow(probabilidades.getAlcanceR() - mediaAlcances, 2);
                    }
                    double desviacionAlturas = Math.sqrt(sumaAlturasCuadrado / base.size());
                    double desviacionAlcances = Math.sqrt(sumaAlcancesCuadrado / base.size());
              
                    int cuartil25 = base.size() / 4;
                    int cuartil75 = cuartil25 * 3;
                    double rangoInterCuartilAlturas = alturas.get(cuartil75) - alturas.get(cuartil25);
                    double rangoInterCuartilAlcances = alcances.get(cuartil75) - alcances.get(cuartil25);

                    System.out.println("Media de alturas: " + mediaAlturas);
                    System.out.println("Media de alcances horizontales: " + mediaAlcances);
                    System.out.println("Mediana de alturas: " + medianaAlturas);
                    System.out.println("Mediana de alcances horizontales: " + medianaAlcances);
                    System.out.println("Desviacion estándar de alturas: " + desviacionAlturas);
                    System.out.println("Desviacion estándar de alcances horizontales: " + desviacionAlcances);
                    System.out.println("Rango intercuartilico de alturas: " + rangoInterCuartilAlturas);
                    System.out.println("Rango intercuartilico de alcances horizontales: " + rangoInterCuartilAlcances);
                    break;
                case 4:
                    System.out.println("Saliendo del programa");
                        break;
                
                    
                default:
                        System.out.println("opcion no valida");
                        break;
            }
        }while(opcion!=4);
    }
        private static double calcularMedianaSinOrdenar(ArrayList<Double> lista) {
        int n = lista.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (lista.get(i) > lista.get(j)) {
                    double temp = lista.get(i);
                    lista.set(i, lista.get(j));
                    lista.set(j, temp);
                }
            }
        }
        if (n % 2 == 0) {
            return (lista.get(n / 2 - 1) + lista.get(n / 2)) / 2.0;
        } else {
            return lista.get(n / 2);
        }
    }
}