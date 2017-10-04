import rxtxrobot.ArduinoNano;
import rxtxrobot.*;


public class Bump {

	public static void main(String[] args) {
		RXTXRobot r = new ArduinoNano(); // Create RXTXRobot object 
		r.setPort("COM5"); // Set the port to COM3 
		r.connect(); 

//		for(int x =0; x < times; x++) {
//			value = r.getDigitalPin(5).getValue(); //0 is non touch 1 is touch
//			System.out.println("Value: " + value + "\n");
//		}
		//r.refreshDigitalPins();
//		while((r.getDigitalPin(4).getValue()) == 0) {
//			//r.refreshDigitalPins();
//			System.out.println("Value: " + (r.getDigitalPin(4).getValue()) + "\n");
//			r.sleep(100);
//		}
		r.refreshAnalogPins();
		while(true) {
			r.refreshAnalogPins();
			int temp = r.getAnalogPin(4).getValue();
			System.out.println(temp);
			if(temp <= 10) {
				System.out.println("Something");
				break;
			}
		}
		r.close();

	}

}
