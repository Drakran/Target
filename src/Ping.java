import rxtxrobot.ArduinoNano;
import rxtxrobot.RXTXRobot;

public class Ping {

	final private static int PING_PIN = 12; 
	
	public static void main(String[] args) {
		RXTXRobot r = new ArduinoNano(); // Create RXTXRobot object 
		r.setPort("COM5"); // Set the port to COM3 
		r.connect(); 
		int times = 10;
		for(int x =0; x < times; x++) {
			if(r.getPing(PING_PIN) <= 30) {
				System.out.println("Its less than 30 cm, the value is: " + r.getPing(PING_PIN) );
			}
			else {
				System.out.println("Its greater than 30 cm, the value is: " + r.getPing(PING_PIN));
			}
			//System.out.println("Response: " + r.getPing(PING_PIN) + " cm"); 
			r.sleep(300); 
		}
		r.close();
	}

}
