import rxtxrobot.*;


public class Temperature {
	
	public static int TIMES = 10;
	public static RXTXRobot r;

	public static void main (String []args) 
	{
		r = new ArduinoNano();
		r.setPort("COM5");
		r.connect();
		r.refreshAnalogPins();
		int pin0 = 0;
		int pin1 = 1;
		double temp1 = (getAverage(pin0)/TIMES);
		double temp2 = (getAverage(pin1)/TIMES);
		System.out.println("ADC code Temp1 for Uncovered: " + temp1);
		double reallyFinalTemp1 = calculateTemp(temp1);
		System.out.println("TempOne(Uncovered): " + reallyFinalTemp1 + "\n");
		System.out.println("ADC code Temp2 for Covered: " + temp2);
		double reallyFinalTemp2 = calculateTemp(temp2);
		System.out.println("TempTwo(Covered): " + reallyFinalTemp2 + "\n");
		double difference = reallyFinalTemp2 - reallyFinalTemp1;
		System.out.println("Difference: " + difference); 
		
		r.close();
	}



	public static double calculateTemp(double temp) {	
		return ((temp - 737.33)/(-7.0423));
	}	
	
	public static double getAverage(int pin) {
		double adc = 0;
		for(int x =0; x < TIMES; x++) {
			r.refreshAnalogPins();
			adc += r.getAnalogPin(pin).getValue();
		}
		return adc;
	}
}
	


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
