package ev3dprinter.devices;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.Port;
import lejos.utility.Delay;

public class BaseMotor
{
	
	TouchSensor touchSensor;
	
	EV3LargeRegulatedMotor m;
	
	Boolean invert;
	
	float pos;
	float offset;
	float studRot;
	float defaultSpeed;
	
	// Used to invert angle inputs and rotation commands
	float v = 1f;
	
	public BaseMotor(Port motorAddress, Port sensorAddress, float defaultSpeed, Boolean inverted)
	{
	    
		// For the y-axis motor, 1 full rotation = 9 studs, therefore 1 stud = 0.111... rotations	
		
		this.m = new EV3LargeRegulatedMotor(motorAddress);
		this.touchSensor = new TouchSensor(sensorAddress);
		
		if(inverted)
		{
			this.v = -1f;
		}
		
		this.pos = 0f;
		this.invert = inverted;
		this.offset = 0f;
		this.studRot = 1f / 9f;
		this.defaultSpeed = defaultSpeed;
		this.m.setSpeed(defaultSpeed);
		
	}	
	
	
	public void Calibrate()
	{
		
		this.forward();
		
		touchSensor.waitUntilRealeased();
		
		Delay.msDelay(500);
		
		this.m.stop();
		this.m.setSpeed(defaultSpeed * 0.2f);
		this.backward();
		
		touchSensor.waitUntilPressed();
		
		this.m.stop();
		
	}

	public void forward()
	{
		if(invert)
		{
			this.m.backward();
		}
		
		this.m.forward();
		
	}

	public void backward()
	{
		if(invert)
		{
			this.m.forward();
		}
		
		this.m.backward();
		
	}
		
	
}
