package Model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.LinkedList;

public class File {

	public final static String SAVE_FILE = "save.ser";
	
	/**
	 * Write a serialization of the object 
	 * @param fileName the fileName of the file
	 * @param object the object to serialize
	 */
	public static void writeObject(String fileName, Object object) {
		try {
			ObjectOutputStream out = new ObjectOutputStream( new FileOutputStream(fileName));
			out.writeObject(object);
			out.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Read the serialized object in the file
	 * @param fileName the fileName of the file
	 * @return the object unserialized
	 */
	public static Object readObject(String fileName) {
		Object res = null;
		try {
			ObjectInputStream in = new ObjectInputStream( new FileInputStream(fileName));
			res = in.readObject();
			in.close();
		} catch (Exception ex) { }
		return res;
	}
	
	/**
	 * Read the data of a file
	 * @param fileName the name of the file
	 * @return the date of the file
	 */ 
	public static String readFile(String fileName) {
		String res = "";
		try {
			InputStream  in = new FileInputStream(fileName);
			
			BufferedReader buffer = new BufferedReader(new InputStreamReader(in)); 
			String line = buffer.readLine(); 
			while(line != null){ 
				res += line + "\n";
				line = buffer.readLine();
			}
			in.close();
		} catch (IOException e) { } 
		return res;
	}
	
	/**
	 * Save data into a file
	 * @param fileName the name of the file
	 * @param data the data to write
	 */
	public static void writeFile(String fileName, String data) {
		try {
			OutputStream out = new FileOutputStream(fileName);
			BufferedWriter buffer = new BufferedWriter(new OutputStreamWriter(out));
			buffer.write(data);
			buffer.flush();
			out.close();
		} catch(Exception ex) { }
	}

}