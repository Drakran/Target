
import rxtxrobot.*;

public class EncodedMotors {


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RXTXRobot r = new ArduinoNano();
		r.setPort("COM5");
		r.connect();
		r.runEncodedMotor(RXTXRobot.MOTOR1, 400, 300);
		r.runEncodedMotor(RXTXRobot.MOTOR2, 400, 300);
		int encodedNumOne = r.getEncodedMotorPosition(RXTXRobot.MOTOR1);
	}
	
//	public int returnCode(Motor x) {
//		return getEncodedMotorPosition(x);
//	}

}

