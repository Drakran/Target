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
		int num = 180;
		//int reverse = 360 -num;
		//r.refreshDigitalPins();
//		r.attachServo(RXTXRobot.SERVO1, 9);
//		r.moveServo(RXTXRobot.SERVO1, 180); //Connect the servos to the Arduino 
//		r.moveServo(RXTXRobot.SERVO1, -90); // Move Servo 1 to location 30   //360
//		r.moveServo(RXTXRobot.SERVO1, reverse);
		r.attachServo(RXTXRobot.SERVO2, 10); 
		r.moveServo(RXTXRobot.SERVO2, 71); // Move Servo 2 to location 170  //180
		r.sleep(1000);
		r.moveServo(RXTXRobot.SERVO2, 180);
		//r.close(); 
	} 
} 
//Hi