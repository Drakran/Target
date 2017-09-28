import rxtxrobot.*;


public class Temperature {

	public static void main (String []args) {
		RXTXRobot r = new ArduinoNano();
		int[] array = {42,42,42};
		r.setPort("COM5");
		r.connect();
		r.refreshAnalogPins();
		int pinTemp = 0; //Actaully find it
		int times = 10;
		double adc = 0;
		for(int x =0; x < times; x++) {
			adc += r.getAnalogPin(pinTemp).getValue();
		}
		double finalTemp = adc/times;
		System.out.println("ADc code: " + finalTemp + "\n");
		double reallyFinalTemp = 0;
		reallyFinalTemp = (finalTemp - 737.33)/(-7.0423);
		System.out.println("Temp: " + reallyFinalTemp);
		
		
		r.close();
	}
}


//public double Calculate() {
	


/*adc code = 1023 * rt/(rt+r fixed)
 * for (int x= 0; x < 10 ; x++){
 * 
 * getThermistorreading
 * 
 * adc = slope * t(y) + intercecpt
 * 
 * t = (adc code- interectp)/ slope)
 * y = -7.0423x + 737.33

 * 
 */
