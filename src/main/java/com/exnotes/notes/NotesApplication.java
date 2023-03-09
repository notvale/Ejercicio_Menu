package com.exnotes.notes;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;


import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NotesApplication {

	/* Pedir al usuario que ingrese la cantidad de alumnos a los cuales les asignará más de una nota
	 * luego de que ingrese la cantidad de alumno y la cantidad de notas por alumnos se le mostrará un menú.
	 * opciones del menú: 
	 * mostrar el promedio de notas
	 * mostrar si la nota es aprobatria y reprobatoria
	 * mostrar si la nota está por sobre o por debajo del promedio del curso
	 */

	
//1.Fx promedio
	
	public static Double promedioNotas(ArrayList<Double> notas){
		Double suma = 0.0;
		for (int i = 0; i < notas.size(); i++) {
			
			suma = suma + notas.get(i);
		}
		return suma/notas.size();
	}

//2. Fx nota aprobatoria

public static Boolean aprobado(ArrayList<Double> notas, Double notaAprobatoria){
		Double promedio = promedioNotas(notas);
		if(promedio >= notaAprobatoria){
			return true;
		}else{
			return false; 
		}
	}

//3. Fux que verifica si la nota está sobre o bajo el promedio general.

public static void sobrePromedio(Double promedioGeneral, ArrayList<Double> notas, String nombreAlum){
		Double promedioAlumno = promedioNotas(notas);
		if(promedioAlumno>promedioGeneral){
			System.out.println("El alumno "+nombreAlum+" está sobre el promedio");
		}else if(promedioAlumno == promedioGeneral){
			System.out.println("El alumno "+nombreAlum+" es igual al promedio general");
		}else{
			System.out.println("El alumno "+nombreAlum+" está bajo el promedio");
		}
	}

	public static void main(String[] args) {

		Scanner teclado = new Scanner (System.in);
	
		String nomAlum = "";
		Double notaAprobatoria = 4.0;
		HashMap <String, ArrayList<Double>> libroClase = new HashMap<String, ArrayList<Double>>();
		  
		int cantAlum;
		int cantNotas;


		do{
			System.out.print("Indique la cantidad de alumnos que va a ingresar: ");
			cantAlum = teclado.nextInt();
			if(cantAlum <= 0){
				System.out.print("La cantidad de alumnos debe ser mayor a cero, porfavor intente denuevo");
			}

		}while(cantAlum <= 0);

		do{
			System.out.print("Indique la cantidad de notas por alumnos: ");
			cantNotas = teclado.nextInt();
			if(cantNotas <= 0){
				System.out.print("La cantidad de notas debe ser mayor a cero, por favor entente mas tarde");
			}
		}while(cantNotas <= 0);
		
		
		for(int i = 1; i <= cantAlum; i++){
			
			teclado.nextLine();
			ArrayList <Double> notasAlumnos = new ArrayList<Double>();
			System.out.print("Ingresa nombre del alumno: ");
			nomAlum = teclado.nextLine();
			for(int x = 1; x <= cantNotas; x++){
				System.out.print("Ingresa nota " +x+ " del alumno "+ nomAlum +": ");
				Double nota = teclado.nextDouble();
				notasAlumnos.add(nota);
			}
			libroClase.put(nomAlum, notasAlumnos);
		
		}
		
		
		int opcion = 1; 

		while(opcion != 0){

			do{
				System.out.println("**************Comienzo de Menú***************");
				System.out.println("Bienvenido/a");
				System.out.println("Seleccione 1 si quiere obtener el promedio de las notas por alumno.");
				System.out.println("Seleccione 2 si quiere ver si el alumno aprueba o reprueba");
				System.out.println("Seleccione 3 para ver si la nota está sobre o por debajo del promedio general");
				System.out.println("Seleccione 0 para salir del menú");
				System.out.print("Seleccione su opción: ");
				opcion = teclado.nextInt();
				
			}while(opcion < 0 || opcion > 3);

			if(opcion == 1){
				for(String i : libroClase.keySet()){
					//cada vez que ocupemos un for opara hashmap
					//el valor de la llave (en este caso el array)
					//está contenido dentro de la sintaxis nomHasmap.get(i)
					Double promAlum = promedioNotas(libroClase.get(i));
					System.out.println("El promedio del alumno: "+ i +" es de: " + promAlum);
				}
			}else if (opcion == 2){
				for(String i : libroClase.keySet()){
					Boolean aprobar = aprobado(libroClase.get(i), notaAprobatoria);
					//es lo mismo que poner if (aprobar == true)
					if(aprobar){
						System.out.println("El alumno/a "+i+" está aprobado");
					}else{
						System.out.println("El alumno/a "+i+" está reprobado");
					}
					
				}
			}else if(opcion == 3){
				Double sumaPromedio = 0.0;
				for(String i : libroClase.keySet()){
					sumaPromedio = sumaPromedio + promedioNotas(libroClase.get(i));
					
				}
				Double promedioGeneral = sumaPromedio / cantAlum;
				for(String i : libroClase.keySet()){
					sobrePromedio(promedioGeneral, libroClase.get(i), i);
				}
			}else{
				System.out.println("Gracias por cerrar el menú, hasta pronto :D");
			}

		}

		

		
	}

}


