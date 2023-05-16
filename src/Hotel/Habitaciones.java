package Hotel;

import java.util.Objects;
import java.util.Scanner;

public class Habitaciones {

	private static int nextId = 0;
	private int id;
	private String nombre;
	private String servicios;
	private int id_hotel;
	private boolean ocupado = false;

	public Habitaciones() {
		this.id = nextId;
        nextId++;
	}

	public Habitaciones(Habitaciones ha) {
		this.id = nextId;
        nextId++;
		this.nombre = ha.nombre;
		this.servicios = ha.servicios;
		this.id_hotel = ha.id_hotel;
		this.ocupado = ha.ocupado;
	}

	public Habitaciones(int id, String nombre, String servicios, int id_hotel, boolean ocupado) {
		this.id = nextId;
        nextId++;
		this.nombre = nombre;
		this.servicios = servicios;
		this.id_hotel = id_hotel;
		this.ocupado = ocupado;
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

	public String getServicios() {
		return servicios;
	}

	public void setServicios(String servicios) {
		this.servicios = servicios;
	}

	public int getId_hotel() {
	    return this.id_hotel;
	}

	public void setId_hotel(int id_hotel) {
		this.id_hotel = id_hotel;
	}

	public boolean isOcupado() {
		return ocupado;
	}

	public void setOcupado(boolean ocupado) {
		this.ocupado = ocupado;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, id_hotel, nombre, ocupado, servicios);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Habitaciones other = (Habitaciones) obj;
		return id == other.id && Objects.equals(id_hotel, other.id_hotel) && Objects.equals(nombre, other.nombre)
				&& ocupado == other.ocupado && Objects.equals(servicios, other.servicios);
	}

	@Override
	public String toString() {
		return "Habitaciones [id=" + id + ", nombre=" + nombre + ", servicios=" + servicios + ", id_hotel=" + id_hotel
				+ ", ocupado=" + ocupado + "]";
		
	}	
	public void print() {

		System.out.println("=== Datos de la Habitación ===");
		System.out.println("id:" + this.id);
		System.out.println("Nombre:" + this.nombre);
		System.out.println("Servicios:" + this.servicios);
		System.out.println("id_Hotel:" + this.id_hotel);
		System.out.println("ocupado:" + this.ocupado);

	}

	public void leer(Scanner teclado, boolean leerOcupado) {

		System.out.println("Ingresa el Nombre del Habitacióin: ");
		this.nombre = teclado.next();

		System.out.println("Ingresa los Servicios de la Habitacióin: ");
		this.servicios = teclado.next();
		
		if (leerOcupado) {
	        System.out.println("Ingresa el estado de ocupación de la Habitación (Ocupado=true/ Disponible=false): ");
	        this.ocupado = teclado.nextBoolean();

	}

}}

