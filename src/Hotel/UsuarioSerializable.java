package Hotel;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class UsuarioSerializable {

	public static void main(String[] args) {

	    ArrayList<Usuario> aUsuario = new ArrayList<>();

	    aUsuario.add(new Usuario("11111111A", "Antonio", "a",0));
	    aUsuario.add(new Usuario("11111111B", "Arka", "a",1));
	    aUsuario.add(new Usuario("11111111C", "Joshua", "a",2));

	    try {
	        FileOutputStream fos = new FileOutputStream("Users.dat");
	        ObjectOutputStream oos = new ObjectOutputStream(fos);

	        for (Usuario u : aUsuario) {
	            oos.writeObject(u);
	            System.out.println(u);
	        }

	        oos.close();
	        fos.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
    }
}
