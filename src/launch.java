import Client.Client;
import Model.ServerCommunication;

public class launch {
	
	public static void main(String[] args) {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new Thread( new Runnable() {
	        public void run()  {
	        	Client cli = new Client();
	        }
	    } ).start();
	}
	
}



