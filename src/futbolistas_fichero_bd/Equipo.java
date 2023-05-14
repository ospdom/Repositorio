package futbolistas_fichero_bd;

import java.io.Serializable;
import java.util.Objects;
import java.util.Scanner;

public class Equipo implements Serializable{

	private static final long serialVersionUID = 424915510900564400L;
	private int idEquipo;
	private String nombre;
	private String ciudad;
	
	public Equipo() {
			
	}
	public Equipo(Equipo e) {
		this.idEquipo = e.idEquipo;
		this.nombre = e.nombre;
		this.ciudad = e.ciudad;
	}
	/**
	 * @param idEquipo
	 * @param nombre
	 * @param ciudad
	 */
	public Equipo(int idEquipo, String nombre, String ciudad) {
		super();
		this.idEquipo = idEquipo;
		this.nombre = nombre;
		this.ciudad = ciudad;
	}
	public int getIdEquipo() {
		return idEquipo;
	}
	public void setIdEquipo(int idEquipo) {
		this.idEquipo = idEquipo;
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
	@Override
	public String toString() {
		return "Equipo [idEquipo=" + idEquipo + ", nombre=" + nombre + ", ciudad=" + ciudad + "]";
	}
	public void print() {
		System.out.println("=== Datos del Equipo ===");
		System.out.println("idEquipo:" + this.idEquipo);
		System.out.println("Nombre:" + this.nombre);
		System.out.println("Apellido:" + this.ciudad);
	}
	public void leer(Scanner teclado) {
		
		System.out.println("Ingresa la Id del Equipo: ");
		this.idEquipo = teclado.nextInt();
		teclado.nextLine();
		
		System.out.println("Ingresa el Nombre del Equipo: ");
		this.nombre = teclado.next();
		
		System.out.println("Ingresa la Ciudad del Equipo: ");
		this.ciudad = teclado.next();
		
	}
	@Override
	public int hashCode() {
		return Objects.hash(ciudad, idEquipo, nombre);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Equipo other = (Equipo) obj;
		return Objects.equals(ciudad, other.ciudad) && idEquipo == other.idEquipo
				&& Objects.equals(nombre, other.nombre);
	}
	
}


