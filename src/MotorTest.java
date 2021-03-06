import rxtxrobot.*;
import java.util.Scanner;

public class MotorTest {
	//right is left, left is right
	public static int right = RXTXRobot.MOTOR1;
	public static int left = RXTXRobot.MOTOR2;
	public static int rightSpeed = -250; // -200
	public static int leftSpeed = -250; // 300
	public static int distance = 1400;
	public static RXTXRobot r;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		r = new ArduinoNano();
		//r.setMotorRampUpTime(600);
		r.setPort("COM5");
		r.connect();
		r.setVerbose(true);
		r.attachMotor(right, 5);
		r.attachMotor(left, 6);
		r.attachServo(RXTXRobot.SERVO1, 9);
		r.moveServo(RXTXRobot.SERVO1, 120);
		//r.runMotor(right, 0, left, 0, distance); 
		Scanner scan = new Scanner(System.in);
		int decision = scan.nextInt();
		scan.close();
		if(decision == 1) {
			runMotorsMeters();
		}
		else if(decision == 2) {
			runBumpMotors();
		}
		else if(decision == 3) {
			runEncodedMotors();
		}
		//r.runMotor(right, 300, left, -300, 4000);
		r.close();
		//r.close();
	}
	
	public static void runMotorsMeters() {
		r.runMotor(right, rightSpeed, left, leftSpeed, distance); // right motor must offset by 20 or around that1 6000 seconds
	}
	
	public static void runBumpMotors() {
		r.runMotor(right, rightSpeed, left, leftSpeed, 0); 
		while(r.getAnalogPin(4).getValue() > 0) {
			r.refreshAnalogPins();
			if(r.getAnalogPin(4).getValue() == 0) {
				break;
			}
		}
	}
	
	public static int rTicks = 20;
	public static int lTicks = 20;
	public static void runEncodedMotors() {
		//r.runEncodedMotor(right, rightSpeed, 10, left, leftSpeed, 10);
		r.runEncodedMotor(right, 200, 10);
		r.close();
	}

}
//Hi