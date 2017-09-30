import rxtxrobot.ArduinoNano;
import rxtxrobot.*;


public class Bump {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RXTXRobot r = new ArduinoNano(); // Create RXTXRobot object 
		r.setPort("COM5"); // Set the port to COM3 
		r.connect(); 

//		for(int x =0; x < times; x++) {
//			value = r.getDigitalPin(5).getValue(); //0 is non touch 1 is touch
//			System.out.println("Value: " + value + "\n");
//		}
		while((r.getDigitalPin(5).getValue()) == 0) {
			r.refreshDigitalPins();
			System.out.println("Value: " + (r.getDigitalPin(5).getValue()) + "\n");
			r.sleep(300);
		}
		r.close();

	}

}
