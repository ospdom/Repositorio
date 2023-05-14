package futbolistas_fichero_bd;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class MainFichero {

	public static void main(String[] args) {

		ArrayList<Futbolistas> aFutbolistas = new ArrayList<Futbolistas>();
		Futbolistas f = new Futbolistas();
		Futbolistas f2 = new Futbolistas();

		ArrayList<Equipo> aEquipo = new ArrayList<Equipo>();
		Equipo e = new Equipo();
		Equipo e2 = new Equipo();

		// LEER FUTBOLISTA
		FileInputStream fis;
		ObjectInputStream ois;
		try {
			fis = new FileInputStream("futbolistas.dat");
			ois = new ObjectInputStream(fis);
			while (fis.available() > 0) {
				f = (Futbolistas) ois.readObject();// convierte los bytes leido es un objeto de la clase
				aFutbolistas.add(f);
			}
			ois.close();
			fis.close();
			System.out.println("Futbolistas: " + f);
		} catch (FileNotFoundException a) {
			// TODO Auto-generated catch block
			a.printStackTrace();
			System.out.println("1");
		} catch (IOException a) {
			// TODO Auto-generated catch block
			a.printStackTrace();
			System.out.println("2");
		} catch (ClassNotFoundException a) {
			a.printStackTrace();
			System.out.println("3");
		}

		// LEER EQUIPO
		FileInputStream fiz;
		ObjectInputStream oiz;
		try {
			fiz = new FileInputStream("equipo.dat");
			oiz = new ObjectInputStream(fiz);
			while (fiz.available() > 0) {
				e = (Equipo) oiz.readObject();// convierte los bytes leido es un objeto de la clase
				aEquipo.add(e);
			}
			oiz.close();
			fiz.close();
			System.out.println("Equipo: " + e);
		} catch (FileNotFoundException i) {
			// TODO Auto-generated catch block
			i.printStackTrace();
			System.out.println("4");
		} catch (IOException i) {
			// TODO Auto-generated catch block
			i.printStackTrace();
			System.out.println("5");
		} catch (ClassNotFoundException i) {
			i.printStackTrace();
			System.out.println("6");
		}

		int opcion;
		int posicion;
		boolean modificadoe = false;
		boolean modificadof = false;
		int parar;

		Scanner teclado = new Scanner(System.in);

		do {
			System.out.println("Elige una de las siguientes opciones");
			System.out.println("1- Ver futbolistas");
			System.out.println("2- Ver equipos");
			System.out.println("3- Añadir futbolistas");
			System.out.println("4- Añadir equipo");
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
					e2.leer(teclado);
					if (aEquipo.contains(e2))
						System.out.println("El Equipo ya existe");
					else {
						aEquipo.add(new Equipo(e2));
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
        System.out.println("Fin del programa");
        if (modificadof) {
            try {
            FileOutputStream fos=new FileOutputStream("futbolistas.dat");
            ObjectOutputStream oos = new ObjectOutputStream (fos);
    
            for (Futbolistas f1 : aFutbolistas) {
                oos.writeObject(f1);
            }
            System.out.println("La información del fichero ha sido actualizada");
        oos.close();
        fos.close();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
        if (modificadoe) {
            try {
            FileOutputStream fos=new FileOutputStream("equipo.dat");
            ObjectOutputStream oos = new ObjectOutputStream (fos);
    
            for (Equipo e1 : aEquipo) {
                oos.writeObject(e1);
            }
            System.out.println("La información del fichero ha sido actualizada");

        oos.close();
        fos.close();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        }
       
		teclado.close();
		

	}
}
