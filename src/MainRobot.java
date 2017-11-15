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
	
	final private static int FRONT_PING_PIN = 10; 
	final private static int LEFT_PING_PIN = 11; //ACTUALLY SIDE PING PIN 
	final private static int rightSpeed = -180; //pin5
	final private static int leftSpeed = 190; //pin6
	final private static int leftRamp = 280
			;
	final private static int rightRamp = -250;
	final private static int right = RXTXRobot.MOTOR1;
	final private static int left = RXTXRobot.MOTOR2;
	final private static int cond = RXTXRobot.SERVO1;
	final private static int boom = RXTXRobot.SERVO2;
	//Declares the static robot object
	public static RXTXRobot r;

	public static void main(String[] args) {
		
		r = new ArduinoNano(); //Creates the r robot object 
		r.setPort("COM5"); //setsPort
		r.connect();
//		r.setVerbose(true);
		r.setResetOnClose(false);
		ConnectMotors();
		ConnectServos();
		r.moveServo(cond, 40);
		r.moveServo(boom,0);
		Scanner scan = new Scanner(System.in);
		int decision = scan.nextInt();
		scan.close();
		//Connect the Motors

//		r.sleep(2000);
//		TurnValues(right,-400,-400,3000);
//		r.runMotor(right, , left, 0, 1000);
		r.sleep(2000);

//		firstDistance();
// 		r.sleep(1000);
// 		firstTurn(decision);
//  		int anotherBarrier = 45;
//  		int timeRamp = 2950; //How long to get to time
// 		barrierDistance(45);
// 		barrierWait(anotherBarrier,timeRamp,decision);
// 		r.sleep(1000); //Makes me feel better  		  		
		 //Raise Boom and Collect  Temperature
//		raiseBoom();
 		//Turn in canyon and before move out of canyon
 		//Turn again
//		afterBoomDistance(decision,2000); //2nd variable is time after boom lift				
		//Turn in canyon and before move out of canyon
//		canyonMove(decision);
////		r.sleep(1000);
//////		
//////		//Move from canyon to ramp
//		postCanyonMove(decision);
////		//Across the Bridge
//		acrossBridge(decision);
//////		To Sandbox
//		sandBoxDistance(decision);
////		r.moveServo(cond, 40);
//		r.sleep(2000);
		
		Conductivity();
//		System.out.println(getConductivity());
		r.close();
		
		
	}
	
	
	/*
	 * First Distance is the first distance out of the box
	 * IT uses while loop to detect the 50cm till the wall and stops
	 */
	public static void firstDistance() {
		int firstDistance = 65;
		MotorsIndefinite();
		boolean distance = true;
		while(distance) {
			r.sleep(100);
			//System.out.println(Ping(FRONT_PING_PIN));
			if(r.getPing(FRONT_PING_PIN) < firstDistance) {
				r.runMotor(right, 0, left, 0, 0);
				distance = false;
			}
		}
	}
	
	/*
	 * First turn is the first turn after the box
	 * @param decision is which course it will run (right or left)
	 */
	public static void firstTurn(int decision) {
		if(decision == 0) {
			//r.runMotor(right, 250, left, 280, 1350);
			TurnValues(right, 250, 250,1425); 
		}
		else {
			TurnValues(left, -250,-250, 1290);
		}
	}
	
	/*
	 * This is the distance till the barrier, it uses a while loop
	 * to detect the barrier
	 * @param distance is the distance itll stop before the barrier
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
	 * Wait Barrier, waits till you remove barrier, and then goes up the ramp
	 * @param distance, what is the distance it needs to be greater than to finally go up
	 * (i.e. 60 is the distance between ramp and robot without barrier
	 * @param time how long to get to ramp
	 */
	public static void barrierWait(int distance, int time, int decision) {
		boolean something = true;
		while(something) {
			if(Ping(FRONT_PING_PIN) > distance) {
				if(decision == 0) {
					MotorsRamp(2950);
					something = false;
				}
				else {
					MotorsRamp(3150);
				}
				something = false;
			}
		}

	}
	/*
	 * Raises the boom, and detects tempearture
	 */
	public static void raiseBoom() {
		r.moveServo(RXTXRobot.SERVO2, 92); // Move Servo 2 to location 170  //180
		
		r.sleep(10000);
		Temperature();
		r.sleep(5000);
		r.moveServo(RXTXRobot.SERVO2, 0);
		//Finallize WindSpeed here
	}
	
	/*
	 * Turn after boom, it only goes down the ramp, thats all
	 * @param decision the decision
	 * @distance, distance down the ramp, or really its time it moves down the ramp
	 */
	public static void afterBoomDistance(int decision, int distance) {
		if(decision == 0) {
			TurnRamp(left);
			r.sleep(1000);
			MotorsRampDistance(distance); //Distance is eh?
		}
		else {
			TurnRamp(right);
			MotorsRampDistance(3500);
		}
	}
	/*
	 * How the robot moves in the canyon, and how it detects the gap
	 * @param decision the decision
	 */
	public static void canyonMove(int decision) {
		if(decision == 0) {
			boolean wait = true;
			MotorsIndefiniteNew();
			while(wait) {
				if(Ping(LEFT_PING_PIN) > 80) {
					r.sleep(300);
					System.out.println(Ping(LEFT_PING_PIN));
					StopMotors();
					break;
				}
				

			}
			r.sleep(2000);
			//TUrns left
			TurnValues(left, -260,-250,1100);
		}

		else {
			boolean wait = true;
			MotorsIndefiniteNew();
			while(wait) {
			//	System.out.println(Ping(LEFT_PING_PIN));
				if(r.getPing(LEFT_PING_PIN) > 80) {
					r.sleep(415);
					StopMotors();
					break;
				}

			}
			r.sleep(2000);
			//TUrns right
			TurnValues(right, 250,250,1650); //1350
		}
		
	}
	//THis is where it moves after it turns towards East(Where bridge is)
	/*
	 * This is where the canyon moves after it detects the gap, and is turned towards it
	 * if the sDistance is less than a certain value, itll either go forward and turn, or go
	 * through a series a turns
	 * @param decision the decision
	 */
	public static void postCanyonMove(int decision) {//Distance to wall after turn so it looks towards the bridgeside.
		int distanceToWall = 60; 
		//Goes to whatever wall is in front
		boolean first = true;
		MotorsDistance(2700);
		
//		MotorsIndefinite();
//		while(first) {
//			if(Ping(FRONT_PING_PIN) < distanceToWall) {
//				StopMotors();
//				first = false;
//			}
//		}
		//This distance is to north side wall
		int sDistance;
		r.sleep(1000);
		sDistance = Ping(LEFT_PING_PIN);
		System.out.println(sDistance);
		//If it is directly going to wall
		if(sDistance < 80) {
			while(Ping(FRONT_PING_PIN) > distanceToWall)
			{
				MotorsIndefinite();
			}
			StopMotors();
			if(decision == 0) {
				backItUp(decision);
			}
			else {
				backItUp(decision);
			}
		}
		//IS if doesn't go directly, and needs to turn multiples times
		else {
			if(decision == 0) {
				//r.runMotor(right, -260, left, -255, 1400); // first left
				TurnValues(left, -250,-250,1275); //First LEft
				//This is what happens if it goes not direct
				boolean secondOption = true;
				MotorsIndefinite();
				while(secondOption) {
					//When its near what it would be if it went directly east
					if(Ping(FRONT_PING_PIN) < 78) {
						StopMotors();
						secondOption = false;
					}
				}
				r.sleep(1000);
				//r.runMotor(right, 250, left, 290, 1350);
				TurnValues(right, 250, 290, 1200);
				r.sleep(1000);
				MotorsIndefinite();
				secondOption = true;
				while(secondOption) {
					//When its near what it would be if it went directly east
					if(Ping(FRONT_PING_PIN) < 50) {
						StopMotors();
						secondOption = false;
					}
				}
				//r.runMotor(right, 250, left, 260, 1300);
				//TurnValues(right, 250, 260, 1300);
				backItUp(decision);
				
			}
			else {
				//r.runMotor(right, -260, left, -255, 1400); // first right
				TurnValues(right, 250,250,1450); //TEST
				//This is what happens if it goes not direct
				boolean secondOption = true;
				MotorsIndefinite();
				while(secondOption) {
					//When its near what it would be if it went directly east
					if(Ping(FRONT_PING_PIN) < 78) {
						r.sleep(100);
						StopMotors();
						break;
					}
				}
				r.sleep(1000);
				TurnValues(left, -250, -250, 1200); //TEST
				r.sleep(1000);
				MotorsIndefinite();
				secondOption = true;
				while(secondOption) {
					//When its near what it would be if it went directly east
					if(Ping(FRONT_PING_PIN) < 50) {
						StopMotors();
						break;
					}
				}
				
				r.sleep(500);
			//	TurnValues(left, -250, -250, 1400); //1200
				backItUp(decision);
			}
			//MotorsIndefinite();
			
		}

	}
	
	public static void backItUp(int decision) {
		if(decision == 0) {
			r.sleep(1000);
			TurnValues(right,-250,-250,2600); //180
			r.sleep(1000);
			r.runMotor(right, 240, left, -230, 2000); //Reverse 180
			r.sleep(1000);
			r.runMotor(right, rightSpeed, left, leftSpeed, 300);
			r.sleep(1000);
			TurnValues(left, -250, -250, 800);// turn left
			r.sleep(1000);
			r.runMotor(right, 200, left, -200, 3000);
			r.sleep(1000);
		//	TurnValues(left,-250,-250,1300);
		}
		else {
			r.sleep(1000);
			TurnValues(left,-250,-250,2600);
			r.sleep(1000);
			r.runMotor(right, 190, left, -180, 2000); //reverse 180
			r.sleep(1000);
			r.runMotor(right, rightSpeed, left, leftSpeed, 300);
			r.sleep(1000);
			TurnValues(right, 250, 250, 1180);
			r.sleep(1000);
			r.runMotor(right, 220, left, -220, 3500);
			r.sleep(1000);
		//	TurnValues(right, 250, 250, 1400);
			
		}
	}
	
	/*
	 * Distance across Bridge
	 */
	public static void acrossBridge(int decision) {
		//MotorsDistance(10000); //DOnt know if enough power
		if(decision == 0) {
			MotorsRampBridge(7600);
		}
		else {
			MotorsRampBridge(8100);
		}
		
	}
	
	/*
	 * To Sandbox distance by ping, it turns first of course then goes till ping detects a distance
	 * @decision a decision
	 */
	public static void sandBoxDistance(int decision) {
		if(decision == 0) {
			r.sleep(1000);
			TurnValues(right, 250, 250, 1450);
			r.sleep(1000);
			MotorsIndefiniteNew();
			boolean x = true;
			while(x) {
				if(Ping(FRONT_PING_PIN) < 5) {
					StopMotors();
					x= false;
				}
			}
		}
		else {
			r.sleep(1000);
			TurnValues(left, -250, -250, 1325);
			r.sleep(1000);
			MotorsIndefiniteNew();
			boolean x = true;
			while(x) {
				if(Ping(FRONT_PING_PIN) < 5) {
					StopMotors();
					x=false;
				}
			}
			//Idk
		}

	}
	
	public static void Conductivity() {
		r.sleep(2000);
		r.moveServo(cond, 20);
		r.sleep(2000);
		double condu = getConductivity();
		if(condu > 1) {
			r.moveServo(cond, 130);
			
		}
		System.out.println(condu);
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
		r.attachServo(cond, 9);
		r.attachServo(boom, 8); 
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
		//r.moveServo(servo, 90);
	}
	/*
	 * Motors Method, to run the Motors Indefinite
	 */
	public static void MotorsIndefinite() {
		r.runMotor(right, rightSpeed, left, leftSpeed, 0);
	}
	
	//SLOWS IN THE CANYON, so ping can detect
	public static void MotorsIndefiniteNew() {
		r.runMotor(right, -157, left, 150, 0);
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
	
	public static void MotorsRampBridge(int distance) {
		r.runMotor(right, -200, left, 220, distance); //left 215
	}
	
	public static void MotorsRampBridgeRight(int distance) {
		r.runMotor(right, -210, left, 200, distance);
	}
	
	public static void MotorsRampDistance(int distance){
		r.runMotor(right, -130, left, 130, distance);
	}
		

	/*
	 * Conductivity, gets the conductivity from pin 12,13 analog 4,5
	 * @return the Conductivity thing
	 */
	public static double getConductivity() {
//		for(int x = 0; x < 10; x++) {
//			
//		}
		double conCode = r.getConductivity() + 43;
		conCode = (-0.098 * conCode) + 100.02;
		return conCode;
	}
	
	/*
	 * Ping Method, gets Ping to a certain number of ties
	 * @return the ping that im reutrning b/c yay
	 */
	public static int Ping(int pin) {
		r.refreshDigitalPins();
		return r.getPing(pin);
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
		int uncoveredPin = 3;
		int coveredPin = 1;
		double tempUncovered = 0;
		double tempCovered = 0;
		 tempUncovered = calculateTemp(getAverage(uncoveredPin));
		 tempCovered = calculateTemp(getAverage(coveredPin)) ;
		System.out.println("Temp of Uncovered: " + tempUncovered);
		System.out.println("Temp of Covered: " + ((tempCovered))); //DIsplays covdered -5
		System.out.println("Wind Speed is " + (tempCovered  - tempUncovered - 3));
	}
	
	/*
	 * Turn Motors
	 */
	public static void Turn (int motor) {
		if(motor == right) {
			r.runMotor(right, 250, left, 245, 1300); //35-  250,245
		}
		else {
			r.runMotor(right, -253, left, -250, 1230); //-300 -253,-250
		}
	}
	
	public static void TurnRamp (int motor) {
		if(motor == right) {
			r.runMotor(right, 250, left, 250, 1200); //35-
		}
		else {
			r.runMotor(right, -250, left, -250, 840); //-300
		}
	}
	
	public static void TurnValues(int motor, int rigthy, int lefty, int time) {
		if(motor == right) {
			r.runMotor(right, rigthy , left, lefty, time); //35-
		}
		else {
			r.runMotor(right, rigthy, left, lefty, time); //-300
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


