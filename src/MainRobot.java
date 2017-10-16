/*
 * MainRobot holds the main driver of the Robot and implements all necesary functions.
 * Such as: Moving Motors, Raising and Moving Certain Objects, Relaying Sensors, etc.
 * 
 * Terry Wang
 * 
 * Created October 13th, 2017
 * 
 * Copyright Terry Corp 2017
 */


//Imports java library and the rxtxrobot library
import java.util.*;
import rxtxrobot.*;


public class MainRobot {
	
	final private static int PING_PIN = 12; 
	final private static int rightSpeed = 410; //pin5
	final private static int leftSpeed = -300; //pin6
	final private static int right = RXTXRobot.MOTOR1;
	final private static int left = RXTXRobot.MOTOR2;
	final private static int boom = RXTXRobot.SERVO2;
	//Declares the static robot object
	public static RXTXRobot r;

	public static void main(String[] args) {
		
		int ping, distance;
		r = new ArduinoNano(); //Creates the r robot object 
		r.setPort("COM5"); //setsPort
		r.connect();
		Scanner scan = new Scanner(System.in);
		int decision = scan.nextInt();
		scan.close();
		//Connect the Motors
		ConnectMotors();
		ConnectServos();
		
		//FirstStep
		int firstDistance = 30;
		while(Ping() > firstDistance) {
			MotorsIndefinite();
			if(Ping() < firstDistance) {
				StopMotors();
			}
		}
		
		//First Turn
		if(decision == 0) {
			Turn(right);
		}
		else {
			Turn(left);
		}
		
		//SecondStep
		int second = 30;
		while(Ping() > second) {
			MotorsIndefinite();
			if(Ping() < second) {
				StopMotors();
			}
		}
		
		//Wait for barrier
		while(Ping() < second) {
			StopMotors();
			if(Ping() > second) {
				MotorsDistance(4000);
			}
		}
		
		r.sleep(1000); //Makes me feel better
		
		//Raise Boom
		MoveServos(180,boom);
		r.sleep(2000);
		Temperature();
		//windspeed
		
		//Turn again
		if(decision == 0) {
			Turn(left);
		}
		else {
			Turn(right);
		}
		
		
		r.close();
		
		
	}
	
	/*
	 * Formula for calculating Temperature
	 */
	public static double calculateTemp(double temp) {	
		return ((temp - 737.33)/(-7.0423));
	}	
	
	/*
	 * CHeck Motors
	 */
//	public static void checkMotors(int distance) {
//		if()
//	}
	
	/*
	 * Connect the Motor
	 */
	public static void ConnectMotors() {
		//Declare Ints
		int rightMotorPin = 5;
		int leftMotorPin = 6;
		r.attachMotor(right, rightMotorPin);
		r.attachMotor(left, leftMotorPin);
	}
	
	/*
	 * Connects Servos
	 */
	public static void ConnectServos() {
		r.attachServo(boom, 10); 
	}
	/*
	 * getAverage calculates the temp
	 * @param the pin that the temp is in
	 */
	public static double getAverage(int pin) {
		double adc = 0;
		int times =  10;
		for(int x =0; x < times; x++) {
			r.refreshAnalogPins();
			adc += r.getAnalogPin(pin).getValue();
		}
		return (adc/times);
	}
	
	/*
	 * Moves the Servos to a specified angle
	 * @param angle is the angle that the servo should go too
	 */
	public static void MoveServos(int angle, int servo) {
		r.moveServo(servo, angle); // Move Servo 2 to location 170  //180
		r.sleep(1000);
		//r.moveServo(servo, 90);
		r.sleep(2000);
	}
	/*
	 * Motors Method, to run the Motors Indefinite
	 */
	public static void MotorsIndefinite() {
		r.runMotor(right, rightSpeed, left, leftSpeed, 0);
	}
	/*
	 * Motors to a certain distance
	 */
	public static void MotorsDistance(int distance) {
		r.runMotor(right, rightSpeed, left, leftSpeed, distance);
	}
		

	
	
	/*
	 * Ping Method, gets Ping to a certain number of ties
	 */
	public static int Ping() {
		int value;
		value = r.getPing(PING_PIN);
		return value;
	}
	
	/*
	 * Stop Indefeintie Motors
	 */
	public static void StopMotors() {
		r.runMotor(right, 0, left, 0, 0);
	}
	
	/*
	 * Temperature Method
	 */
	public static void Temperature() {
		int uncoveredPin = 0;
		int coveredPin = 1;
		double tempUncovered = calculateTemp(getAverage(uncoveredPin));
		double tempCovered = calculateTemp(getAverage(coveredPin));
		System.out.println("Temp of Uncovered: " + tempUncovered);
		System.out.println("Temp of Covered: " + tempCovered);
	}
	
	/*
	 * Turn Motors
	 */
	public static void Turn (int motor) {
		if(motor == right) {
			r.runMotor(right, 0, left, leftSpeed, 1000);
		}
		else {
			r.runMotor(right, rightSpeed, left, 0, 1000);
		}
	}
}



