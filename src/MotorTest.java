import rxtxrobot.*;

public class MotorTest {

	public static int right = RXTXRobot.MOTOR1;
	public static int left = RXTXRobot.MOTOR2;
	public static RXTXRobot r;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		r = new ArduinoNano();
		r.setPort("COM5");
		r.connect();
		r.setVerbose(true);
		r.attachMotor(right, 5);
		r.attachMotor(left, 6);
		runMotorsMeters();
		//runBumpMotors();
		r.close();
		
	}
	
	public static void runMotorsMeters() {
		r.runMotor(RXTXRobot.MOTOR1, 225, RXTXRobot.MOTOR2, 225, 10000);
		r.close();
	}
	
	public static void runBumpMotors() {
		r.runMotor(right, -100, left, 120, 0); 
		while(r.getAnalogPin(4).getValue() > 0) {
			r.refreshAnalogPins();
			if(r.getAnalogPin(4).getValue() == 0) {
				r.close();
				break;
			}
		}
	}

}
//Hi