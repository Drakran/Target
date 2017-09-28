import rxtxrobot.*;

public class MotorTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RXTXRobot r = new ArduinoNano();
		r.setPort("COM5");
		r.connect();
		//r.runMotor(RXTXRobot.MOTOR1, 225, 3000); //O time = infite 0 speed = stop
		//r.runMotor(RXTXRobot.MOTOR1, 0, 0); // speed, time
		r.runEncodedMotor(RXTXRobot.MOTOR1, 255, 100);
		r.close();
	}

}
