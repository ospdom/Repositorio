package futbolistas_fichero_bd;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class FutbolistasSerializable {
	
	public static void main(String[] args) {

    ArrayList<Futbolistas> aFutbolistas = new ArrayList<>();

    aFutbolistas.add(new Futbolistas("1111111A", "Antonio", "Rodelgo",40000,1));

    try {
        FileOutputStream fos = new FileOutputStream("futbolistas.dat");
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        for (Futbolistas f : aFutbolistas) {
            oos.writeObject(f);
        }

        oos.close();
        fos.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
}}
