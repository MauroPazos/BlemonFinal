package redes;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;

public class UtilConexion {
	public static Object bytesToObject(DatagramPacket paquete) {
		byte[] data = paquete.getData();
		ByteArrayInputStream in = new ByteArrayInputStream(data);
		ObjectInputStream is = null;
		try {
			is = new ObjectInputStream(in);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			return is.readObject();
		}
		catch(Exception e){
			
		}
		return null;
	}
	

	public static byte[] objectToBytes(Object objeto) {
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		ObjectOutputStream os;
		try {
			os = new ObjectOutputStream(bytes);
			os.writeObject(objeto);
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bytes.toByteArray();
	}
}
