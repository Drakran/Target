import rxtxrobot.*;

public class Temperature {


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RXTXRobot r = new ArduinoNano();
		r.setPort("COM5");
		r.connect();
		r.refreshAnalogPins();
		int pinTemp = 3; // Whatever pin it is attachted too
		double temp = r.getAnalogPin(pinTemp).getValue();
		//Temp  = formula;
		System.out.println("Temp: " + temp);
		
	}

}
