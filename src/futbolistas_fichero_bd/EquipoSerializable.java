package futbolistas_fichero_bd;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class EquipoSerializable {

	public static void main(String[] args) {
		
	    ArrayList<Equipo> aEquipo = new ArrayList<>();

	    aEquipo.add(new Equipo(1, "Athletic", "Bilbao"));

	    try {
	        FileOutputStream fos = new FileOutputStream("equipo.dat");
	        ObjectOutputStream oos = new ObjectOutputStream(fos);

	        for (Equipo e : aEquipo) {
	            oos.writeObject(e);
	        }

	        oos.close();
	        fos.close();
	    } catch (IOException i) {
	        i.printStackTrace();
	    }
	}
}