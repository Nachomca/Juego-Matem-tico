/*
 * Crea un juego interactivo por terminal en el que tendrás que adivinar 
 * el resultado de diferentes
 * operaciones matemáticas aleatorias (suma, resta, multiplicación 
 * o división de dos números enteros).
 * - Tendrás 3 segundos para responder correctamente.
 * - El juego finaliza si no se logra responder en ese tiempo.
 * - Al finalizar el juego debes mostrar cuántos cálculos has acertado.
 * - Cada 5 aciertos debes aumentar en uno el posible número de cifras 
 *   de la operación (cada vez en un operando):
 *   - Preguntas 1 a 5: X (entre 0 y 9) operación Y (entre 0 y 9)
 *   - Preguntas 6 a 10: XX (entre 0 y 99) operación Y (entre 0 y 9)
 *   - Preguntas 11 a 15: XX operación YY
 *   - Preguntas 16 a 20: XXX (entre 0 y 999) operación YY
 *   ...
 */

package juegomatematico;

import java.util.Random;

/**
 *
 * @author nacho
 */

public class JuegoMatematico {
    
    private final int TIEMPO = 3;
    
    private static int [] generarOperacion(int dig1, int dig2)
    {
        int [] operacion = new int [3];
        
        //generar numero random
        Random r = new Random();
        int random;
        
        //numeros random
        int numero1 = r.nextInt(dig1 - 1);
        int numero2 = r.nextInt(dig2 - 1);
        //operacion random
        int operador = r.nextInt(3);
        
        operacion[0] = numero1;
        operacion[1] = operador;
        operacion[2] = numero2;
        
        return operacion;
    }
    
    private static void comprobarSolucion(int [] operacion)
    {
        //para comenzar a contar el tiempo
        Tiempo tiempo = new Tiempo();
        tiempo.Contar();
        
        int bandera = 0; //para salir del bucle cuando se acierta
        
        while(bandera == 0)
        {

            int resultado = 0;
            
            switch(operacion[1])
            {
                case 0:
                {
                    System.out.println("Resuelve: " + operacion[0] + " " + "+" + " " + operacion[2] + " =");
                    resultado =  operacion[0] + operacion[2];
                    break;
                }
                case 1:
                {
                    if(operacion[0] > operacion [2])
                    {
                        System.out.println("Resuelve: " + operacion[0] + " " + "-" + " " + operacion[2] + " =");
                        resultado =  operacion[0] - operacion[2];                    
                    }else
                    {
                        System.out.println("Resuelve: " + operacion[2] + " " + "-" + " " + operacion[0] + " =");
                        resultado =  operacion[2] - operacion[0];
                    }
                    break;
                }
                case 2:
                {
                    System.out.println("Resuelve: " + operacion[0] + " " + "*" + " " + operacion[2] + " =");
                    resultado =  operacion[0] * operacion[2];
                    break;
                }
                case 3:
                {
                    if(operacion[0] > operacion [2])
                    {
                        System.out.println("Resuelve: " + operacion[0] + " " + "/" + " " + operacion[2] + " =");
                        resultado =  operacion[0] / operacion[2];                    
                    }else
                    {
                        System.out.println("Resuelve: " + operacion[2] + " " + "/" + " " + operacion[0] + " =");
                        resultado =  operacion[2] / operacion[0];
                    }
                    break;
                }
            }
            
            int respuesta = leer.datoInt();
            
            if(respuesta == resultado)
            {
                tiempo.Detener();
                bandera = 1;
            }else
                System.out.println("Respuesta incorrecta");
            
        }
    }
    
    public static void perdido()
    {
        System.out.println("Has perdido!");
        System.exit(0);
    }

    public static void main(String[] args) {
        
        int contadorAciertos = 0; //para contar el numero de aciertos e ir avanzando en la dificultad
        
        //numero de digitos en cada numero
        int digitos1 = 10;
        int digitos2 = 10;
        
        while(true)
        {
            int [] operacion = generarOperacion(digitos1, digitos2);
            comprobarSolucion(operacion);
            
            contadorAciertos++;
            
            if(contadorAciertos % 5 == 0)
            {
                if(digitos2 > digitos1)
                    digitos1 = digitos1 * 10;
                else
                    digitos2 = digitos2 * 10;
            }
        }       
    }   
}