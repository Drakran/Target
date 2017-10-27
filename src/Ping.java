import rxtxrobot.ArduinoNano;
import rxtxrobot.RXTXRobot;

public class Ping {

	final private static int PING_PIN = 11; 
	
	public static void main(String[] args) {
		RXTXRobot r = new ArduinoNano(); // Create RXTXRobot object 
		r.setPort("COM5"); // Set the port to COM3 
		r.connect(); 
		int times = 20;
		for(int x =0; x < times; x++) {
			r.refreshDigitalPins();
				System.out.println("the value is: " + r.getPing(PING_PIN));
				r.sleep(300);
			}
		r.close();
			//System.out.println("Response: " + r.getPing(PING_PIN) + " cm"); 
		}
	}


