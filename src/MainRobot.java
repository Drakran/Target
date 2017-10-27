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
	
	final private static int FRONT_PING_PIN = 12; 
	final private static int LEFT_PING_PIN = 11;
	final private static int RIGHT_PING_PIN = 10;
	final private static int rightSpeed = -200; //pin5
	final private static int leftSpeed = 250; //pin6
	final private static int leftRamp = 400;
	final private static int rightRamp = -350;
	final private static int right = RXTXRobot.MOTOR1;
	final private static int left = RXTXRobot.MOTOR2;
	final private static int boom = RXTXRobot.SERVO2;
	//Declares the static robot object
	public static RXTXRobot r;

	public static void main(String[] args) {
		
		r = new ArduinoNano(); //Creates the r robot object 
		r.setPort("COM5"); //setsPort
		r.connect();
		Scanner scan = new Scanner(System.in);
		int decision = scan.nextInt();
		scan.close();
		//Connect the Motors
		ConnectMotors();
		ConnectServos();
		
		firstDistance();
		r.sleep(1000);
		firstTurn(decision);
//		TurnForever();
		
//		//SecondStep
		int pingDistanceBarrier = 45;
		barrierDistance(pingDistanceBarrier);
//		
//		//Wait for barrier
		int timeRamp = 3000; //How long to get to time
		barrierWait(pingDistanceBarrier,timeRamp);
//		
//		r.sleep(1000); //Makes me feel better
//		
//		//Raise Boom and Collect  Temperature
//		//raiseBoom();
//		
//		//Turn again
//		afterBoomDistance(decision,1000); //2nd variable is time after boom lift
//		
//		//Turn in canyon and before move out of canyon
//		canyonMove(decision);
//		r.sleep(1000);
//		
//		//Move from canyon to ramp
//		postCanyonMove(decision);
		r.close();
		
		
	}
	
	
	/*
	 * 
	 */
	public static void firstDistance() {
		int firstDistance = 45;
		MotorsIndefinite();
		boolean distance = true;
		while(distance) {
			if(Ping(FRONT_PING_PIN) < firstDistance) {
				StopMotors();
				distance = false;
			}
		}
	}
	
	/*
	 * First turn
	 */
	public static void firstTurn(int decision) {
		if(decision == 0) {
			Turn(right);
		}
		else {
			Turn(left);
		}
	}
	
	/*
	 * Second Foward
	 */
	public static void barrierDistance(int distance) {
		boolean somePing = true;
		MotorsIndefinite();
		while(somePing) {
			if(Ping(FRONT_PING_PIN) < distance) {
				StopMotors();
				somePing = false;
			}
		}
	}
	
	/*
	 * Wait Barrier
	 * @param distance, what is the distance of ping
	 * @param time how long to get to ramp
	 */
	public static void barrierWait(int distance, int time) {
		while(Ping(FRONT_PING_PIN) < distance) {
			StopMotors();
		}
			r.sleep(2000);
			MotorsRamp(time);

		}
	/*
	 * 
	 */
	public static void raiseBoom() {
		MoveServos(180,boom);
		r.sleep(2000);
		Temperature();
		//Finallize WindSpeed here
	}
	
	/*
	 * Turn after boom
	 */
	public static void afterBoomDistance(int decision, int distance) {
		if(decision == 0) {
			Turn(left);
			r.sleep(300);
			MotorsDistance(distance); //Distance is eh?
		}
		else {
			Turn(right);
		}
	}
		
	public static void canyonMove(int decision) {
		if(decision == 0) {
			boolean wait = true;
			MotorsIndefinite();
			while(wait) {
				if(Ping(LEFT_PING_PIN) >50) {
					StopMotors();
					wait = false;
				}

			}
			r.sleep(2000);
			Turn(left);
		}

		else {
			//Ping for pin 3?
		}
		
	}
	
	public static void postCanyonMove(int decision) {
		int fDistance = Ping(FRONT_PING_PIN);
		while(fDistance > 60) {
			MotorsIndefinite();
		}
		StopMotors();
		int sDistance;
		if(decision == 0) {
			sDistance = Ping(LEFT_PING_PIN);
		}
		else {
			sDistance = Ping(RIGHT_PING_PIN);
		}
		if(sDistance < 110) {
			while(Ping(FRONT_PING_PIN) > 60) {
				MotorsIndefinite();
			}
			StopMotors();
			if(decision == 0) {
				Turn(right);
			}
			else {
				Turn(left);
			}
		}
		else {
			if(decision == 0) {
				Turn(right);
			}
			else {
				Turn(left);
			}
			MotorsIndefinite();
			
		}

	}
	/*
	 * Formula for calculating Temperature
	 * @param the adc code really thats being sent
	 * @return the real temp should be
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
	 * @param distance is the "time" i want to go forward 
	 */
	public static void MotorsDistance(int distance) {
		r.runMotor(right, rightSpeed, left, leftSpeed, distance);
	}
	
	public static void MotorsRamp(int distance) {
		r.runMotor(right, rightRamp, left, leftRamp, distance);
	}
		

	
	
	/*
	 * Ping Method, gets Ping to a certain number of ties
	 * @return the ping that im reutrning b/c yay
	 */
	public static int Ping(int pin) {
		int value = 0; //Note possible source of error? returns zero
			value = r.getPing(pin);

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
		System.out.println("Temp of Covered: " + ((tempCovered) -5)); //DIsplays covdered -5
	}
	
	/*
	 * Turn Motors
	 */
	public static void Turn (int motor) {
		if(motor == right) {
			r.runMotor(right, 250, left, 258, 1300); //35-
		}
		else {
			r.runMotor(right, -238, left, -250, 1230); //-300
		}
	}
	
	public static void TurnForever() {
		for(int x = 0; x < 4; x++) {
			Turn(right);
			r.sleep(500);
		}
	}
}

//public static void Turn (int motor) {
//	if(motor == right) {
//		r.runMotor(right, 0, left, 350, 1260); //35-
//	}
//	else {
//		r.runMotor(right, -300, left, 0, 1630); //-300
//	}
//}


