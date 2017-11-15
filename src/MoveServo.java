//import java.util.*;
import rxtxrobot.*;

public class MoveServo 
{ 
	public static void main(String[] args) 
	{ 
		RXTXRobot r = new ArduinoNano(); // Create RXTXRobot object 
		r.setPort("COM5"); // Set the port to COM3 
		r.setVerbose(true); // Turn on debugging messages 

		r.connect(); 
		//int reverse = 360 -num;
//		//r.refreshDigitalPins();
		r.attachServo(RXTXRobot.SERVO2, 8);
		r.moveServo(RXTXRobot.SERVO2, 120);
//		r.sleep(500);	
//		r.moveServo(RXTXRobot.SERVO1, 90);
		r.sleep(3000);//Connect the servos to the Arduino 
		r.attachServo(RXTXRobot.SERVO1, 9); 
//		r.moveServo(RXTXRobot.SERVO1, 9); // Move Servo 1 to location 30   //360
		r.moveServo(RXTXRobot.SERVO1, 45);
		r.sleep(2000);

		r.moveServo(RXTXRobot.SERVO1, 0);
		r.sleep(2000);
		r.moveServo(RXTXRobot.SERVO1, 150);// Move Servo 2 to location 170  //180
//		r.sleep(5000);
		r.sleep(2000);
		r.moveServo(RXTXRobot.SERVO1, 45);
//		r.moveServo(RXTXRobot.SERVO1, 40);
//		r.sleep(2000);
//	r.moveServo(RXTXRobot.SERVO1, 100);
//		r.sleep(2000);
		r.setResetOnClose(false);
		r.close(); 
		
		
	} 
} 
//Hi