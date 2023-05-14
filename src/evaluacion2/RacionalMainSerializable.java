package evaluacion2;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;


public class RacionalMainSerializable {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RacionalT5 r1;
		RacionalT5 r2;
		RacionalT5 r3;
		
		
		try {
			FileOutputStream fos = new FileOutputStream("racionales.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			// lo grabo
			
			r1 = new RacionalT5(1,2);
			r2 = new RacionalT5(3,4);
			r3 = new RacionalT5(5,6);
			oos.writeObject(r1);
			oos.writeObject(r2);
			oos.writeObject(r3);
			// cierro el fichero
			oos.close();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

