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
		r.attachServo(RXTXRobot.SERVO1, 9); //Connect the servos to the Arduino 
		r.attachServo(RXTXRobot.SERVO2, 10); 
		r.moveServo(RXTXRobot.SERVO1, 30); // Move Servo 1 to location 30 
		r.moveServo(RXTXRobot.SERVO2, 170); // Move Servo 2 to location 170 
		r.close(); 
	} 
} 
//Hi