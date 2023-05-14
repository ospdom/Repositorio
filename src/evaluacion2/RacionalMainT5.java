package evaluacion2;

import java.util.Scanner;

public class RacionalMainT5 {

	public static void main(String[] args) {
		// Clase para probar la clase Racional
		RacionalT5 r1 = new RacionalT5();
		System.out.println(r1);// 0/1
		RacionalT5 r2 = new RacionalT5(2,3); 
		System.out.println(r2);// 2/3
		RacionalT5 r3 = new RacionalT5(3); 
		System.out.println(r3);// 3/1
		RacionalT5 r4 = new RacionalT5(r2);
		System.out.println(r4);// 2/3
		// Getters and Setters
		int n;
		n = r4.getNumerador();
		int d;
		d = r4.getDenominador();
		System.out.println("Numerador: "+n+" Denominador: "+d);
		// hashCode and equals
		r4.setNumerador(4);
		r4.setDenominador(6);
		if(r4.equals(r2)) {
			// si son Iguales
			System.out.println(r4 + " y " + r2 + " son Iguales");
		}
		else {
			System.out.println(r4 + " y " + r2 + " NO son Iguales");
		}
		// compareTo
		if(r4.compareTo(r2) > 0) {
			System.out.println(r4 + " es MAYOR que " + r2);
		}
		else if(r4.compareTo(r2) < 0) {
			System.out.println(r4 + " es MENOR que " + r2);
		}
		else {
			System.out.println(r4 + " es IGUAL que " + r2);
		}
		
		// leer
		Scanner teclado = new Scanner(System.in);
		// leo r1
		System.out.println("Racional:");
		r1.leer(teclado);
		
		// saco el valor leido por pantalla
		System.out.println("Racional Leido: "+r1);
		
		// cierro teclado
		teclado.close();

	}
}
