package Hotel;

import java.io.Serializable;
import java.util.Scanner;

public class Usuario extends Persona implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6505781804432594722L;
	private int tipo;
	
	public Usuario() {
		super();
		this.tipo = 0;
	}
	
	public Usuario(Usuario u) {
		super(u);
		this.tipo = u.tipo;
	}

	public Usuario(String dni, String nombre, String contraseña, int tipo) {
		super(dni, nombre, contraseña);
		this.tipo = tipo;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	
	@Override
	public String toString() {
		return "Usuario [tipo=" + tipo + "]";
	}

	@Override
	public void print() {
		
		System.out.println("=== Datos del Usuario ===");
		System.out.println("DNI:" + this.dni);
		System.out.println("Nombre:" + this.nombre);
		System.out.println("Contraseña:" + this.contraseña);
		System.out.println("Tipo:" + this.tipo);
		
	}

	@Override
	public void leer(Scanner teclado) {
		
		System.out.println("Ingresa el DNI del Usuario: ");
		this.dni = teclado.next();

		System.out.println("Ingresa el Nombre del Usuario: ");
		this.nombre = teclado.next();

		System.out.println("Ingresa la Contraseña del Usuario: ");
		this.contraseña = teclado.next();

		System.out.println("Ingresa el Tipo del Usuario: ");
		this.tipo = teclado.nextInt();
		teclado.nextLine();
		
	}

}
