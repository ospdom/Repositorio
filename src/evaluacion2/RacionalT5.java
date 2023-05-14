package evaluacion2;

import java.io.Serializable;
import java.util.Scanner;

public class RacionalT5 implements Comparable<RacionalT5>, Serializable {

	// defino la clase Racional
	// propiedades
	private int numerador;
	private int denominador;

	// constructores
	// constructor por defecto
	public RacionalT5() {
		this.numerador = 0;
		this.denominador = 1;
	}

	// constructor copia
	public RacionalT5(RacionalT5 r) {
		this.numerador = r.numerador;
		this.denominador = r.denominador;
	}

	// constructores personalizados
	public RacionalT5(int numerador) {
		this.numerador = numerador;
		this.denominador = 1;
	}

	public RacionalT5(int numerador, int denominador) {
		this.numerador = numerador;
		// compruebo el valor del denominador
		if (denominador != 0) {
			// si NO es 0
			this.denominador = denominador;
		} else {
			// si es 0
			// muestro un mensaje de Error
			System.out.println("El denominador no puede ser 0. Se ha puesto 1 como denominador");
			// pongo 1 al denominador
			this.denominador = 1;
		}
	}

	// Getters and Setters
	public int getNumerador() {
		return numerador;
	}

	public void setNumerador(int numerador) {
		this.numerador = numerador;
	}

	public int getDenominador() {
		return denominador;
	}

	public void setDenominador(int denominador) {
		// compruebo el valor del denominador
		if (denominador != 0) {
			// si NO es 0
			this.denominador = denominador;
		} else {
			// si es 0
			// muestro un mensaje de Error
			System.out.println("El denominador no puede ser 0");
		}
	}

	// toString
	@Override
	public String toString() {
		return (numerador + "/" + denominador);
	}

	// hashCode
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + denominador;
		result = prime * result + numerador;
		return result;
	}

	// equals
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			// si es el mismo objeto
			return true;
		if (obj == null)
			// si obj no est� creado
			return false;
		if (getClass() != obj.getClass())
			// si los objetos NO son de la misma clase
			return false;
		// comparo el valor de las Propiedades
		RacionalT5 other = (RacionalT5) obj;
		if (this.numerador * other.denominador == this.denominador * other.numerador) {
			// si son el mismo Racional
			// intento simplificar
			if (this.numerador > other.numerador) {
				// simplifico
				this.numerador = other.numerador;
				this.denominador = other.denominador;
			}
			return true;
		}

		// si NO son el mismo Racional
		return false;
	}

	// compareTo
	@Override
	public int compareTo(RacionalT5 other) {
		int n1 = this.numerador * other.denominador;
		int n2 = this.denominador * other.numerador;
		if (n1 == n2) {
			// si son el mismo Racional
			// intento simplificar
			if (this.numerador > other.numerador) {
				// simplifico
				this.numerador = other.numerador;
				this.denominador = other.denominador;
			}
			return 0;
		} else if (n1 > n2) {
			// si this es mayor que other
			return (1);
		}
		// si this es menor que other
		return (-1);
	}

	public void leer(Scanner teclado) {
		// lee por teclado las propiedades
		// leo numerador
		System.out.println("Numerador: ");
		this.numerador = teclado.nextInt();
		// leo denominador
		System.out.println("Denominador: ");
		int denomi = teclado.nextInt();
		while (denomi == 0) {
			System.out.println("El denominador no puede ser 0");
			System.out.println("Denominador: ");
			denomi = teclado.nextInt();
		}
		this.denominador = denomi;
	}
}