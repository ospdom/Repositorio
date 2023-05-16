package Hotel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HotelMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ArrayList<Usuario> aUsuario = new ArrayList<Usuario>();
		Usuario u = new Usuario();

		ArrayList<Hotel> aHotel = new ArrayList<Hotel>();
		Hotel ho = new Hotel();

		ArrayList<Habitaciones> aHabitaciones = new ArrayList<Habitaciones>();
		Habitaciones ha = new Habitaciones();

		ArrayList<Cliente> aCliente = new ArrayList<Cliente>();

		ArrayList<Reservas> aReservas = new ArrayList<Reservas>();
		Reservas r = new Reservas();

		int posicion;
		boolean modificadou = false;
		boolean modificadoho = false;
		boolean modificadoha = false;
		boolean modificador = false;

		try {
			ResultSet rs;
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/hoteles", "root", "");
			System.out.println("Conexión Correcta.");
			Statement st = conexion.createStatement();
			rs = st.executeQuery("SELECT * FROM hoteles.hotel");
			while (rs.next()) {
				int idHotel = rs.getInt("id");
				aHotel.add(new Hotel(rs.getInt("id"), rs.getString("nombre"), rs.getString("ciudad"),
						rs.getString("dni_director")));
			}
			rs = st.executeQuery("SELECT * FROM hoteles.habitaciones;");
			while (rs.next()) {
				aHabitaciones.add(new Habitaciones(rs.getInt("id"), rs.getString("nombre"), rs.getString("servicios"),
						rs.getInt("id_hotel"), rs.getBoolean("ocupado")));
			}
			rs = st.executeQuery("SELECT * FROM hoteles.cliente;");
			while (rs.next()) {
				aCliente.add(new Cliente(rs.getString("dni"), rs.getString("nombre")));
			}
			rs = st.executeQuery("SELECT * FROM hoteles.reservas;");
			while (rs.next()) {
				aReservas.add(
						new Reservas(rs.getInt("id_reserva"), rs.getInt("id_habitacion"), rs.getString("dni_cliente")));
			}
			
			rs.close();
			
			st.close();
			
		} catch (SQLException e) {
			
			System.out.println("Error de conexión");
		}

		FileInputStream fis;
		ObjectInputStream ois;
		try {
			fis = new FileInputStream("Users.dat");
			ois = new ObjectInputStream(fis);
			while (fis.available() > 0) {
				u = (Usuario) ois.readObject();
				aUsuario.add(u);
//		        u.print();
			}
			ois.close();
			fis.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String dniUsuario;
		String contrasena;
		Scanner scanner = new Scanner(System.in);

		if (aUsuario.isEmpty()) {
			System.out.println("No hay usuarios registrados en el sistema.");
		} else {
			boolean encontrado = false;

			while (!encontrado) {
				System.out.print("Dni de usuario: ");
				dniUsuario = scanner.next();
				System.out.print("Contraseña: ");
				contrasena = scanner.next();
				List<Usuario> copiaUsuario = new ArrayList<>(aUsuario);
				for (Usuario u2 : copiaUsuario) {
					if (u2.getDni().equals(dniUsuario) && u2.getContraseña().equals(contrasena)) {
						encontrado = true;
						String tipoUsuario = "";
						switch (u2.getTipo()) {

						case 0:
							tipoUsuario = "admin";
							boolean seguir = true;
							do {
								System.out.println("Bienvenido/a, " + tipoUsuario + " " + u2.getNombre() + "!");

								// Menú para administrador
								System.out.println("=====================================");
								System.out.println("1. Crear usuario");
								System.out.println("2. Ver lista de usuarios");
								System.out.println("3. Modificar tipo de usuario");
								System.out.println("4. Eliminar usuario");
								System.out.println("=====================================");
								System.out.println("=====================================");
								System.out.println("5. Crear Hotel");
								System.out.println("6. Ver lista Hoteles");
								System.out.println("7. Modificar Hotel");
								System.out.println("8. Eliminar Hotel");
								System.out.println("9. Salir");
								System.out.println("Seleccione una opción del menú: ");
								System.out.print("=====================================");
								int opcionAdmin = scanner.nextInt();

								switch (opcionAdmin) {

								case 1:
									int parar = 1;
									System.out.println("Añadir Usuario: ");
									System.out.println("--------------------------");
									do {
										Usuario nuevoUsuario = new Usuario();
										nuevoUsuario.leer(scanner);
										if (aUsuario.contains(nuevoUsuario)) {
											System.out.println("El Usuario ya existe");
										} else {
											aUsuario.add(nuevoUsuario);
											System.out.println("El Usuario ha sido añadido correctamente");
											modificadou = true;
										}
										System.out.println("Pulsa 0 para parar y 1 para continuar añadiendo");
										parar = scanner.nextInt();
									} while (parar != 0);
									break;
								case 2:
									for (posicion = 0; posicion < aUsuario.size(); posicion++) {
										aUsuario.get(posicion).print();
									}
									break;
								case 3:
									// Modificar tipo de usuario
									System.out.print("Introduce el dni del usuario a modificar: ");
									String dniModificar = scanner.next();
									boolean encontradoModificar = false;
									for (posicion = 0; posicion < aUsuario.size(); posicion++) {
										Usuario usuario = aUsuario.get(posicion);
										if (usuario.getDni().equals(dniModificar)) {
											encontradoModificar = true;
											System.out.print(
													"Introduce el nuevo tipo de usuario (0 para admin, 1 para director, 2 para empleado): ");
											int nuevoTipoUsuario = scanner.nextInt();
											usuario.setTipo(nuevoTipoUsuario);
											System.out.println("El usuario ha sido modificado");
											modificadou = true;
											break;
										}
									}
									if (!encontradoModificar) {
										System.out.println("Usuario no encontrado");
									}
									break;

								case 4:
									// Eliminar usuario
									System.out.print("Introduce el DNI del usuario a eliminar: ");
									String dniEliminar = scanner.next();
									boolean encontradoParaEliminar = false;
									for (posicion = 0; posicion < aUsuario.size(); posicion++) {
										Usuario usuario = aUsuario.get(posicion);
										if (usuario.getDni().equals(dniEliminar)) {
											encontradoParaEliminar = true;
											aUsuario.remove(posicion);
											System.out.println("El usuario ha sido eliminado");
											modificadou = true;
											break;
										}
									}
									if (!encontradoParaEliminar) {
										System.out.println("Usuario no encontrado");
									}
									break;
								case 5:
									int parar3 = 1;
									System.out.println("Añadir Hotel: ");
									System.out.println("--------------------------");
									do {
										Hotel nuevoHotel = new Hotel();
										nuevoHotel.leer(scanner);
										if (aHotel.contains(nuevoHotel)) {
											System.out.println("El Hotel ya existe");
										} else {
											aHotel.add(nuevoHotel);
											System.out.println("El Hotel ha sido añadido correctamente");
											modificadoho = true;
										}
										System.out.println("Pulsa 0 para parar y 1 para continuar añadiendo");
										parar3 = scanner.nextInt();
									} while (parar3 != 0);
									break;
								case 6:
									for (posicion = 0; posicion < aHotel.size(); posicion++) {
										aHotel.get(posicion).print();
									}
									break;
								case 7:
									// Modificar hotel
									System.out.print("Introduce el id del hotel a modificar: ");
									int idModificar = scanner.nextInt();
									boolean encontradoModificarHotel = false;
									for (posicion = 0; posicion < aHotel.size(); posicion++) {
										Hotel hotel = aHotel.get(posicion);
										if (hotel.getId() == idModificar) {
											encontradoModificarHotel = true;
											hotel.leer(scanner); 
																	
											System.out.println("El Hotel ha sido modificado");
											modificadoho = true;
											break;
										}
									}
									if (!encontradoModificarHotel) {
										System.out.println("Hotel no encontrado");
									}
									break;
								case 8:
									// Eliminar hotel
									System.out.print("Introduce el id del Hotel a eliminar: ");
									int idEliminar = scanner.nextInt();
									boolean hotelEliminar = false;
									for (posicion = 0; posicion < aHotel.size(); posicion++) {
										Hotel hotel = aHotel.get(posicion);
										if (hotel.getId() == idEliminar) {
											hotelEliminar = true;
											aHotel.remove(posicion);
											System.out.println("El Hotel ha sido eliminado");
											modificadoho = true;
											break;
										}
									}
									if (!hotelEliminar) {
										System.out.println("Hotel no encontrado");
									}
									break;
								case 9:
									System.out.println("¡Hasta pronto!");
									seguir = false;
									break;
								default:
									System.out.println("Opción no válida");
								}
							} while (seguir);
							break;
						case 1:
							tipoUsuario = "director";
							boolean seguir1 = true;
							do {
								System.out.println("Bienvenido/a, " + tipoUsuario + " " + u2.getNombre() + "!");

								// Menú para director
								System.out.println("=====================================");
								System.out.println("1. Añadir habitación");
								System.out.println("2. Ver habitaciónes");
								System.out.println("3. Modificar habitación");
								System.out.println("4. Eliminar habitación");
								System.out.println("5. Salir");
								System.out.println("Seleccione una opción del menú: ");
								System.out.print("=====================================");
								int opcionDirector = scanner.nextInt();
								switch (opcionDirector) {
								case 1:
									int parar1 = 1;
									System.out.println("Añadir Habitación: ");
									System.out.println("--------------------------");
									do {
										Habitaciones habitacionNueva = new Habitaciones();
										habitacionNueva.leer(scanner, false ); 
																				
										if (aHabitaciones.contains(habitacionNueva)) {
											System.out.println("La habitación ya existe");
										} else {
											aHabitaciones.add(habitacionNueva);
											System.out.println("La habitación ha sido añadido correctamente");
											modificadoha = true;
										}
										System.out.println("Pulsa 0 para parar y 1 para continuar añadiendo");
										parar1 = scanner.nextInt();
									} while (parar1 != 0);
									break;

								case 2:
									List<Hotel> hotelesMismoDirector = new ArrayList<>();
									for (Hotel hotel : aHotel) {
										if (hotel.getDni_director().equals(u2.getDni())) {
											hotelesMismoDirector.add(hotel);
										}
									}

									// Mostrar lista de hoteles
									System.out.println("Hoteles con el mismo director:");
									for (Hotel hotel : hotelesMismoDirector) {
										hotel.print();
									}

									// Mostrar habitaciones de los hoteles de la lista
									System.out.println("Habitaciones de los hoteles del mismo director:");
									for (Habitaciones habitacion : aHabitaciones) {
										for (Hotel hotel : hotelesMismoDirector) {
											if (habitacion.getId_hotel() == hotel.getId()) {
												habitacion.print();

											}
										}
									}
									break;

								case 3:
									// Crear lista de hoteles con el mismo dni_director que el usuario actual
									List<Hotel> hotelesMismoDirector2 = new ArrayList<>();
									for (Hotel hotel : aHotel) {
										if (hotel.getDni_director().equals(u2.getDni())) {
											hotelesMismoDirector2.add(hotel);
										}
									}
									// Mostrar habitaciones de los hoteles de la lista
									System.out.println("Habitaciones de los hoteles del mismo director:");
									for (Habitaciones habitacion : aHabitaciones) {
										for (Hotel hotel : hotelesMismoDirector2) {
											if (habitacion.getId_hotel() == hotel.getId()) {
												habitacion.print();
											}
										}
									}
									// Leer id de habitación a modificar
									System.out.print("Introduce el ID de la habitación a modificar: ");
									int idHabitacionModificar = scanner.nextInt();
									// Buscar habitación en la lista de habitaciones a partir del ID
									Habitaciones habitacionModificar = null;
									for (Habitaciones habitacion : aHabitaciones) {
										if (habitacion.getId() == idHabitacionModificar) {
											habitacionModificar = habitacion;
											break;
										}
									}
									// Modificar atributos de la habitación elegida
									if (habitacionModificar != null) {
										System.out.println("Introduce los nuevos valores para la habitación:");
										habitacionModificar.leer(scanner, false);
										modificadoha = true;
									} else {
										System.out.println(
												"La habitación con ID " + idHabitacionModificar + " no existe.");
									}
									break;

								case 4:

									// Crear lista de hoteles con el mismo dni_director que el usuario actual
									List<Hotel> hotelesMismoDirector3 = new ArrayList<>();
									for (Hotel hotel : aHotel) {
										if (hotel.getDni_director().equals(u2.getDni())) {
											hotelesMismoDirector3.add(hotel);
										}
									}
									// Mostrar habitaciones de los hoteles de la lista
									System.out.println("Habitaciones de los hoteles del mismo director:");
									for (Habitaciones habitacion : aHabitaciones) {
										for (Hotel hotel : hotelesMismoDirector3) {
											if (habitacion.getId_hotel() == hotel.getId()) {
												habitacion.print();
											}
										}
									}
									// Leer id de habitación a eliminar
									System.out.print("Introduce el ID de la habitación a eliminar: ");
									int idHabitacionEliminar = scanner.nextInt();
									// Buscar habitación en la lista de habitaciones a partir del ID
									Habitaciones habitacionEliminar = null;
									for (Habitaciones habitacion : aHabitaciones) {
										if (habitacion.getId() == idHabitacionEliminar) {
											habitacionEliminar = habitacion;
											break;
										}
									}
									// Eliminar la habitación elegida
									if (habitacionEliminar != null) {
										aHabitaciones.remove(habitacionEliminar);
										System.out.println(
												"La habitación con ID " + idHabitacionEliminar + " ha sido eliminada.");
										modificadoha = true;
									} else {
										System.out.println(
												"La habitación con ID " + idHabitacionEliminar + " no existe.");
									}
									break;

								case 5:
									System.out.println("¡Hasta pronto!");

									seguir1 = false;
									break;
								default:
									System.out.println("Opción no válida");
								}
							} while (seguir1);
							break;
						case 2:
							tipoUsuario = "empleado";
							boolean seguir2 = true;
							do {
								System.out.println("Bienvenido/a, " + tipoUsuario + " " + u2.getNombre() + "!");

								// Menú para empleado
								System.out.println("=====================================");
								System.out.println("1. Añadir nueva Reserva");
								System.out.println("2. Ver Reservas");
								System.out.println("3. Modificar Reserva");
								System.out.println("4. Eliminar Reserva");
								System.out.println("5. Salir");
								System.out.println("Seleccione una opción del menú: ");
								System.out.print("=====================================");
								int opcionEmpleado = scanner.nextInt();
								switch (opcionEmpleado) {
								case 1:
								    int parar2 = 1;
								    // Mostrar lista de habitaciones disponibles
								    System.out.println("Lista de habitaciones disponibles para reservar:");
								    for (Habitaciones habitacion : aHabitaciones) {
								        if (!habitacion.isOcupado()) {
								            habitacion.print();
								        }
								    }
								    System.out.println("Añadir Reserva: ");
								    System.out.println("--------------------------");
								    do {
								        System.out.print("Introduce el ID de la habitación para pasar a Ocupado: ");
								        int idHabitacion = scanner.nextInt();
								        
								        // Buscar la habitación por su ID
								        Habitaciones habitacionSeleccionada = null;
								        for (Habitaciones habitacion : aHabitaciones) {
								            if (habitacion.getId() == idHabitacion) {
								                habitacionSeleccionada = habitacion;
								                break;
								            }
								        }
								        
								        if (habitacionSeleccionada == null) {
								            System.out.println("Habitación no encontrada");
								            continue;
								        }
								        
								        if (habitacionSeleccionada.isOcupado()) {
								            System.out.println("La habitación ya está ocupada");
								            continue;
								        }
								        
								        // Cambiar estado de la habitación a ocupado
								        habitacionSeleccionada.setOcupado(true);
								        modificadoha = true;
								        System.out.println("La habitación ha pasado a estado: Ocupado");
								        
								        // Crear la reserva
								        Reservas nuevaReserva = new Reservas();
								        nuevaReserva.leer(scanner);
								        
								        if (aReservas.contains(nuevaReserva)) {
								            System.out.println("La reserva ya existe");
								        } else {
								            // Agregar nueva reserva
								            aReservas.add(nuevaReserva);
								            System.out.println("La reserva ha sido añadida correctamente");
								            modificador = true;
								        }
								        
								        System.out.println("Pulsa 0 para parar y 1 para continuar añadiendo");
								        parar2 = scanner.nextInt();
								    } while (parar2 != 0);
								    break;

									
								case 2:
									for (posicion = 0; posicion < aReservas.size(); posicion++) {
										aReservas.get(posicion).print();
									}
									break;
								case 3:
									// Modificar habitación
									System.out.print("Introduce el id de la Reserva a modificar: ");
									int idModificar = scanner.nextInt();
									boolean ModificarReserva = false;
									for (posicion = 0; posicion < aReservas.size(); posicion++) {
										Reservas reserva = aReservas.get(posicion);
										if (reserva.getId_reserva() == idModificar) {
											ModificarReserva = true;
											reserva.leer(scanner); // Se solicita el estado de ocupación de la
																	// habitación
											System.out.println("La reserva ha sido modificada");
											modificador = true;
											break;
										}
									}
									if (!ModificarReserva) {
										System.out.println("Reserva no encontrada");
									}
									break;
								case 4:
								    System.out.print("Introduce el ID de la reserva a eliminar: ");
								    int idEliminar = scanner.nextInt();
								    boolean reservaEliminar = false;
								    for (int posicion1 = 0; posicion1 < aReservas.size(); posicion1++) {
								        Reservas reserva = aReservas.get(posicion1);
								        if (reserva.getId_reserva() == idEliminar) {
								            reservaEliminar = true;
								            // Buscamos la habitación correspondiente a la reserva eliminada
								            for (Habitaciones habitacion : aHabitaciones) {
								                if (habitacion.getId() == reserva.getId_habitacion()) {
								                    habitacion.setOcupado(false);
								                    System.out.println("La habitación ha pasado a estado: Disponible");
								                    modificadoha = true;
								                    break;
								                }
								            }
								            aReservas.remove(posicion1);
								            System.out.println("La reserva ha sido eliminada");
								            modificador = true;
								            break;
								        }
								    }
								    if (!reservaEliminar) {
								        System.out.println("Reserva no encontrada");
								    }
								    break;
								case 5:
									System.out.println("¡Hasta pronto!");
									seguir2 = false;
									break;
								default:
									System.out.println("Opción no válida");
								}
							} while (seguir2);
							break;
						}
					}
				}

				if (!encontrado) {
					System.out.println("Nombre de usuario o contraseña incorrectos. Por favor, inténtelo de nuevo.");
				}

			}

		}
		scanner.close();
		if (modificadou) {
		    try {
		        FileOutputStream fos = new FileOutputStream("users.dat", false);
		        ObjectOutputStream oos = new ObjectOutputStream(fos);

		        oos.close();
		        fos.close();

		        fos = new FileOutputStream("users.dat");
		        oos = new ObjectOutputStream(fos);

		        for (int i = 0; i < aUsuario.size(); i++) {
		            Usuario u2 = aUsuario.get(i);
		            oos.writeObject(u2);
		        }

		        oos.close();
		        fos.close();
		        System.out.println("La información del fichero ha sido actualizada");
		    } catch (FileNotFoundException e1) {
		        e1.printStackTrace();
		    } catch (IOException e1) {
		        e1.printStackTrace();
		    }
		}


		if (modificadoho) {
			try {
				System.out.println("OK");
				Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/hoteles", "root", "");
				String consulta = "";

				Statement st = conexion.createStatement();
				consulta = "DELETE FROM hotel;";
				st.executeUpdate(consulta);

				int id;
				String nombre;
				String ciudad;
				String dni_director;

				for (posicion = 0; posicion < aHotel.size(); posicion++) {
					// obtengo el elemento
					ho = aHotel.get(posicion);
					id = ho.getId();
					nombre = ho.getNombre();
					ciudad = ho.getCiudad();
					dni_director = ho.getDni_director();
					// genero la consulta a ejecutar
					ho.print();
					consulta = "insert into hoteles.hotel values (" + id + ",'" + nombre + "','" + ciudad + "','"
							+ dni_director + "');";
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
		if (modificadoha) {
			try {
				System.out.println("OK");
				Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/hoteles", "root", "");
				String consulta = "";

				Statement st = conexion.createStatement();
				consulta = "DELETE FROM habitaciones;";
				st.executeUpdate(consulta);

				int id;
				String nombre;
				String servicios;
				int id_hotel;
				boolean Ocupado;

				for (posicion = 0; posicion < aHabitaciones.size(); posicion++) {
					// obtengo el elemento
					ha = aHabitaciones.get(posicion);
					id = ha.getId();
					nombre = ha.getNombre();
					servicios = ha.getServicios();
					id_hotel = ha.getId_hotel();
					Ocupado = ha.isOcupado();
					// genero la consulta a ejecutar
					ha.print();
					consulta = "insert into hoteles.habitaciones values (" + id + ",'" + nombre + "','" + servicios
							+ "'," + id_hotel + "," + Ocupado + ");";
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
		if (modificador) {
			try {
				System.out.println("OK");
				Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/hoteles", "root", "");
				String consulta = "";

				Statement st = conexion.createStatement();
				consulta = "DELETE FROM reservas;";
				st.executeUpdate(consulta);

				int id_reserva;
				int id_habitacion;
				String dni_cliente;

				for (posicion = 0; posicion < aReservas.size(); posicion++) {
					// obtengo el elemento
					r = aReservas.get(posicion);
					id_reserva = r.getId_reserva();
					id_habitacion = r.getId_habitacion();
					dni_cliente = r.getDni_cliente();

					// genero la consulta a ejecutar
					r.print();
					consulta = "insert into hoteles.reservas values (" + id_reserva + "," + id_habitacion + ",'"
							+ dni_cliente + "');";
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
