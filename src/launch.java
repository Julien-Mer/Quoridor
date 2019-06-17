import Client.Client;
import Model.ServerCommunication;

public class launch {
	
	public static void main(String[] args) {
		boolean serveur = false;
		if(serveur) {
			new Thread( new Runnable() {
			        public void run()  {
			        	ServerCommunication.listen();
			        }
			    } ).start();
		}
		if(!serveur) {
			new Thread( new Runnable() {
		        public void run()  {
		        	try {
						Client cli = new Client();
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("Impossible de se connecter au serveur");
					}
		        }
		    } ).start();
		}
	}
	
}



