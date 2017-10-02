import rxtxrobot.*;

public class MotorTest {

	public static int right = RXTXRobot.MOTOR1;
	public static int left = RXTXRobot.MOTOR2;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RXTXRobot r = new ArduinoNano();
		//RXTXRobot test = new RXTXRobot();
		r.setPort("COM5");
		r.connect();
		r.setVerbose(true);
		//r.refreshDigitalPins();
		//r.runMotor(RXTXRobot.MOTOR1, 225, RXTXRobot.MOTOR2, 500, 300); //O time = infite 0 speed = stop
		r.attachMotor(right, 5);
		r.attachMotor(left, 6);
		//System.out.println(right);
//		r.attachMotor(RXTXRobot.MOTOR2, 6);
//		r.runMotor(right, 500, 1000);
//		r.runMotor(left, 200, 3000);
		r.runMotor(right, -100, left, 120, 0); 
		while(r.getAnalogPin(4).getValue() > 0) {
			r.refreshAnalogPins();

			if(r.getAnalogPin(4).getValue() == 0) {
				r.close();
				break;
			}
		}
//		r.runMotor(right, -100, left, 100, 0); // speed, time
//		r.runEncodedMotor(RXTXRobot.MOTOR1, 500, 10);
		
//		r.close();
		
	}

}
//Hi