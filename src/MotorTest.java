import rxtxrobot.*;

public class MotorTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RXTXRobot r = new ArduinoNano();
		r.setPort("COM5");
		r.connect();
		r.setVerbose(true);
		//r.refreshDigitalPins();
		//r.runMotor(RXTXRobot.MOTOR1, 225, RXTXRobot.MOTOR2, 500, 300); //O time = infite 0 speed = stop
		r.attachMotor(RXTXRobot.MOTOR2, 6);
//		r.attachMotor(RXTXRobot.MOTOR2, 6);
		r.runMotor(RXTXRobot.MOTOR2, 200, 3000);
//		r.runMotor(RXTXRobot.MOTOR1, 500, RXTXRobot.MOTOR2, 500, 0); // speed, time
//		r.runEncodedMotor(RXTXRobot.MOTOR1, 500, 10);

		r.close();
		
	}

}
//Hi