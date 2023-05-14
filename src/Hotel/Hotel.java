package Hotel;

import java.util.Objects;
import java.util.Scanner;

public class Hotel {
	
    private static int nextId = 0;
	private int id;
	private String nombre;
	private String ciudad;
	private String dni_director;

	public Hotel() {
		this.id = nextId;
        nextId++;
	}

	public Hotel(Hotel ho) {
		this.id = nextId;
        nextId++;
		this.nombre = ho.nombre;
		this.ciudad = ho.ciudad;
		this.dni_director = ho.dni_director;
	}

	public Hotel(int id, String nombre, String ciudad, String dni_director) {
		this.id = nextId;
        nextId++;
		this.nombre = nombre;
		this.ciudad = ciudad;
		this.dni_director = dni_director;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getDni_director() {
		return dni_director;
	}

	public void setDni_director(String dni_director) {
		this.dni_director = dni_director;
	}

	@Override
	public String toString() {
		return "Hotel [id=" + id + ", nombre=" + nombre + ", ciudad=" + ciudad + ", dni_director=" + dni_director + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(ciudad, dni_director, id, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Hotel other = (Hotel) obj;
		return Objects.equals(ciudad, other.ciudad) && Objects.equals(dni_director, other.dni_director)
				&& id == other.id && Objects.equals(nombre, other.nombre);
	}

	public void print() {

		System.out.println("=== Datos del Hotel ===");
		System.out.println("id:" + this.id);
		System.out.println("Nombre:" + this.nombre);
		System.out.println("Ciudad:" + this.ciudad);
		System.out.println("dni_director:" + this.dni_director);

	}

	public void leer(Scanner teclado) {

		System.out.println("Ingresa el Nombre del Hotel: ");
		this.nombre = teclado.next();

		System.out.println("Ingresa la Ciudad del Hotel: ");
		this.ciudad = teclado.next();

		System.out.println("Ingresa el dni del Director: ");
		this.dni_director = teclado.next();
		teclado.nextLine();

	}
	
}
