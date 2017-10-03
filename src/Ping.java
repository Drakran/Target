import rxtxrobot.ArduinoNano;
import rxtxrobot.RXTXRobot;

public class Ping {

	final private static int PING_PIN = 12; 
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RXTXRobot r = new ArduinoNano(); // Create RXTXRobot object 
		r.setPort("COM5"); // Set the port to COM3 
		r.connect(); 
		int times = 10;
		for(int x =0; x < times; x++) {
			System.out.println("Response: " + r.getPing(PING_PIN) + " cm"); 
			r.sleep(300); 
		}
		r.close();
	}

}
