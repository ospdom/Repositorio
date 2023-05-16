package Hotel;

import java.io.Serializable;
import java.util.Objects;
import java.util.Scanner;

public abstract class Persona implements Serializable{


	private static final long serialVersionUID = -5127211784112841344L;
	protected String dni;
	protected String nombre;
	protected String contraseña;
	
	public Persona() {
		super();
		
	}
	
	public Persona(Persona p) {
		super();
		this.dni = p.dni;
		this.nombre = p.nombre;
		this.contraseña = p.contraseña;
		
	}
	public Persona(String dni, String nombre, String contraseña) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.contraseña = contraseña;
	}
	
	public abstract void print();
	public abstract void leer(Scanner teclado);

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

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	@Override
	public String toString() {
		return "Persona [dni=" + dni + ", nombre=" + nombre + ", contraseña=" + contraseña + "]";
	}
	

	@Override
	public int hashCode() {
		return Objects.hash(dni, nombre, contraseña);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Persona other = (Persona) obj;
		return Objects.equals(contraseña, other.contraseña) && Objects.equals(dni, other.dni)
				&& Objects.equals(nombre, other.nombre);
	}
	
}


