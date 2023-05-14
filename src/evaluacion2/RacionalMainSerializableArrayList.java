package evaluacion2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class RacionalMainSerializableArrayList {

	public static void main(String[] args) {
		
	        ArrayList<RacionalT5> arrayList = new ArrayList<RacionalT5>();

	  
	        arrayList.add(new RacionalT5(1, 2));
	        arrayList.add(new RacionalT5(3, 4));
	        arrayList.add(new RacionalT5(5, 6));
	        arrayList.add(new RacionalT5(7, 8));
	        arrayList.add(new RacionalT5(9, 10));

	        ArrayList<RacionalT5> arrayList2 = new ArrayList<RacionalT5>();
	        RacionalT5 r = new RacionalT5();

	   

	        try {
	            FileOutputStream fos = new FileOutputStream("racionales.dat");
	            ObjectOutputStream oos = new ObjectOutputStream(fos);

	            for (int posicion = 0; posicion < arrayList.size(); posicion++) {
	                r = arrayList.get(posicion);
	                oos.writeObject(r);
	            }

	            oos.close();
	            fos.close();

	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	        try {
	            FileInputStream fis = new FileInputStream("racionales.dat");
	            ObjectInputStream ois = new ObjectInputStream(fis);

	            while (fis.available() > 0) {
	                r = (RacionalT5) ois.readObject();
	                arrayList2.add(r);
	            }

	            ois.close();
	            fis.close();

	        } catch (FileNotFoundException fnfe) {
	            System.out.println("Error archivo racionales.dat No encontrado");
	        } catch (IOException ioe) {
	            System.out.println("Error de Entrada y Salida");
	        } catch (ClassNotFoundException cnfe) {
	            System.out.println("Error Clase no Encontrada");
	        }

	        for (int posicion = 0; posicion < arrayList2.size(); posicion++) {
	            r = arrayList2.get(posicion);
	            System.out.println("Racional leido: " + r);
	        }
	    }

	}
