import rxtxrobot.*;

public class MotorTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RXTXRobot r = new ArduinoNano();
		r.setPort("COM5");
		r.connect();
		//r.runMotor(RXTXRobot.MOTOR1, 225, RXTXRobot.MOTOR2, 500, 300); //O time = infite 0 speed = stop
		r.runMotor(RXTXRobot.MOTOR1, 225, 300); // speed, time
		//r.runEncodedMotor(RXTXRobot.MOTOR1, 255, 100);
		r.close();
		
	}

}
