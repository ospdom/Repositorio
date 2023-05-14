package Hotel;

import java.util.Objects;
import java.util.Scanner;

public class Cliente {

	private String dni;
	private String nombre;

	public Cliente() {

	}

	public Cliente(Cliente c) {
		this.dni = c.dni;
		this.nombre = c.nombre;
	}

	public Cliente(String dni, String nombre) {
		this.dni = dni;
		this.nombre = nombre;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dni, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(dni, other.dni) && Objects.equals(nombre, other.nombre);
	}

	@Override
	public String toString() {
		return "Cliente [dni=" + dni + ", nombre=" + nombre + "]";
	}
	public void print() {

		System.out.println("=== Datos del Cliente ===");
		System.out.println("dni:" + this.dni);
		System.out.println("Nombre:" + this.nombre);

	}

	public void leer(Scanner teclado) {

		System.out.println("Ingresa el DNI del Cliente: ");
		this.dni = teclado.next();

		System.out.println("Ingresa el Nombre del Cliente: ");
		this.nombre = teclado.next();

	}

}
