package futbolistas_fichero_bd;

import java.io.Serializable;
import java.util.Objects;
import java.util.Scanner;

public class Futbolistas implements Serializable {

	private static final long serialVersionUID = 6388767682977965355L;
	private String dni;
	private String nombre;
	private String apellido;
	private int salario;
	private int idEquipo;

	public Futbolistas() {

	}

	public Futbolistas(Futbolistas f) {
		this.dni = f.dni;
		this.nombre = f.nombre;
		this.apellido = f.apellido;
		this.salario = f.salario;
		this.idEquipo = f.idEquipo;
	}

	/**
	 * @param dni
	 * @param nombre
	 * @param apellido
	 * @param salario
	 * @param idEquipo
	 */
	public Futbolistas(String dni, String nombre, String apellido, int salario, int idEquipo) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.salario = salario;
		this.idEquipo = idEquipo;
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

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public int getSalario() {
		return salario;
	}

	public void setSalario(int salario) {
		this.salario = salario;
	}

	public int getIdEquipo() {
		return idEquipo;
	}

	public void setIdEquipo(int idEquipo) {
		this.idEquipo = idEquipo;
	}

	@Override
	public String toString() {
		return "Futbolistas [dni=" + dni + ", nombre=" + nombre + ", apellido=" + apellido + ", salario=" + salario
				+ ", idEquipo=" + idEquipo + "]";
	}

	public void print() {
		System.out.println("=== Datos del Futbolista ===");
		System.out.println("DNI:" + this.dni);
		System.out.println("Nombre:" + this.nombre);
		System.out.println("Apellido:" + this.apellido);
		System.out.println("Salario:" + this.salario);
		System.out.println("idEquipo:" + this.idEquipo);
	}

	public void leer(Scanner teclado) {

		System.out.println("Ingresa el DNI del Futbolista: ");
		this.dni = teclado.next();

		System.out.println("Ingresa el Nombre del Futbolista: ");
		this.nombre = teclado.next();

		System.out.println("Ingresa el Apellido del Futbolista: ");
		this.apellido = teclado.next();

		System.out.println("Ingresa el Salario del Futbolista: ");
		this.salario = teclado.nextInt();
		teclado.nextLine();

		System.out.println("Ingresa el idEquipo del Futbolista: ");
		this.idEquipo = teclado.nextInt();

	}

	@Override
	public int hashCode() {
		return Objects.hash(apellido, dni, idEquipo, nombre, salario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Futbolistas other = (Futbolistas) obj;
		return Objects.equals(apellido, other.apellido) && Objects.equals(dni, other.dni) && idEquipo == other.idEquipo
				&& Objects.equals(nombre, other.nombre)
				&& Double.doubleToLongBits(salario) == Double.doubleToLongBits(other.salario);
	}

}
