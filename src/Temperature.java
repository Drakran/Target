import rxtxrobot.*;


public class Temperature {

	public static void main (String []args) {
		RXTXRobot r = new ArduinoNano();
		r.setPort("COM5");
		r.connect();
		r.refreshAnalogPins();
		int pinTemp = 3; //Actaully find it
		double temp = r.getAnalogPin(pinTemp).getValue();
		System.out.println(temp);
	}
}
