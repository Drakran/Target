import rxtxrobot.*;
import java.util.Scanner;

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
		Scanner scan = new Scanner(System.in);
		int decision = scan.nextInt();
		scan.close();
		if(decision == 1) {
			runMotorsMeters();
		}
		else {
			runBumpMotors();
		}
		//r.runMotor(right, 300, left, -300, 4000);
		r.close();
		//r.close();
	}
	
	public static void runMotorsMeters() {
		r.runMotor(right, 220, left, -200, 7250); // right motor must offset by 20 or around that
	}
	
	public static void runBumpMotors() {
		r.runMotor(right, 220, left, -200, 0); 
		while(r.getAnalogPin(4).getValue() > 0) {
			r.refreshAnalogPins();
			if(r.getAnalogPin(4).getValue() == 0) {
				break;
			}
		}
	}

}
//Hi