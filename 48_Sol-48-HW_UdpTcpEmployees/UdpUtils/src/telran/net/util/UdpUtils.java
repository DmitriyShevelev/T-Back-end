package telran.net.util;
import java.io.*;
public class UdpUtils {
public static byte[] getByteArray(Serializable obj) throws Exception {
	try(ByteArrayOutputStream arrayStream = new ByteArrayOutputStream();
			ObjectOutputStream output = new ObjectOutputStream(arrayStream)) {
		output.writeObject(obj);
		return arrayStream.toByteArray();
		
	}
}
public static Serializable getSerializableFromByteArray(byte[] array, int length) throws Exception{
	try(ObjectInputStream input = new ObjectInputStream(new ByteArrayInputStream(array, 0, length)) ) {
		return (Serializable) input.readObject();
	}
}
}
