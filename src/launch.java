import Client.Client;
import Model.ServerCommunication;

public class launch {
	
	public static void main(String[] args) {
		new Thread( new Runnable() {
		        public void run()  {
		        	ServerCommunication.listen();
		        }
		    } ).start();
		new Thread( new Runnable() {
	        public void run()  {
	        	Client cli = new Client();
	        }
	    } ).start();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("---------------------------------");
		new Thread( new Runnable() {
	        public void run()  {
	        	Client cli = new Client();
	        }
	    } ).start();
	}
	
}



