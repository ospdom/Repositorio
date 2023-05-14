package Hotel;

import java.util.Objects;
import java.util.Scanner;

public class Reservas {
	private static int nextId = 0;
	private int id_reserva;
	private int id_habitacion;
	private String dni_cliente;
	
	public Reservas () {
		this.id_reserva = nextId;
        nextId++;
	}
	
	public Reservas (Reservas v) {
		this.id_reserva = nextId;
        nextId++;
		this.id_reserva = v.id_reserva;
		this.id_habitacion = v.id_habitacion;
		this.dni_cliente = v.dni_cliente;
	}
	
	public Reservas(int id_reserva, int id_habitacion, String dni_cliente) {
		this.id_reserva = nextId;
        nextId++;
		this.id_reserva = id_reserva;
		this.id_habitacion = id_habitacion;
		this.dni_cliente = dni_cliente;
	}

	public int getId_reserva() {
		return id_reserva;
	}

	public void setId_reserva(int id_reserva) {
		this.id_reserva = id_reserva;
	}

	public int getId_habitacion() {
		return id_habitacion;
	}

	public void setId_habitacion(int id_habitacion) {
		this.id_habitacion = id_habitacion;
	}

	public String getDni_cliente() {
		return dni_cliente;
	}

	public void setDni_cliente(String dni_cliente) {
		this.dni_cliente = dni_cliente;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dni_cliente, id_habitacion, id_reserva);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reservas other = (Reservas) obj;
		return Objects.equals(dni_cliente, other.dni_cliente) && id_habitacion == other.id_habitacion
				&& id_reserva == other.id_reserva;
	}

	@Override
	public String toString() {
		return "Reservas [id_reserva=" + id_reserva + ", id_habitacion=" + id_habitacion + ", dni_cliente="
				+ dni_cliente + "]";
	}
	public void print() {

		System.out.println("=== Datos de Reserva ===");
		System.out.println("id_reserva:" + this.id_reserva);
		System.out.println("id_habitacion:" + this.id_habitacion);
		System.out.println("dni_cliente:" + this.dni_cliente);

	}

	public void leer(Scanner teclado) {
		
		System.out.println("Ingresa el id de la Habitación: ");
		this.id_habitacion = teclado.nextInt();

		System.out.println("Ingresa el DNI del Cliente: ");
		this.dni_cliente = teclado.next();

	}
	

}
