package futbolistas_fichero_bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class MainBd {

	public static void main(String[] args) {
		
		ArrayList<Futbolistas> aFutbolistas = new ArrayList<Futbolistas>();
		Futbolistas f = new Futbolistas();
		Futbolistas f2 = new Futbolistas();

		ArrayList<Equipo> aEquipo = new ArrayList<Equipo>();
		Equipo eq = new Equipo();
		Equipo eq2 = new Equipo();
		
		int opcion;
		int posicion;
		boolean modificadoe = false;
		boolean modificadof = false;
		int parar;
		
		try {
			ResultSet rs;
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/futbolistas", "root", "");
			System.out.println("Conexión Correcta.");
			Statement st = conexion.createStatement();
			rs = st.executeQuery("SELECT * FROM futbolistas.futbolistas;");
			while (rs.next()) {
				String dni = rs.getString("dni");
				aFutbolistas.add(new Futbolistas(rs.getString("dni"), rs.getString("nombre"), rs.getString("apellido"),
						rs.getInt("salario"),rs.getInt("idEquipo")));
			}
			rs = st.executeQuery("SELECT * FROM futbolistas.equipo;");
			while (rs.next()) {
				aEquipo.add(new Equipo(rs.getInt("idEquipo"), rs.getString("nombre"), rs.getString("ciudad")));
			}
		
			// cierro el resultset
			rs.close();
			// cierro el statement despues de realizar la consulta
			st.close();
			// cierro la conexion
		} catch (SQLException e) {
			// si no se ha conectado correctamente
			System.out.println("Error de conexión");
		}

		Scanner teclado = new Scanner(System.in);
		
		do {
			System.out.println("Elige una de las siguientes opciones");
			System.out.println("1- Ver futbolistas");
			System.out.println("2- Ver equipos");
			System.out.println("3- Agregar futbolista");
			System.out.println("4- Agregar equipo");
			System.out.println("0- Salir");
			System.out.println("Opcion: ");
			opcion = teclado.nextInt();
			
			switch (opcion) {
			case 1:
				for (posicion = 0; posicion < aFutbolistas.size(); posicion++) {
					aFutbolistas.get(posicion).print();
				}
				break;
			case 2:
				for (posicion = 0; posicion < aEquipo.size(); posicion++) {
					aEquipo.get(posicion).print();
				}
				break;
			case 3:
				parar = 1;
				System.out.println("Añadir Futbolista: ");
				System.out.println("--------------------------");
			
				do {
					f2.leer(teclado);
					if (aFutbolistas.contains(f2))
						System.out.println("El Futbolista ya existe");
					else {
						aFutbolistas.add(f2);
						System.out.println("El futbolista a sido añadido correctamente");
						modificadof = true;
					}
					System.out.println("Pulsa 0 para parar y 1 para continuar añadiendo");
					parar = teclado.nextInt();
					}while (parar!=0);
				
				break;
			case 4:
				parar = 1;
				System.out.println("Añadir Equipo: ");
				System.out.println("--------------------------");
			
				do {
					eq2.leer(teclado);
					if (aEquipo.contains(eq2))
						System.out.println("El Equipo ya existe");
					else {
						aEquipo.add(new Equipo(eq2));
						System.out.println("El Equipo a sido añadido correctamente");
						modificadoe = true;
					}
					System.out.println("Pulsa 0 para parar y 1 para continuar añadiendo");
					parar = teclado.nextInt();
					}while (parar!=0);
				
				break;
			default:
				System.out.println("Agur");
				
			}
		} while (opcion != 0);
		teclado.close();
		if (modificadof) {
			try {
				System.out.println("OK");
				Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/futbolistas", "root", "");
				String consulta = "";

				Statement st = conexion.createStatement();
				consulta = "DELETE FROM futbolistas;";
				st.executeUpdate(consulta);

				String dni;
				String nombre;
				String apellido;
				int salario;
				int idEquipo;

				for (posicion = 0; posicion < aFutbolistas.size(); posicion++) {
					// obtengo el elemento
					f = aFutbolistas.get(posicion);
					dni = f.getDni();
					nombre = f.getNombre();
					apellido = f.getApellido();
					salario = f.getSalario();
					idEquipo = f.getIdEquipo();
					// genero la consulta a ejecutar
					f.print();
					consulta = "insert into futbolistas.futbolistas values ('" + dni + "','" + nombre + "','" + apellido
							+ "','" + salario + "','" + idEquipo + "');";
					// ejecuto la consulta
					int retorno = st.executeUpdate(consulta);
				}
				// cierro el statement despues de realizar la consulta
				st.close();
				// cierro la conexion
				conexion.close();

			} catch (SQLException e) {
				System.out.println("Error de conexion");
			}
		}
		if (modificadoe) {
			try {
				System.out.println("OK");
				Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/futbolistas", "root", "");
				String consulta = "";

				Statement st = conexion.createStatement();
				consulta = "DELETE FROM equipo;";
				st.executeUpdate(consulta);

				int idEquipo;
				String nombre;
				String ciudad;
				
				for (posicion = 0; posicion < aEquipo.size(); posicion++) {
					// obtengo el elemento
					eq = aEquipo.get(posicion);
					idEquipo = eq.getIdEquipo();
					nombre = eq.getNombre();
					ciudad = eq.getCiudad();
					// genero la consulta a ejecutar
					eq.print();
					consulta = "insert into futbolistas.equipo values ('" + idEquipo + "','" + nombre + "','" + ciudad
							+ "');";
					// ejecuto la consulta
					st.executeUpdate(consulta);
				}
				// cierro el statement despues de realizar la consulta
				st.close();
				// cierro la conexion
				conexion.close();

			} catch (SQLException e) {
				System.out.println("Error de conexion");
			}
		}
	}
}

